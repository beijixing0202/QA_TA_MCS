package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import mas.channel.internal.client.WsMtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shelper.iffixture.HttpFixture;

import com.bill99.cps.common.dto.MgwItem;
import com.bill99.cps.orm.AccessDB;
import com.bill99.cps.service.ConverMgwDataDriverService;
import com.bill99.cps.service.DBOracleCheck;
import com.bill99.cps.service.HttpContentService;
import com.bill99.cps.service.InternalService;
import com.bill99.qa.framework.testcase.BaseTestCase;
import com.caucho.hessian.client.HessianProxyFactory;

/**MGW 快捷预授权 
 * @author kaiquan.jiang
 *
 */
public class MgwQuickPrePay extends BaseTestCase {
	@Autowired
	@Qualifier("getDynNumService")
	private HttpContentService getDynNumService;

	@Autowired
	@Qualifier("httpContentService")
	private HttpContentService httpContentService;

	@Autowired
	private ConverMgwDataDriverService converMgwDataDriverService;

	@Autowired
	private DBOracleCheck dbOracleCheck;
	@Autowired
	private AccessDB accessDB;
//	@Resource
//	private InternalService internalService;
	
	private String customerId;
	private String refNumber = "";
	private String cfmrefNumber = "";
	
	//快捷首次预授权
	@Test(dataProvider = "quickPayFirst" ,description="MGW 快捷首次预授权" ,timeOut=120000)
	public void quickPayFirst(Map<String, String> datadriven) {
		
		Reporter.start(datadriven.get("comment"));
		
		// 数据驱动内容转成 MgwItem 对象
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);
		
		// 生成手机动态码
		String reString = getDynNumService.cnpContent(mgwItem);
		Reporter.log(reString);

		// 解析 报文（待定 解析是否需要单独抽出？？？？？？）
		String token = "";
		String ref_no = mgwItem.getExternalRefNumber();
		String resptokenResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(reString);
		boolean result = mat.find();
		if (result) {
			resptokenResult = mat.group();
			Reporter.log("获取手机动态码 状态 结果=" + resptokenResult);
			
			// 若状态结果 为 “00” ，获取token 值
			if ("00".equals(resptokenResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<token>)(.*?)(?=</token>)");
				Pattern pattern3 = Pattern.compile("(?<=<customerId>)(.*?)(?=</customerId>)");
				Matcher mat1 = pattern1.matcher(reString);
				Matcher mat3 = pattern3.matcher(reString);
				boolean result1 = mat1.find();
				boolean result3 = mat3.find();
				if (result1) {
					token = mat1.group();
					Reporter.log("token=" + token);
				}
				if (result3) {
					customerId = mat3.group();
					Reporter.log("customerId=" + customerId);
				}
			}
		}
		
		// check resp是否为 00
		Reporter.log("获取手机动态码结果", "00".equals(resptokenResult));
		if (StringUtils.hasLength(token)) {

			// 从数据库获取手机验证码（这个验证码是否验证与mas系统配置的安全等级有关！）
			String validCode = dbOracleCheck.getValidCode(ref_no, mgwItem.getPhoneNO());
			
			mgwItem.setToken(token);
			mgwItem.setValidCode(validCode);
			mgwItem.setCardNo(datadriven.get("pan"));
		}

			// 获取 手机token 及验证码后提交
			String reString2 = httpContentService.cnpContent(mgwItem);
			Reporter.log("reString2=" + reString2);

			boolean checkresult = reString2.contains(datadriven.get("exceptedmessage"));

			Reporter.log("快捷首次预授权验证结果", checkresult);
			
		

			// 数据库检查，如果预期结果与实际结果一致 并且是正常流程，数据库检查
			if (checkresult && datadriven.get("comment").equalsIgnoreCase("quickPay(PUR)首次交易正常流程")) {

				// 访问数据库，并得出查询结果
				String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));
				
				 String[] splitresult = b3.split(";");
				 String txn_flg=splitresult[0];
