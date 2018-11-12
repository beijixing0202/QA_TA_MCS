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
import com.bill99.fi.service.gateway.GatewayPageSubmit;
import com.bill99.fi.service.payments.PaymentController;

public class CreditCardInstallmentPay extends BaseTestCase {
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
	
	@Test(description = "网关3.0信用卡快捷分期支付", dataProvider = "creditCardInstallmentPay", timeOut = 300000, enabled = true)
	public void creditCardInstallmentPay(Map<String, String> data) throws Exception {
		Reporter.start("当前测试--------：" + data.get("name") + "开始！");
		// 添加一些字段的默认值
		ParameterDispose.addDefaultValue(data);
		if (data.get("signMsg").equals("")) {

			data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.gatewayParameter, data));
		}

		// 网关提交
		HttpFixture query = doSubmit.gatewaySubmit(data);
		System.out.println("query.Response_____________"+query.getResponseBody());
	    //选择支付方式
		gatewayPageSubmit.selectPayType(query, data);
		//选择银行———目的是恢复首次支付
		gatewayPageSubmit.selectClickPayBank(query, data);
		//快捷支付
		boolean payResult = paymentController.gateway3InstallmentPay(query, data);
		if (payResult) {
			Reporter.log("+++++++++++提交成功+++++++++++");
			Reporter.log(data.get("name"),gatewayDbCheck.checkGatewayCNPDeal(data, 1)&& logDbCheck.notifyLogDbCkeck(data));

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

	@DataProvider(name = "creditCardInstallmentPay")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "creditCardInstallmentPay");
	}
}
