package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.orm.AccessDB;
import com.bill99.cps.service.ConverMgwDataDriverService;
//import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.cps.service.check.CpsDbCheck;
import com.bill99.mcs.service.McsService;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**
 * @Title:CPS CNP交易
 * @Description:CPS1.1、1.2、1.3、1.4、1.5、1.6,MCS1.1、1.2、1.3、1.4、1.5
 * @author chongpeng.yan
 * @Since:2017年06月23日
 */
public class BDCA_MgwCnp extends BaseTestCase {

	
	@Autowired
	@Qualifier("httpContentService")
	private HttpContentService httpContentService;
	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;
//	@Autowired
//	private DBOracleCheck dbOracleCheck;
	@Autowired
	private AccessDB accessDB;
	@Resource
	private CpsDbCheck cpsDbCheck;
	@Resource
	private McsService mcsService;

	private String refNumber = "";
	private String cfmrefNumber = "";

	// pur 消费
	@Test(dataProvider = "CnpPurchasePUR", description = "CNP 消费", timeOut = 200000)
	public void cnpPur(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));
		// http 发送报文
		String respose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));

		Reporter.log("respose=" + respose);
		
		// 实际结果与预期结果比较
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);

		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(respose);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取交易返回码=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resprefnumberResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat1 = pattern1.matcher(respose);
				boolean result1 = mat1.find();
				if (result1) {
					refNumber = mat1.group();
				}
			}

			
			// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，Cps数据库检查
			if (a1 && datadriven.get("comment").contains("正常流程")) {

				// 访问数据库，并得出查询结果
				String b3 = cpsDbCheck.exRefDBcheck(datadriven.get("externalRefNumber"), datadriven);

				String[] splitresult = b3.split(";");
				String idTxn = splitresult[0];

				//Mcs根据idTxn做数据检查
				if (idTxn != null && idTxn.length() > 5) {
					String merchantCode = datadriven.get("merchantId");
					Reporter.log("结算商户号：" + merchantCode + ",交易编号：" + idTxn);
					boolean mcs = mcsService
							.clearAndSettle(merchantCode, idTxn);
					// 结算明细表状态为2，结算指令执行成功，mcs清结算通过
					Reporter.log("MCS清结算验证通过,结算商户号：" + merchantCode + ",交易编号："
							+ idTxn, mcs);
				} else {
					Reporter.log("idTxn为空，不校验Cps和mcs");
				}
			}
			
			Reporter.end(datadriven.get("comment"));
		}
	}

	// 消费撤销- VTX
	@Test(dataProvider = "CnpPurchaseVTX", description = "CNP 消费撤销", timeOut = 200000)
	public void cnpVtx(Map<String, String> datadriven) {

		Reporter.start(datadriven.get("comment"));
		String vtxrefNumber;
		datadriven.put("txnType", "PUR");
		datadriven.put("orignalTxnType", "");
		String purrespose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));
		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(purrespose);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取交易返回码=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resprefnumberResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat1 = pattern1.matcher(purrespose);
				boolean result1 = mat1.find();
				if (result1) {
					vtxrefNumber = mat1.group();
					datadriven.put("txnType", "VTX");
					datadriven.put("orignalTxnType", "PUR");
					MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
					mgwItem.setRefNumber(vtxrefNumber);

					// 获取 refnumber 及验证码后提交
					String reString = httpContentService.cnpContent(mgwItem);
					Reporter.log(reString);
					boolean checkresult = reString.contains(datadriven.get("exceptedmessage"));
					Reporter.log("消费撤销结果", checkresult);

					// 待完成
					Reporter.log("respose=" + reString);
					// 实际结果与预期结果比较
					boolean a1 = reString.contains(datadriven.get("exceptedmessage"));
					Reporter.log(datadriven.get("comment"), a1);
					if (a1 && datadriven.get("comment").equalsIgnoreCase("PUR撤销正常流程"));

					String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));

					String[] splitresult = b3.split(";");
					String txn_flg = splitresult[0];
					String txn_type = splitresult[1];

					if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
						Reporter.TRUE("RRRRR数据库txn_flg检查正常");
					} else {
						Reporter.FALSE("NNNNN数据库txn_flg检查异常");
					}
					if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
						Reporter.TRUE("RRRRR数据库txn_type检查正常");
					} else {
						Reporter.FALSE("NNNNN数据库txn_type检查异常");
					}
				}
			}
			else{
				Reporter.FALSE("消费撤销异常");
			}
		}
	}

	// 分期- INP
	@Test(dataProvider = "CnpPurchaseINP", description = "CNP 分期", timeOut = 200000)
	public void cnpInp(Map<String, String> datadriven) {

		Reporter.start(datadriven.get("comment"));
		// http 发送报文
		String respose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));

		// 待完成
		Reporter.log("respose=" + respose);
		// 实际结果与预期结果比较
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);

		if (a1 && datadriven.get("comment").equalsIgnoreCase("INP简单正常流程"));

		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(respose);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取交易返回码=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if (resprefnumberResult.equalsIgnoreCase("00")) {
				// 访问数据库，并得出查询结果
				String b3 = cpsDbCheck.exRefDBcheck(datadriven.get("externalRefNumber"), datadriven);

				String[] splitresult = b3.split(";");
				String idTxn = splitresult[0];

				//Mcs根据idTxn做数据检查
				if (idTxn != null && idTxn.length() > 5) {
					String merchantCode = datadriven.get("merchantId");
					Reporter.log("结算商户号：" + merchantCode + ",交易编号：" + idTxn);
					boolean mcs = mcsService
							.clearAndSettle(merchantCode, idTxn);
					// 结算明细表状态为2，结算指令执行成功，mcs清结算通过
					Reporter.log("MCS清结算验证通过,结算商户号：" + merchantCode + ",交易编号："
							+ idTxn, mcs);
				} else {
					Reporter.log("idTxn为空，不校验Cps和mcs");
				}

//				String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));
//
//				String[] splitresult = b3.split(";");
//				String txn_flg = splitresult[0];
//				String txn_type = splitresult[1];
//				// String app_type=splitresult[2];
//				if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
//					Reporter.TRUE("RRRRR数据库txn_flg检查正常");
//				} else {
//					Reporter.FALSE("NNNNN数据库txn_flg检查异常");
//				}
//				if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
//					Reporter.TRUE("RRRRR数据库txn_type检查正常");
//				} else {
//					Reporter.FALSE("NNNNN数据库txn_type检查异常");
//				}
			}
		}

	}

	// 预售权- PRE
	@Test(dataProvider = "CnpPurchasePRE", description = "CNP 预授权", timeOut = 100000)
	public void cnpPre(Map<String, String> datadriven) {

		Reporter.start(datadriven.get("comment"));
		// http 发送报文
		String respose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));
		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(respose);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取授权码 结果=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resprefnumberResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat1 = pattern1.matcher(respose);
				boolean result1 = mat1.find();
				if (result1) {
					refNumber = mat1.group();

					// refNumber = refnumber;
				}
			}
		}
		// 待完成
		Reporter.log("respose=" + respose);
		// 实际结果与预期结果比较
		boolean a1 = respose.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);
		
		// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，Cps数据库检查
		if (a1 && datadriven.get("comment").contains("正常流程")) {

			// 访问数据库，并得出查询结果
			String b3 = cpsDbCheck.exRefDBcheck(datadriven.get("externalRefNumber"), datadriven);

			String[] splitresult = b3.split(";");
			String idTxn = splitresult[0];

			//Mcs根据idTxn做数据检查
			if (idTxn != null && idTxn.length() > 5) {
				String merchantCode = datadriven.get("merchantId");
				Reporter.log("结算商户号：" + merchantCode + ",交易编号：" + idTxn);
				boolean mcs = mcsService
						.preClear(idTxn);
				// 结算明细表状态为2，结算指令执行成功，mcs清结算通过
				Reporter.log("MCS清结算验证通过,结算商户号：" + merchantCode + ",交易编号："
						+ idTxn, mcs);
			} else {
				Reporter.log("idTxn为空，不校验Cps和mcs");
			}
		}