//				 String txn_type=splitresult[1];
				 String app_type=splitresult[2];
				
				if (txn_flg.equalsIgnoreCase(datadriven.get("excepted_txn_flg"))) {
					Reporter.TRUE("RRRRR数据库txn_flg检查正常");
				} else {
					Reporter.FALSE("NNNNN数据库txn_flg检查异常");
				}
				/*if (txn_type.equalsIgnoreCase(datadriven.get("excepted_txn_type"))) {
					Reporter.log("RRRRR数据库txn_type检查正常");
				} else {
					Reporter.log("NNNNN数据库txn_type检查异常");
				}*/
				if (app_type.equalsIgnoreCase(datadriven.get("excepted_app_type"))) {
					Reporter.TRUE("RRRRR数据库app_type检查正常");
				} else {
					Reporter.FALSE("NNNNN数据库app_type检查异常");
				}
			}
		Reporter.end(datadriven.get("comment"));
		
	}

	//快捷二次预授权
	@Test(dataProvider = "quickPaySecond",description="MGW 快捷二次预授权", dependsOnMethods = "quickPayFirst" ,timeOut=120000)
	public void quickPaySecond(Map<String, String> datadriven) {
		Reporter.start(datadriven.get("comment"));

		/**
		 * 数据驱动内容转成 MgwItem 对象
		 */
		MgwItem mgwItem = converMgwDataDriverService.ConverData(datadriven);

		/**
		 * 生成手机动态码
		 */
		mgwItem.setCustomerId(customerId);
		String reString = getDynNumService.cnpContent(mgwItem);
		Reporter.log(reString);

		// 解析 报文（待定 解析是否需要单独抽出？？？？？？）
		String token = "";
		String ref_no = mgwItem.getExternalRefNumber();
		String resptokenResult = "";
		Pattern pattern = Pattern.compile("(?<=<responseCode>)(.*?)(?=</responseCode>)");
		Matcher mat = pattern.matcher(reString);
		boolean result = mat.find();
		if (result) {
			resptokenResult = mat.group();
			Reporter.log("获取手机动态码 状态 结果=" + resptokenResult);
			// 若状态结果 为 “00” ，获取token 值
			if ("00".equals(resptokenResult)) {
				Pattern pattern1 = Pattern.compile("(?<=<token>)(.*?)(?=</token>)");
				Pattern pattern2 = Pattern.compile("(?<=<externalRefNumber>)(.*?)(?=</externalRefNumber>)");
				Matcher mat1 = pattern1.matcher(reString);
				Matcher mat2 = pattern2.matcher(reString);
				boolean result1 = mat1.find();
				boolean result2 = mat2.find();
				if (result1) {
					token = mat1.group();
					Reporter.log("token=" + token);
				}
				if (result2) {
					ref_no = mat2.group();
					Reporter.log("externalRefNumber=" + ref_no);
				}
			}
		}
		/**
		 * check resp是否为 00
		 */
		Reporter.log("获取手机动态码结果", "00".equals(resptokenResult));
		if (StringUtils.hasLength(token)) {

			/**
			 * 从数据库获取手机验证码（这个验证码是否验证与mas系统配置的安全等级有关！）
			 */
			String validCode = dbOracleCheck.getValidCode(ref_no, mgwItem.getPhoneNO());
			mgwItem.setToken(token);
			mgwItem.setValidCode(validCode);
			mgwItem.setStorableCardNo(datadriven.get("storablePan"));
			/**
			 * 获取 手机token 及验证码后提交
			 */
			//
			String reString2 = httpContentService.cnpContent(mgwItem);
			System.out.println("reString2=" + reString2);

			boolean checkresult = reString2.contains(datadriven.get("exceptedmessage"));

			Reporter.log("快捷再次预授权验证结果", checkresult);

			/**
			 * 数据库检查，如果预期结果与实际结果一致 并且是正常流程，数据库检查
			 */

			if (checkresult && datadriven.get("comment").equalsIgnoreCase("quickPay(PUR)再次交易正常流程")) {

				/**
				 * 访问数据库，并得出查询结果
				 */
				String b3 = accessDB.purchaseDBcheck(datadriven.get("externalRefNumber"));
				
				 String[] splitresult = b3.split(";");
				 String txn_flg=splitresult[0];
				 String txn_type=splitresult[1];
				 String app_type=splitresult[2];
				
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
				if (app_type.equalsIgnoreCase(datadriven.get("excepted_app_type"))) {
					Reporter.TRUE("RRRRR数据库app_type检查正常");
				} else {
					Reporter.FALSE("NNNNN数据库app_type检查异常");
				}
			}
		}
		Reporter.end(datadriven.get("comment"));

	}
	
	
	
	// 预售权完成- CFM
		@Test(dataProvider = "quickPayCFM", description = "CNP 预授权完成", timeOut = 100000)
		public void quickPayCFM(Map<String, String> datadriven) {
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

			if (a1 && datadriven.get("comment").equalsIgnoreCase("CFM简单正常流程"))
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
	
	


	@DataProvider(name = "quickPayFirst")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "quickPayFirst");
	}

	@DataProvider(name = "quickPaySecond")
	public Iterator<Object[]> data5test() throws IOException {
		return ExcelProviderByEnv(this, "quickPaySecond");
	}
	
	@DataProvider(name = "quickPayCFM")
	public Iterator<Object[]> data9test() throws IOException {
		return ExcelProviderByEnv(this, "quickPayCFM");
	
	}
	
}
