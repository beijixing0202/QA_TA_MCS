package com.bill99.cps.test.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shelper.common.MyDate;

import com.bill99.mcs.service.McsService;
import com.bill99.qa.framework.testcase.BaseTestCase;
//import com.bill99.qa.ta.product.db.service.ProductDbService;
import com.bill99.cps.access.http.HttpInternalPost;
//import com.bill99.ta.internal.common.utils.PospCheckout;
import com.bill99.cps.service.HttpInternalContentService;
import com.bill99.cps.service.MockConverInternalDataDriverService;
import com.bill99.cps.service.check.CpsDbCheck;

/**
 * internal发CNP交易测试类
 * author chongpeng.yan
 */

public class BDCA_MockInternalCnp extends BaseTestCase{
	@Autowired
	private MockConverInternalDataDriverService mockConverInternalDataDriverService;
	
	@Autowired
	@Qualifier("getDynCodeService")
	private HttpInternalContentService getDynCodeService;
	
	@Autowired
	@Qualifier("httpInternalContentService")
	private HttpInternalContentService httpInternalContentService;
	
	@Resource
	private CpsDbCheck cpsDbCheck;
	@Resource
	private McsService mcsService;
	
//	@Resource
//	ProductDbService productDbService;
	
	private String purPostRes="";//消费返回报文、消费撤销公用
	//private String prePostRes="";//预授权返回报文、预授权撤销公用
	private String precfmPostRes="";//预授权完成返回报文、预授权完成公用
	//private String inpPostRes="";//分期返回报文、分期撤销公用
	private String customerId="";//二次鉴权时用首次鉴权生成的cusomerId
	
	// 消费
	@Test(dataProvider = "CnpPurchasePUR", description = "CNP 消费" )//, priority = 1
	public void CnpPurchasePUR(Map<String, String> datadriven) 
			throws InterruptedException{
		Reporter.start(datadriven.get("comment"));
		/*String testttt=datadriven.get("expected_ismycat");
		System.out.println(testttt);
		
		Reporter.log("sktest",true);
		*/
		// http 发送报文，获取返回报文
		purPostRes = httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("pur交易返回：" + purPostRes);
		Reporter.log("预期返回码："+datadriven.get("expected_respcode"));
		Reporter.log("消费返回成功", purPostRes.contains("responseCode=[00]"));
		
		//map获取返回string中各个参数值
		Map<String, String> purResMap = HttpInternalPost.tranStringtoMap(purPostRes);
		//获取到消费交易编号RRN，更新到数据驱动,去除首位0
		String purRRN = purResMap.get("RRN").replaceFirst("^0*", "");
		//为oracle机组交易，查oracle库，等待3秒数据入库
		Thread.sleep(3000);
		datadriven.put("id_txn", purRRN);
		cpsDbCheck.cnpDbcheck(datadriven);

		Reporter.log("返回码数据库检查[00]："+purRRN);
		
		Reporter.end("CNP消费交易测试通过");
	}
	
	
	@Test(dataProvider = "CnpPurchaseVTX", description ="CNP 撤销", dependsOnMethods = "CnpPurchasePUR" )//,  priority = 2
	public void CnpPurchaseVTX(Map<String,String> datadriven)
			throws InterruptedException{
		Reporter.start(datadriven.get("comment"));
		System.out.println(purPostRes);
		//map获取返回string中各个参数值
				Map<String, String> purResMap = HttpInternalPost.tranStringtoMap(purPostRes);
				//获取到消费交易编号RRN，更新到数据驱动
				String purRRN = purResMap.get("RRN").replaceFirst("^0*", "");
		
		//撤销返回string报文初始化
		String vtxPostRes = "";
		Reporter.log(purRRN,(purRRN!=null));
		//交易编号赋值给originalRRN
		datadriven.put("originalRRN",purRRN);
		//http发送撤销交易报文，获取返回报文
		vtxPostRes = httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("vtx交易返回：" + vtxPostRes);
		Reporter.log("预期返回码："+datadriven.get("expected_respcode"));
		Reporter.log("撤销返回成功",vtxPostRes.contains("responseCode=[00]"));

		// 为oracle机组交易，查oracle库，等待3秒数据入库
//		Thread.sleep(3000);
//		if (datadriven.get("expected_ismycat").equals("0")) {
//			productDbService.initDbInf("oracle", "vposdgl");
//		} else {
//			productDbService.initDbInf("mysql", "ma-mas");
//		}
		// 根据需要选择合适的查询方法，所有时间参数均以秒为单位
		Reporter.log("返回码数据库检查[00]："+ purRRN);
		
		Reporter.end("CNP消费撤销交易测试通过");
	}
	
