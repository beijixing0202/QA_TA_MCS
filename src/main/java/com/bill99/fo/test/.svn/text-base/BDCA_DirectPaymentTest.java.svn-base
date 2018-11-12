package com.bill99.fo.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shelper.environment.Environment;
import shelper.webdriver.GetDriver;

import com.bill99.fo.common.dto.DirectedPaymentData;
import com.bill99.fo.orm.SeashellDB;
import com.bill99.fo.service.DirectedPaymentCashService;
import com.bill99.fo.service.InfsBalanceService;
import com.bill99.fo.service.LoginService;
import com.bill99.fo.service.NetBankRemitService;
import com.bill99.qa.framework.testcase.BaseTestCase;



/**
 * @Title:FO 定向付款
 * @Description:FO 1.07
 * @author lulu.yang
 * @Since:2017年04月18日
 * @Version:0.0.1
 */
public class BDCA_DirectPaymentTest extends BaseTestCase{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private DirectedPaymentCashService directedPaymentCashService;
	
	
	@Autowired
	private NetBankRemitService netBankRemitService;
	
	@Autowired
	private InfsBalanceService infsBalanceService;
	
	@Autowired
	private SeashellDB seashellDB;
	
	private WebDriver dr;
	private WebDriver fscDr;
	
	@BeforeClass
	public void before() {
		dr = GetDriver.get("ie8");
		fscDr = GetDriver.get("ie8");
	}
	
	@AfterClass
	public void after() {
		if (dr != null)
			dr.quit();
		if (fscDr != null)
			fscDr.quit();
		try {
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(description = "定向付款", dataProvider = "getData",timeOut=3600000)
	public void directPaymentTest(Map<String, String> datadriven)
			throws Exception {
		Reporter.start("--------------------开始---NO." + datadriven.get("编号")
				+ "-" + datadriven.get("描述") + "--------------------");
		
		DirectedPaymentData directedPaymentData = new DirectedPaymentData();
		BeanUtils.copyProperties(directedPaymentData, datadriven);
		
		//做交易之前先充值防止余额不足
		//infsBalanceService.chargeMoneyRmb(datadriven.get("memberCode"), datadriven.get("chargeAmount"));
		
		//登陆门户网站
		boolean flag;
		flag = loginService.logon(dr, Environment.get("bill99.loginName"), Environment.get("bill99.loginPwd"));
		Reporter.log("门户网站登录", flag);
		
		//定向付款
		String orderSeqId = directedPaymentCashService.processRequest(dr, directedPaymentData);
		Reporter.log("orderSeqId::"+orderSeqId);
		Thread.sleep(2000);
		
		//查看工单表状态是否是2
		String status;
		status = seashellDB.qryStatusFrmWthdrwWrkrOrder(orderSeqId);
		if("2".equals(status)){
			Reporter.log("风控审核通过", true);
		}else{
			Reporter.log("风控审核失败", false);
		}
		
		//PE记账第一步
		int count1 = seashellDB.qryCuntFrmPymentorder(orderSeqId, datadriven.get("orderAmount"), "101", datadriven.get("orderCode"));
		int count2 = seashellDB.qryCuntFrmDeal(orderSeqId, datadriven.get("dealAmount"), "1", datadriven.get("dealCode1"));
		int count3 = seashellDB.qryCuntFrmDeal(orderSeqId, datadriven.get("dealAmount"), "0", datadriven.get("dealCode2"));
		String dealId = seashellDB.qryDealIdFrmDeal(orderSeqId, datadriven.get("dealCode1"));
		int count4 = seashellDB.qryCuntFrmEntry(dealId, datadriven.get("acctCode1"), datadriven.get("orderAmount"), "1");
		int count5 = seashellDB.qryCuntFrmEntry(dealId, datadriven.get("acctCode2"), datadriven.get("dealAmount"), "2");
		int count6 = seashellDB.qryCuntFrmEntry(dealId, datadriven.get("acctCode3"), datadriven.get("feeAmount"), "2");
		if(1==count1&&1==count2&&1==count3&&1==count4&&1==count5&&1==count6){
			Reporter.log("定向付款第一步记账", true);
		}else{
			Reporter.log("定向付款第一步记账", false);
		}
		count1=0;count2=0;count3=0;count4=0;count5=0;count6=0;dealId="";
		//登陆清算平台
		boolean flag1 = netBankRemitService.fscLogin(fscDr, Environment.get("fsc.loginName"), Environment.get("fsc.loginPwd"));
		Reporter.log("登陆清算平台", flag1);
		
		//手工出批次
		String batchId = netBankRemitService.doBatchId(fscDr, orderSeqId, 0);
		Reporter.log("batchId::"+batchId);
		
		//复核出款+导入出款结果
		netBankRemitService.doRemit(fscDr, batchId, datadriven.get("bankName"), datadriven.get("checkNum"), datadriven.get("checkAmount"));
		
		Thread.sleep(60000);
		//PE记账检查--第二部记账
		count1 = seashellDB.qryCuntFrmPymentorder(orderSeqId, datadriven.get("orderAmount"), "111", datadriven.get("orderCode"));
		count2 = seashellDB.qryCuntFrmDeal(orderSeqId, datadriven.get("dealAmount"), "1", datadriven.get("dealCode2"));
		dealId = seashellDB.qryDealIdFrmDeal(orderSeqId, datadriven.get("dealCode2"));
		count4 = seashellDB.qryCuntFrmEntry(dealId, datadriven.get("acctCode2"), datadriven.get("dealAmount"), "1");
		count5 = seashellDB.qryCuntFrmEntry(dealId, datadriven.get("acctCode3"), datadriven.get("feeAmount"), "1");
		count6 = seashellDB.qryCuntFrmEntry(dealId, datadriven.get("acctCode4"), datadriven.get("dealAmount"), "2");
		int count7 = seashellDB.qryCuntFrmEntry(dealId, datadriven.get("acctCode5"), datadriven.get("feeAmount"), "2");
		if(1==count1&&1==count2&&1==count4&&1==count5&&1==count6&&1==count7){
			Reporter.log("定向付款第二步记账", true);
		}else{
			Reporter.log("定向付款第二步记账", false);
		}
		Reporter.end("--------------------结束---NO." + datadriven.get("编号")
				+ "-" + datadriven.get("描述") + "--------------------");
		
	}	
	@DataProvider(parallel = false)
	public Iterator<Object[]> getData(Method method) {
		return ExcelProviderByEnv(this, method.getName());
	}

}
