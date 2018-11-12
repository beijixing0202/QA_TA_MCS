package com.bill99.fi.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shelper.iffixture.HttpFixture;

import com.bill99.fi.access.http.DoSubmit;
import com.bill99.fi.common.helper.ParameterDispose;
import com.bill99.fi.common.helper.ParameterSignMsg;
import com.bill99.fi.common.helper.ParameterSource;
import com.bill99.fi.orm.dao.LogDbCheck;
import com.bill99.fi.orm.mng.GatewayDbCheck;
import com.bill99.fi.service.gateway.GatewayPageSubmit;
import com.bill99.fi.service.payments.PaymentController;
import com.bill99.qa.framework.testcase.BaseTestCase;

/**
 * @Title:FI人民币网关网银支付
 * @Description:FI1.3
 * @author lulu.yang
 * @Since:2017年05月08日
 * @Version:0.0.1
 */
public class BDCA_DepositCardPay extends BaseTestCase {
	@Autowired
	private DoSubmit doSubmit;
	@Autowired
	private GatewayPageSubmit gatewayPageSubmit;

	@Autowired
	private PaymentController paymentController;
	@Autowired
	private ParameterSignMsg parameterSignMsg;
	@Autowired
	private GatewayDbCheck gatewayDbCheck;
	@Autowired
	private LogDbCheck logDbCheck;
	
     @Test(description="网关3.0储蓄卡网银支付",dataProvider="depositCardPay",timeOut=60000,enabled=true)
     public void depositCardPay(Map<String,String> data){
    	 Reporter.start("当前测试开始-------网关3.0储蓄卡网银支付");
    	// 添加一些字段的默认值
 		ParameterDispose.addDefaultValue(data);
 		if (data.get("signMsg").equals("")) {

 			data.put("signMsg", parameterSignMsg.SignMsg(
 					ParameterSource.gatewayParameter, data));
 		}

 	        // 网关提交
 			HttpFixture query = doSubmit.gatewaySubmit(data);
 			//选择支付方式
 			gatewayPageSubmit.selectPayType(query, data);
 			// 选择银行 
 			gatewayPageSubmit.selectBank(query, data);
 			// 模拟银行支付，及检查商户通知页面是否返回成功
 			boolean payResult = paymentController.payDemoBankOnce(query, data);
 	       // 如果支付成功，进行数据库检查 数据库和商户通知
 			if (payResult) {

 				Reporter.log(data.get("name")+ "数据库检查结果", gatewayDbCheck.checkGatewayDeal(data, 4) && logDbCheck.notifyLogDbCkeck(data));

 			} else {
 				Reporter.log(data.get("name"), payResult);
 			}

 			Reporter.end("当前测试--------：" + data.get("name") + "结束！");
 		
     }
     @BeforeClass
     public void beforeClass(){
    	 
     }
     @AfterClass
     public void afterClass(){
    	 
     }
     @DataProvider(name="depositCardPay")
     public Iterator<Object[]> data() throws IOException{
    	 return ExcelProviderByEnv(this, "depositCardPay");
    			  
    	 
     }
}