	@Test(dataProvider = "CnpPurchaseRFD", description = "CNP 退货" )//,dependsOnMethods = "CnpPurchaseVTX")//,  priority = 3
	public void CnpPurchaseRFD(Map<String, String> datadriven)
			throws InterruptedException {
		Reporter.start(datadriven.get("comment"));
		//消费、退货返回string报文初始化
		String purForRfd="";
		String rfdPostRes="";
		
		//先发送一笔消费
		purForRfd= httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("退货前置消费交易返回：" + purForRfd);
		Reporter.log("退货前置消费成功",purForRfd.contains("responseCode=[00]"));
		//map获取返回string中各个参数值
		Map<String, String> PurForRfdMap = HttpInternalPost.tranStringtoMap(purForRfd);
		//获取原交易RRN
		String purRRN = PurForRfdMap.get("RRN").replaceFirst("^0*", "");
		//若为oracle机组则，调用posp做终端结账操作，mycat商户配置为自动结账----现在数据驱动配了专门退货商户，此段批结逻辑暂不使用
		/*if (datadriven.get("expected_ismycat").equals("0")) {

			PospCheckout posppos = new PospCheckout();
			try {
				Thread.sleep(3000);
				posppos.Checkout(PurForRfdMap.get("merchantId"),
						PurForRfdMap.get("terminalId"));
				Reporter.log("执行posp批结");

			} catch (Exception e) {
				Reporter.log("Posp发送异常！");
				e.printStackTrace();
			}
		}*/
			Reporter.log("消费forrfd交易编号："+purRRN);
			//查询结账是否成功
//			Thread.sleep(5000);
//			if (datadriven.get("expected_ismycat").equals("0")) {
//				productDbService.initDbInf("oracle", "vposdgl");
//			} else {
//				productDbService.initDbInf("mysql", "ma-mas");
//			}
			// 根据需要选择合适的查询方法，所有时间参数均以秒为单位
//			Reporter.log("结账是否成功：", productDbService.queryCheckInTime(
//					"select TERM_RECON_FLG from maspos.t_txn_ctrl where id_txn =" + purRRN
//							+ " and TXN_TYPE=00200", "C", 20, 1));
		
			
		//消费交易编号RRN，更新到数据驱动,交易类型变更为RFD
		datadriven.put("originalRRN",purRRN);
		datadriven.put("txnType","RFD");
		//使用同一InternalMockItem，需重新生成外部跟踪编号
		datadriven.put("serviceChannelTraceNo",
				MyDate.getUserDate("yyMMddhhmmss")+MyDate.getRandom(2));
		//发起退货，获取返回报文
		rfdPostRes = httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("rfd交易返回：" + rfdPostRes);
		Reporter.log("预期返回码："+datadriven.get("expected_respcode"));
		Reporter.log("退货返回成功",rfdPostRes.contains("responseCode=[00]"));
		//退货交易报文map获取返回string中各个参数值
		Map<String, String> rfdPostResMap = HttpInternalPost.tranStringtoMap(rfdPostRes);
		String rfdRRN = rfdPostResMap.get("RRN").replaceFirst("^0*", "");
		Reporter.log("退货交易编号："+rfdRRN);
		
		
		// 为oracle机组交易，查oracle库，等待3秒数据入库
//		Thread.sleep(3000);
//		if (datadriven.get("expected_ismycat").equals("0")) {
//			productDbService.initDbInf("oracle", "vposdgl");
//		} else {
//			productDbService.initDbInf("mysql", "ma-mas");
//		}
		// 根据需要选择合适的查询方法，所有时间参数均以秒为单位
//		Reporter.log("返回码数据库检查[00]：", productDbService.queryCheckInTime(
//				"select RESP_CODE from maspos.t_txn_ctrl where id_txn =" + rfdRRN
//						+ " and TXN_TYPE=00500", "00", 20, 1));
//		
		Reporter.end("CNP消费退货交易测试通过");
	}
	
	
	// 预授权
	@Test(dataProvider = "CnpPurchasePRE", description = "CNP 预授权")//,  priority = 4
	public void CnpPurchasePRE(Map<String, String> datadriven)
			throws InterruptedException {
		Reporter.start(datadriven.get("comment"));
		// http 发送报文，获取返回报文
		precfmPostRes = httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("pre交易返回：" + precfmPostRes);
		Reporter.log("预期返回码：" + datadriven.get("expected_respcode"));
		Reporter.log("预授权返回成功", precfmPostRes.contains("responseCode=[00]"));

		// map获取返回string中各个参数值
		Map<String, String> preCfmMap = HttpInternalPost.tranStringtoMap(precfmPostRes);
		// 获取到预授权交易编号RRN，更新到数据驱动,去除首位0
		String preRRN = preCfmMap.get("RRN").replaceFirst("^0*", "");
		Reporter.log("预授权交易编号："+preRRN);
		// 为oracle机组交易，查oracle库，等待3秒数据入库
//		Thread.sleep(3000);
//		if (datadriven.get("expected_ismycat").equals("0")) {
//			productDbService.initDbInf("oracle", "vposdgl");
//		} else {
//			productDbService.initDbInf("mysql", "ma-mas");
//		}
		// 根据需要选择合适的查询方法，所有时间参数均以秒为单位
//		Reporter.log("返回码数据库检查[00]：", productDbService.queryCheckInTime(
//				"select RESP_CODE from maspos.t_txn_ctrl where id_txn =" + preRRN
//						+ " and TXN_TYPE=00300", "00", 20, 1));
//		
		Reporter.end("CNP预授权交易测试通过");
	}
	
