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

import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.fi.access.http.DoSubmit;
import com.bill99.fi.common.helper.ParameterDispose;
import com.bill99.fi.common.helper.ParameterSignMsg;
import com.bill99.fi.common.helper.ParameterSource;
import com.bill99.fi.orm.dao.LogDbCheck;
import com.bill99.fi.orm.mng.GatewayDbCheck;
import com.bill99.fi.service.payments.PaymentController;
import com.bill99.fi.service.refund.MsgatewayRefund;

public class MsGatewayAdvanceRefund extends BaseTestCase {
	@Autowired
	private ParameterSignMsg parameterSignMsg;
	@Autowired
	private DoSubmit doSubmit;
	@Autowired
	private PaymentController paymentController;
	@Autowired
	private GatewayDbCheck gatewayDbCheck;
	@Autowired
	private LogDbCheck logDbCheck;
	@Autowired
	private MsgatewayRefund msgatewayRefund;
	
 @Test(description="分账网关垫付退款/垫付还款", dataProvider="msGatewayAdvanceRefund", timeOut=240000,enabled=true)
	 public void msGatewayAdvanceRefund(Map<String,String> data) throws Exception{
	 Reporter.start("当前测试------："+data.get("name")+"开始！");
	// 添加一些字段的默认值
	 	ParameterDispose.addDefaultValue(data);
			data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.msgatewayParameter, data));

	// 网关提交
		HttpFixture query = doSubmit.msGatewaySubmit(data);
	// 模拟银行支付，及检查商户通知页面是否返回成功
		boolean payResult = paymentController.payDemoBankOnce(query, data);
	// 如果支付成功，进行数据库检查 数据库和商户通知
		if (payResult) {				
			Reporter.log("分账支付订单数据库检查结果",
				gatewayDbCheck.checkGatewayDeal(data, 8) && logDbCheck.notifyLogDbCkeck(data) 
				&& gatewayDbCheck.checkGatewayorder(data, 1));
			//垫付退款申请
			data.put("ref_signMsg",  parameterSignMsg.SignMsg(ParameterSource.msgatewayRefundParameter, data));
			msgatewayRefund.refundRequest(data);
			
		 	// 添加一些字段的默认值---垫付还款
			Thread.sleep(1000);
	 		data.put("ref_returnDetail", null);
	 		data.put("ref_advanceFlag", "2");	 		
	 		data.put("ref_signMsg",null);
	 		 if (data.get("ref_signMsg")==null) {
	 		 		data.put("ref_signMsg", parameterSignMsg.SignMsg(ParameterSource.msgatewayRefundParameter,data));
	 		 	}
	 		 msgatewayRefund.refundRequestAndRiskAndFinance(data);
	 		 	 		 
	 		 //退款数据库检查
			data.put("orderId", data.get("refundOrderId"));
			System.err.println("data"+data);
			//Reporter.log("退款数据库检查结果", gatewayDbCheck.checkGatewayDeal(data, 14));
			Reporter.log("退款数据库检查结果", gatewayDbCheck.checkGatewayDeal(data, 8)&&gatewayDbCheck.checkAcctItermsMsAdvanceRfd(data));
		} 
			else {
				Reporter.log(data.get("name"), payResult);
			}
		Reporter.end("当前测试--------：" + data.get("name") + "结束！");
	}
 

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@DataProvider(name = "msGatewayAdvanceRefund")
	public Iterator<Object[]> msGatewayAdvanceRefund() throws IOException {
		return ExcelProviderByEnv(this, "msGatewayAdvanceRefund");
	}	
}

