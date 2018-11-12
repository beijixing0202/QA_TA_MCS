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

public class MsGatewayRefundTolerance extends BaseTestCase {
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
	
	
 @Test(description="分账差额补贴退款", dataProvider="refundTolerance", timeOut=300000,enabled=true)
	 public void refundTolerance(Map<String,String> data) throws Exception{
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
 			Reporter.log("差额补贴支付数据库检查结果",
 					gatewayDbCheck.checkGatewayDeal(data, 10) && logDbCheck.notifyLogDbCkeck(data) 
 					&& gatewayDbCheck.checkGatewayorder(data, 1));
 			data.put("ref_signMsg",  parameterSignMsg.SignMsg(ParameterSource.msgatewayRefundParameter, data));
 			msgatewayRefund.refundRequestAndRiskAndFinance(data);
 			data.put("orderId", data.get("refundOrderId"));
 			System.err.println("data"+data);
 			Reporter.log("差额补贴退款数据库检查结果", gatewayDbCheck.checkGatewayDeal(data, 4)&& gatewayDbCheck.checkAcctItermsMsToleranceRfd(data));
 			
		} else {
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

	@DataProvider(name = "refundTolerance")
	public Iterator<Object[]> refundTolerance() throws IOException {
		return ExcelProviderByEnv(this, "refundTolerance");
	}
}

