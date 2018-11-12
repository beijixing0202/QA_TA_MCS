package com.bill99.fi.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

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

public class GatewayPrepaidCardPay extends BaseTestCase {
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

	@Test(description = "人民币网关预付卡支付", dataProvider = "gatewayPrepaidCardPay", timeOut = 60000, enabled = true)
	public void gatewayPrepaidCardPay(Map<String, String> data) throws Exception {
		Reporter.start("当前测试--------：" + data.get("name") + "开始！");
		// 添加一些字段的默认值
		ParameterDispose.addDefaultValue(data);
		if (data.get("signMsg").equals("")) {

			data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.gatewayParameter, data));
		}

		// 网关提交
		HttpFixture query = doSubmit.gatewaySubmit(data);
		//选择支付方式
		gatewayPageSubmit.selectPayType(query, data);
		
		// 输入卡信息支付，及检查商户通知页面是否返回成功
		boolean payResult = paymentController.payPrepaidCard(query, data);
		
		// 如果支付成功，进行数据库检查 数据库和商户通知
		if (payResult) {

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

	@DataProvider(name = "gatewayPrepaidCardPay")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "gatewayPrepaidCardPay");
	}
}