	// 预授权撤销--internal暂不支持
	/*Test(dataProvider = "CnpPurchasePREVTX", description = "CNP 预授权撤销", dependsOnMethods = "CnpPurchasePRE")
	public void CnpPurchasePREVTX(Map<String, String> datadriven)
			throws InterruptedException {
		Reporter.start(datadriven.get("comment"));
		//新建map获取预授权交易RRN
		System.out.println(prePostRes);
		// map获取返回string中各个参数值
		Map<String, String> preResMap = HttpInternalPost.tranStringtoMap(prePostRes);
		// 获取到预授权交易编号RRN，更新到数据驱动
		String RRN = preResMap.get("RRN");
		String authCode = preResMap.get("authCode");
		
		if (purPostRes.contains("responseCode=[00]")) {
			String prevtxPostRes="";//预授权撤销返回报文初始化
			Reporter.log(RRN, (RRN != null));
			// 交易编号赋值给originalRRN,授权码赋值给authCode
			datadriven.put("originalRRN", RRN);
			datadriven.put("authCode", authCode);
			// http发送撤销交易报文，获取返回报文
			prevtxPostRes = httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
			Reporter.log("prevtx交易返回：" + prevtxPostRes);
			Reporter.log("预期返回码：" + datadriven.get("expected_respcode"));
			Reporter.log("预授权撤销返回成功", prevtxPostRes.contains("responseCode=[00]"));
		} else {
			Reporter.log("原交易没有成功，请检查交易：" + RRN, false);
		}
		
	}*/
	
