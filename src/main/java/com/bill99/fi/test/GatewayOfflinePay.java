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

public class GatewayOfflinePay extends BaseTestCase {
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

	@Test(description = "人民币网关线下支付", dataProvider = "gatewayOfflinePay", timeOut = 60000, enabled = true)
	public void gatewayOfflinePay(Map<String, String> data) throws Exception {
		Reporter.start("当前测试--------：" + data.get("name") + "开始！");
		// 添加一些字段的默认值
		ParameterDispose.addDefaultValue(data);
		if (data.get("signMsg").equals("")) {

			data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.gatewayParameter, data));
		}

		// 网关提交
		HttpFixture query = doSubmit.gatewaySubmit(data);
		//System.out.println(query.getResponseBody());
		//选择支付方式
		gatewayPageSubmit.selectPayType(query, data);
		
		// 通过银行柜台汇款，及检查页面申请是否成功
		boolean payResult = paymentController.payOffline(query, data);
		
		//intra后台上传excel还没做,申请成功即可
		// 如果支付成功，进行数据库检查 数据库和商户通知
		if (payResult) {

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

	@DataProvider(name = "gatewayOfflinePay")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "gatewayOfflinePay");
	}
}