//		if (a1 && datadriven.get("comment").equalsIgnoreCase("PRE简单正常流程"))
//			;
//
//		String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));
//
//		String[] splitresult = b3.split(";");
//		String txn_flg = splitresult[0];
//		String txn_type = splitresult[1];
//
//		if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
//			Reporter.TRUE("RRRRR数据库txn_flg检查正常");
//		} else {
//			Reporter.FALSE("NNNNN数据库txn_flg检查异常");
//		}
//		if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
//			Reporter.TRUE("RRRRR数据库txn_type检查正常");
//		} else {
//			Reporter.FALSE("NNNNN数据库txn_type检查异常");
//		}
	}

	// }

	// 预售权撤销- VTX
	@Test(dataProvider = "CnpPurchasePREVTX", description = "CNP 预授权撤销", timeOut = 100000)
	public void cnpPreVtx(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));
		String prerefNumber;
		datadriven.put("txnType", "PRE");
		datadriven.put("orignalTxnType", "");
		String prerespose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));
		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(prerespose);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取授权码 结果=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resprefnumberResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat1 = pattern1.matcher(prerespose);
				boolean result1 = mat1.find();
				if (result1) {
					prerefNumber = mat1.group();

					// refNumber = refnumber;

					datadriven.put("txnType", "VTX");
					datadriven.put("orignalTxnType", "PRE");

					MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);

					mgwItem.setRefNumber(prerefNumber);

					// 获取 refnumber 及验证码后提交
					String reString = httpContentService.cnpContent(mgwItem);

					Reporter.log(reString);

					boolean checkresult = reString.contains(datadriven.get("exceptedmessage"));

					Reporter.log("预售权撤销结果", checkresult);

					// 待完成
					Reporter.log("respose=" + reString);
					// 实际结果与预期结果比较
					boolean a1 = reString.contains(datadriven.get("exceptedmessage"));

					Reporter.log(datadriven.get("comment"), a1);

					if (a1 && datadriven.get("comment").equalsIgnoreCase("PRE撤销正常流程"))
						;

					String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));

					String[] splitresult = b3.split(";");
					String txn_flg = splitresult[0];
					String txn_type = splitresult[1];

					if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
						Reporter.TRUE("RRRRR数据库txn_flg检查正常");
					} else {
						Reporter.FALSE("NNNNN数据库txn_flg检查异常");
					}
					if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
						Reporter.TRUE("RRRRR数据库txn_type检查正常");
					} else {
						Reporter.FALSE("NNNNN数据库txn_type检查异常");
					}
				}
			}else{
				Reporter.FALSE("预售权撤销异常");
			}
		}
	}

	// 预售权完成- CFM
	@Test(dataProvider = "CnpPurchaseCFM", description = "CNP 预授权完成", timeOut = 200000)
	public void cnpCfm(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));
		// 先做预售权
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		mgwItem.setTxnType("PRE");
		mgwItem.setOrignalTxnType("");
		mgwItem.setProdInfo("0");
		mgwItem.setAmount(datadriven.get("preAmount"));
		// 获取respose
		String respose = httpContentService.cnpContent(mgwItem);
		// 解析 授权码
		String authorizationcode = "";
		String resptokenResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(respose);
		boolean result = mat.find();
		if (result) {
			resptokenResult = mat.group();
			Reporter.log("获取授权码 结果=" + resptokenResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resptokenResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<authorizationCode>)(.*?)(?=</authorizationCode>)");
				Matcher mat1 = pattern1.matcher(respose);
				boolean result1 = mat1.find();
				if (result1) {
					authorizationcode = mat1.group();
					Reporter.log("authorizationcode=" + authorizationcode);
				}
			}else{
				Reporter.FALSE("预售权交易异常,程序跳出,不继续做预授权完成操作");
			}

			String authorizationCode1 = "";

			authorizationCode1 = authorizationcode;

			mgwItem.setTxnType("CFM");
			mgwItem.setOrignalTxnType("PRE");
			mgwItem.setProdInfo("0");
			mgwItem.setAmount(datadriven.get("amount"));

			mgwItem.setAuthorizationCode(authorizationCode1);

		}

		// 获取 authorizationCode 及验证码后提交
		String reString = httpContentService.cnpContent(mgwItem);

		String resptokenResult1 = "";
		Pattern pattern2 = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat2 = pattern2.matcher(reString);
		boolean result2 = mat2.find();
		if (result2) {
			resptokenResult1 = mat2.group();
			Reporter.log("预售权完成 结果=" + resptokenResult1);
			if ("00".equals(resptokenResult1)) {
				Pattern pattern3 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat3 = pattern3.matcher(reString);
				boolean result3 = mat3.find();
				if (result3) {
					cfmrefNumber = mat3.group();
					Reporter.log("refNumber=" + refNumber);
				}
			}
		}

		Reporter.log(reString);

		boolean checkresult = reString.contains(datadriven.get("exceptedmessage"));

		Reporter.log("预售权完成结果", checkresult);

		// 待完成
		Reporter.log("respose=" + reString);
		// 实际结果与预期结果比较
		boolean a1 = reString.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);

		if (a1 && datadriven.get("comment").equalsIgnoreCase("CFM简单正常流程"));

		// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，Cps数据库检查
		if (a1 && datadriven.get("comment").contains("正常流程")) {

			// 访问数据库，并得出查询结果
			String b3 = cpsDbCheck.exRefDBcheck(
					datadriven.get("externalRefNumber"), datadriven);

			String[] splitresult = b3.split(";");
			String idTxn = splitresult[0];

			// Mcs根据idTxn做数据检查
			if (idTxn != null && idTxn.length() > 5) {
				String merchantCode = datadriven.get("merchantId");
				Reporter.log("结算商户号：" + merchantCode + ",交易编号：" + idTxn);
				boolean mcs = mcsService.clearAndSettle(merchantCode, idTxn);
				// 结算明细表状态为2，结算指令执行成功，mcs清结算通过
				Reporter.log("MCS清结算验证通过,结算商户号：" + merchantCode + ",交易编号："
						+ idTxn, mcs);
			} else {
				Reporter.log("idTxn为空，不校验Cps和mcs");
			}
		}