	@Test(dataProvider = "CnpPurchaseCFM", description = "CNP 预授权完成", dependsOnMethods = "CnpPurchasePRE")// ,  priority = 5
	public void CnpPurchaseCFM(Map<String, String> datadriven)
			throws InterruptedException {
		Reporter.start(datadriven.get("comment"));
		//新建map获取预授权交易RRN
		System.out.println(precfmPostRes);
		// map获取返回string中各个参数值
		Map<String, String> preResMap = HttpInternalPost.tranStringtoMap(precfmPostRes);
		// 获取到预授权交易编号RRN，更新到数据驱动
		String preRRN = preResMap.get("RRN").replaceFirst("^0*", "");
		String authCode = preResMap.get("authCode");
		
			Reporter.log(preRRN, (preRRN != null));
			// 交易编号赋值给originalRRN,授权码赋值给authCode
			datadriven.put("originalRRN", preRRN);
			datadriven.put("authCode", authCode);
			// http发送预授权完成交易报文，获取返回报文
			String cfmPostRes = httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
			Reporter.log("cfm交易返回：" + cfmPostRes);
			Reporter.log("预期返回码：" + datadriven.get("expected_respcode"));
			Reporter.log("预授权完成返回成功", cfmPostRes.contains("responseCode=[00]"));
			
		
		// map获取返回string中各个参数值
		Map<String, String> cfmMap = HttpInternalPost
				.tranStringtoMap(cfmPostRes);
		// 获取到预授权完成交易编号RRN，更新到数据驱动,去除首位0
		String cfmRRN = cfmMap.get("RRN").replaceFirst("^0*", "");
		Reporter.log("预授权完成交易编号："+cfmRRN);
		// 为oracle机组交易，查oracle库，等待3秒数据入库
//		Thread.sleep(3000);
//		if (datadriven.get("expected_ismycat").equals("0")) {
//			productDbService.initDbInf("oracle", "vposdgl");
//		} else {
//			productDbService.initDbInf("mysql", "ma-mas");
//		}
//		// 根据需要选择合适的查询方法，所有时间参数均以秒为单位
//		Reporter.log("返回码数据库检查[00]：", productDbService.queryCheckInTime(
//				"select RESP_CODE from maspos.t_txn_ctrl where id_txn =" + cfmRRN
//						+ " and TXN_TYPE=00400", "00", 20, 1));
//		
		// 预授权退货
		// 消费交易编号RRN，更新到数据驱动,交易类型变更为RFD
		datadriven.put("originalRRN", cfmRRN);
		datadriven.put("txnType", "RFD");
		// 使用同一InternalMockItem，需重新生成外部跟踪编号
		datadriven.put("serviceChannelTraceNo",
				MyDate.getUserDate("yyMMddhhmmss") + MyDate.getRandom(2));
		// 发起退货，获取返回报文
		String cfmRfdPostRes = httpInternalContentService
				.cnpContent(mockConverInternalDataDriverService
						.ConverData(datadriven));
		Reporter.log("cfmRfd交易返回：" + cfmRfdPostRes);
		Reporter.log("预授权退货返回成功", cfmRfdPostRes.contains("responseCode=[00]"));
		
		Reporter.end("CNP预授权完成交易测试通过");
	}
	
	@Test(dataProvider = "quickpayFirst", description="首次获取手机动态码鉴权-消费")
	public void quickpayFirst(Map<String, String> datadriven) throws InterruptedException{
		Reporter.start(datadriven.get("comment"));
		
		String getDynCodeRes= getDynCodeService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("获取手机验证码-首次：" + getDynCodeRes);
		//Reporter.log("预期返回码：" + datadriven.get("expected_respcode"));
		Reporter.log("获取验证码成功", getDynCodeRes.contains("RespCode=00"));
		Map<String, String> getDynCodeResMap = HttpInternalPost.getDynCodetoMap(getDynCodeRes);
		//获取返回报文中的token、validCode和customerId
		String token=getDynCodeResMap.get("Token");
		String validCode=getDynCodeResMap.get("ValidCode");
		String externalRefNumber=datadriven.get("serviceChannelTraceNo");//使用原serviceChannelTraceNo
		customerId=getDynCodeResMap.get("CustomerId");
		System.out.println("token:"+token+"\nvalidCode:"+validCode+"\nexternalRefNumber:"+externalRefNumber);
		datadriven.put("token", token);
		datadriven.put("validCode", validCode);
		datadriven.put("externalRefNumber", externalRefNumber);
		datadriven.put("customerId", customerId);
		datadriven.put("savePciFlag", "1");
		datadriven.put("payBatch", "1");
		
		String quickpayFirstRes=httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("首次鉴权消费返回报文:"+quickpayFirstRes);
		Reporter.log("首次鉴权消费成功", quickpayFirstRes.contains("responseCode=[00]"));
		//获取交易编号
		Map<String, String> quickpayFirstResMap = HttpInternalPost
				.tranStringtoMap(quickpayFirstRes);
		String quickpayFirstRRN = quickpayFirstResMap.get("RRN").replaceFirst("^0*", "");
		Reporter.log("首次鉴权消费交易编号："+quickpayFirstRRN);
		
		// 为oracle机组交易，查oracle库，等待3秒数据入库
//		Thread.sleep(3000);
//		if (datadriven.get("expected_ismycat").equals("0")) {
//			productDbService.initDbInf("oracle", "vposdgl");
//		} else {
//			productDbService.initDbInf("mysql", "ma-mas");
//		}
//		// 根据需要选择合适的查询方法，所有时间参数均以秒为单位
//		Reporter.log("返回码数据库检查[00]", productDbService
//				.queryCheckInTime(
//						"select RESP_CODE from maspos.t_txn_ctrl where id_txn ="
//								+ quickpayFirstRRN + " and TXN_TYPE=00200",
//						"00", 20, 1));
//		Reporter.log("应用类型数据库检查CPS_KJZF_PAY1", productDbService
//				.queryCheckInTime(
//						"select APP_TYPE from maspos.t_txn_ctrl where id_txn ="
//								+ quickpayFirstRRN + " and TXN_TYPE=00200",
//						"CPS_KJZF_PAY1", 20, 1));
//		
		//对原交易撤销或者退货
		datadriven.put("txnType", "VTX");
		datadriven.put("originalRRN",quickpayFirstRRN);
		datadriven.put("serviceChannelTraceNo",
				MyDate.getUserDate("yyMMddhhmmss")+MyDate.getRandom(2));
		String quickpayFirstVtx = httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("首次鉴权消费撤销成功",quickpayFirstVtx.contains("responseCode=[00]"));
		
		Reporter.end("首次鉴权消费测试通过");
	}
	