//		String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));
//
//		String[] splitresult = b3.split(";");
//		String txn_flg = splitresult[0];
//		String txn_type = splitresult[1];
//
//		if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
//			Reporter.TRUE("RRRRR数据库txn_flg检查正常");
//		} else {
//			Reporter.FALSE("NNNNN数据库txn_flg检查异常");
//		}
//		if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
//			Reporter.TRUE("RRRRR数据库txn_type检查正常");
//		} else {
//			Reporter.FALSE("NNNNN数据库txn_type检查异常");
//		}
	}

	// 预售权完成撤销- VTX
	@Test(dataProvider = "CnpPurchaseCFMVTX", description = "CNP 预授权完成撤销", dependsOnMethods = "cnpCfm", timeOut = 100000)
	public void cnpCfmVtx(Map<String, String> datadriven) {

		Reporter.start(datadriven.get("comment"));

		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);

		mgwItem.setRefNumber(cfmrefNumber);

		// 获取 refnumber 及验证码后提交
		String reString = httpContentService.cnpContent(mgwItem);

		Reporter.log(reString);

		boolean checkresult = reString.contains(datadriven.get("exceptedmessage"));

		Reporter.log("预售权完成撤销结果", checkresult);

		// 待完成
		Reporter.log("respose=" + reString);
		// 实际结果与预期结果比较
		boolean a1 = reString.contains(datadriven.get("exceptedmessage"));

		Reporter.log(datadriven.get("comment"), a1);

		if (a1 && datadriven.get("comment").equalsIgnoreCase("CFM撤销正常流程"));

		String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));

		String[] splitresult = b3.split(";");
		String txn_flg = splitresult[0];
		String txn_type = splitresult[1];

		if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
			Reporter.TRUE("RRRRR数据库txn_flg检查正常");
		} else {
			Reporter.FALSE("NNNNN数据库txn_flg检查异常");
		}
		if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
			Reporter.TRUE("RRRRR数据库txn_type检查正常");
		} else {
			Reporter.FALSE("NNNNN数据库txn_type检查异常");
		}

	}

	// 消费未结帐退货
	@Test(dataProvider = "CnpPurchaseNoCheckoutRFD", description = "CNP 消费未结账退货", timeOut = 200000)
	public void cnpnocheckoutRfd(Map<String, String> datadriven) {

		Reporter.start(datadriven.get("comment"));

		String rfdrefNumber;
		datadriven.put("txnType", "PUR");
		datadriven.put("orignalTxnType", "");
		String purrespose = httpContentService.cnpContent(converMgwDataDriverService.ConverData(datadriven));
		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(purrespose);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取交易返回码=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resprefnumberResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat1 = pattern1.matcher(purrespose);
				boolean result1 = mat1.find();
				if (result1) {
					rfdrefNumber = mat1.group();
					
					MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
					
					mgwItem.setTxnType("RFD");
					mgwItem.setOrignalTxnType("PUR");
					mgwItem.setAmount(datadriven.get("rfd_amount"));
					mgwItem.setOrigRefNumber(rfdrefNumber);

					// 获取 refnumber 及验证码后提交
					String reString = httpContentService.cnpContent(mgwItem);

					Reporter.log(reString);

					boolean checkresult = reString.contains(datadriven.get("exceptedmessage"));

					Reporter.log("未结帐消费退货结果", checkresult);

					// 待完成
					Reporter.log("respose=" + reString);

					boolean a1 = reString.contains(datadriven.get("exceptedmessage"));

					Reporter.log(datadriven.get("comment"), a1);

					if (a1 && datadriven.get("comment").equalsIgnoreCase("PUR退货正常流程"))
						;

					String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));

					String[] splitresult = b3.split(";");
					String txn_flg = splitresult[0];
					String txn_type = splitresult[1];
					System.out.println(txn_type);
					if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
						Reporter.TRUE("RRRRR数据库txn_flg检查正常");
					} else {
						Reporter.FALSE("NNNNN数据库txn_flg检查异常");
					}
					if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
						Reporter.TRUE("RRRRR数据库txn_type检查正常");
					} else {
						Reporter.FALSE("NNNNN数据库txn_type检查异常");
					}
				}
			}else{
				Reporter.FALSE("消费交易异常,跳出消费未结帐退货操作");
			}
		}
	}

	// 消费已结帐退货
	@Test(dataProvider = "CnpPurchaseCheckoutRFD", description = "CNP 消费已结账退货", timeOut = 100000)
	public void cnpcheckoutRfd(Map<String, String> datadriven) {

		Reporter.start(datadriven.get("comment"));

		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);

		mgwItem.setTxnType("PUR");
		mgwItem.setOrignalTxnType("");
		mgwItem.setAmount(datadriven.get("purAmount"));

		String reString = httpContentService.cnpContent(mgwItem);
		Reporter.log(reString);
		String resprefnumberResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(reString);
		boolean result = mat.find();
		if (result) {
			resprefnumberResult = mat.group();
			Reporter.log("获取交易返回码=" + resprefnumberResult);
			// 若状态结果 为 “00” ，获取authorizationcode 值
			if ("00".equals(resprefnumberResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<refNumber>)(.*?)(?=</refNumber>)");
				Matcher mat1 = pattern1.matcher(reString);
				boolean result1 = mat1.find();
				if (result1) {
					refNumber = mat1.group();
				}
			}else{
				Reporter.FALSE("消费交易异常,跳出消费已结帐退货操作");
			}

			mgwItem.setOrigRefNumber(refNumber);
			mgwItem.setTxnType("RFD");
			mgwItem.setOrignalTxnType("PUR");
			mgwItem.setAmount(datadriven.get("amount"));

			boolean b1 = accessDB.updateTermReconFlg(refNumber);

			if (b1) {

				MgwItem mgwItem1 = converMgwDataDriverService.ConverData(datadriven);

				mgwItem1.setOrigRefNumber(refNumber);

				// 获取 refnumber 及验证码后提交
				String reString1 = httpContentService.cnpContent(mgwItem);

				Reporter.log(reString1);

				boolean checkresult1 = reString1.contains(datadriven.get("exceptedmessage"));

				Reporter.log("已结帐消费退货结果", checkresult1);

				// 待完成
				Reporter.log("respose=" + reString1);
				// 实际结果与预期结果比较
				boolean a1 = reString1.contains(datadriven.get("exceptedmessage"));

				Reporter.log(datadriven.get("comment"), a1);

				if (a1 && datadriven.get("comment").equalsIgnoreCase("PUR退货正常流程"))
					;

				String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));

				String[] splitresult = b3.split(";");
				String txn_flg = splitresult[0];
				String txn_type = splitresult[1];

				if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
					Reporter.TRUE("RRRRR数据库txn_flg检查正常");
				} else {
					Reporter.FALSE("NNNNN数据库txn_flg检查异常");
				}
				if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
					Reporter.TRUE("RRRRR数据库txn_type检查正常");
				} else {
					Reporter.FALSE("NNNNN数据库txn_type检查异常");
				}
			}
		}
	}

	@DataProvider(name = "CnpPurchasePUR")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchasePUR");
	}

	@DataProvider(name = "CnpPurchaseVTX")
	public Iterator<Object[]> data5test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseVTX");
	}

	@DataProvider(name = "CnpPurchaseINP")
	public Iterator<Object[]> data6test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseINP");
	}

	@DataProvider(name = "CnpPurchasePRE")
	public Iterator<Object[]> data7test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchasePRE");
	}

	@DataProvider(name = "CnpPurchasePREVTX")
	public Iterator<Object[]> data8test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchasePREVTX");
	}

	@DataProvider(name = "CnpPurchaseCFM")
	public Iterator<Object[]> data9test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseCFM");
	}

	@DataProvider(name = "CnpPurchaseCFMVTX")
	public Iterator<Object[]> data10test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseCFMVTX");
	}

	@DataProvider(name = "CnpPurchaseNoCheckoutRFD")
	public Iterator<Object[]> data11test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseNoCheckoutRFD");
	}

	@DataProvider(name = "CnpPurchaseCheckoutRFD")
	public Iterator<Object[]> data12test() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseCheckoutRFD");
	}

	@AfterClass
	public void afterClass() {
	}

}