	@Test(dataProvider = "quickpaySecond", description="二次获取手机动态码鉴权-消费" , dependsOnMethods = "quickpayFirst")
	public void quickpaySecond(Map<String, String> datadriven) throws InterruptedException{
		Reporter.start(datadriven.get("comment"));
		datadriven.put("customerId", customerId);//将首次鉴权生成的customerId取过来
		String getDynCodeRes2= getDynCodeService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("获取手机验证码-二次：" + getDynCodeRes2);
		//Reporter.log("预期返回码：" + datadriven.get("expected_respcode"));
		Reporter.log("获取验证码成功", getDynCodeRes2.contains("RespCode=00"));
		Map<String, String> getDynCodeRes2Map = HttpInternalPost.getDynCodetoMap(getDynCodeRes2);
		//获取返回报文中的token、validCode和customerId
		String token=getDynCodeRes2Map.get("Token");
		String validCode=getDynCodeRes2Map.get("ValidCode");
		String externalRefNumber=datadriven.get("serviceChannelTraceNo");//使用原serviceChannelTraceNo
		customerId=getDynCodeRes2Map.get("CustomerId");
		System.out.println("token:"+token+"\nvalidCode:"+validCode+"\nexternalRefNumber:"+externalRefNumber);
		datadriven.put("token", token);
		datadriven.put("validCode", validCode);
		datadriven.put("externalRefNumber", externalRefNumber);
		datadriven.put("customerId", customerId);
		datadriven.put("savePciFlag", "1");
		datadriven.put("payBatch", "2");
		
		String quickpaySecondRes=httpInternalContentService.cnpContent(mockConverInternalDataDriverService.ConverData(datadriven));
		Reporter.log("二次鉴权消费返回报文:"+quickpaySecondRes);
		Reporter.log("二次鉴权消费成功", quickpaySecondRes.contains("responseCode=[00]"));
		//获取交易编号
		Map<String, String> quickpaySecondResMap = HttpInternalPost
				.tranStringtoMap(quickpaySecondRes);
		String quickpaySecondRRN = quickpaySecondResMap.get("RRN").replaceFirst("^0*", "");
		Reporter.log("二次鉴权消费交易编号："+quickpaySecondRRN);
		
		// 为oracle机组交易，查oracle库，等待3秒数据入库
//		Thread.sleep(3000);
//		if (datadriven.get("expected_ismycat").equals("0")) {
//			productDbService.initDbInf("oracle", "vposdgl");
//		} else {
//			productDbService.initDbInf("mysql", "ma-mas");
//		}
//		// 根据需要选择合适的查询方法，所有时间参数均以秒为单位
//		Reporter.log("返回码数据库检查[00]", productDbService
//				.queryCheckInTime(
//						"select RESP_CODE from maspos.t_txn_ctrl where id_txn ="
//								+ quickpaySecondRRN + " and TXN_TYPE=00200",
//						"00", 20, 1));
//		Reporter.log("应用类型数据库检查CPS_KJZF_PAY2", productDbService
//				.queryCheckInTime(
//						"select APP_TYPE from maspos.t_txn_ctrl where id_txn ="
//								+ quickpaySecondRRN + " and TXN_TYPE=00200",
//						"CPS_KJZF_PAY2", 20, 1));
		
		// 对原交易撤销或者退货
		datadriven.put("txnType", "VTX");
		datadriven.put("originalRRN", quickpaySecondRRN);
		datadriven.put("serviceChannelTraceNo",
				MyDate.getUserDate("yyMMddhhmmss") + MyDate.getRandom(2));
		String quickpayFirstVtx = httpInternalContentService
				.cnpContent(mockConverInternalDataDriverService
						.ConverData(datadriven));
		Reporter.log("二次鉴权消费撤销成功", quickpayFirstVtx.contains("responseCode=[00]"));

		Reporter.end("二次鉴权消费测试通过");
		
	}
	
	
	@DataProvider(name = "CnpPurchasePUR")
	public Iterator<Object[]> cnpPurtest() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchasePUR");
	}
	
	@DataProvider(name = "CnpPurchaseVTX")
	public Iterator<Object[]> cnpVtxtest() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseVTX");
	}
	
	@DataProvider(name = "CnpPurchaseRFD")
	public Iterator<Object[]> cnpRfdtest() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseRFD");
	}
	
	@DataProvider(name = "CnpPurchasePRE")
	public Iterator<Object[]> cnpPretest() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchasePRE");
	}
	
	/*@DataProvider(name = "CnpPurchasePREVTX")
	public Iterator<Object[]> cnpPreVtxtest() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchasePREVTX");
	}*/
	
	@DataProvider(name = "CnpPurchaseCFM")
	public Iterator<Object[]> cnpPreVtxtest() throws IOException {
		return ExcelProviderByEnv(this, "CnpPurchaseCFM");
	}
	
	@DataProvider(name = "quickpayFirst")
	public Iterator<Object[]> quickpayFirsttest() throws IOException {
		return ExcelProviderByEnv(this, "quickpayFirst");
	}
	
	@DataProvider(name = "quickpaySecond")
	public Iterator<Object[]> quickpaySecondtest() throws IOException {
		return ExcelProviderByEnv(this, "quickpaySecond");
	}
}
