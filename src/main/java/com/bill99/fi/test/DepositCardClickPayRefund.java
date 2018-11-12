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
import com.bill99.fi.service.refund.GatewayRefund;
import com.bill99.fi.service.refund.impl.WebsiteSingleRfdImpl;

public class DepositCardClickPayRefund extends BaseTestCase {

	@Autowired
	private DoSubmit doSubmit;
	@Autowired
	private GatewayPageSubmit gatewayPageSubmit;

	@Autowired
	private DoSubmit bill99WebSiteLoginSubmit;
	@Autowired
	private PaymentController paymentController;
	@Autowired
	private ParameterSignMsg parameterSignMsg;
	@Autowired
	private GatewayDbCheck gatewayDbCheck;
	@Autowired
	private LogDbCheck logDbCheck;
	@Autowired
	private GatewayRefund gatewayRefund;

	@Test(description = "网关3.0储蓄卡快捷支付退款", dataProvider = "depositCardClickPayRefund", timeOut = 1200000, enabled = true)
	public void depositCardClickPayRefund(Map<String, String> data) {
		Reporter.start("当前测试--------：" + data.get("name") + "开始！");
		// 添加一些字段的默认值
		ParameterDispose.addDefaultValue(data);
		if (data.get("signMsg").equals("")) {

			data.put("signMsg", parameterSignMsg.SignMsg(
					ParameterSource.gatewayParameter, data));
		}

		// 网关提交
		HttpFixture query = doSubmit.gatewaySubmit(data);
		// 选择支付方式
		gatewayPageSubmit.selectPayType(query, data);
		//选择银行———目的是恢复首次支付
		gatewayPageSubmit.selectDepositPayBank(query, data);
		//快捷支付
		boolean payResult = paymentController.gateway3DepositPay(query, data);
		if (payResult) {
			Reporter.log("+++++++++++提交成功+++++++++++");
			Reporter.log(data.get("name"),gatewayDbCheck.checkGatewayCNPDeal(data, 1)&& logDbCheck.notifyLogDbCkeck(data));

		} else {
			Reporter.log(data.get("name"), payResult);
		}
		
		// website登录
		HttpFixture loginHf = bill99WebSiteLoginSubmit.bill99WebSiteLoginSubmit(data);
		Reporter.log("website 登陆成功",loginHf.getResponseBody().contains(data.get("pname")+"，欢迎您！"));
				
		//单笔退款申请
		new WebsiteSingleRfdImpl().singleRfd(loginHf, data);
						
				
		//退款数据库检查
		String orderId;
		orderId= gatewayDbCheck.getRefundOrderIdBySeqId(gatewayDbCheck.getSequenceidByOrderid(data).getSequenceid());
		System.out.println("orderId="+orderId);
				
		data.put("orderId", orderId);
		data.put("amount", data.get("orderAmount")+"000");
		data.put("poundage", data.get("orderAmount")+"0");
		System.err.println("data"+data);
		//退款数据检查
		if(gatewayDbCheck.checkGatewayCNPDeal(data, 1))
		{
			Reporter.end("当前测试--------：" + data.get("name") + "数据库检查成功");	
		}
		Reporter.end("当前测试--------：" + data.get("name") + "结束！");
				
	}


	@BeforeClass
	public void beforeClass() {

	}

	@AfterClass
	public void afterClass() {

	}

	@DataProvider(name = "depositCardClickPayRefund")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "depositCardClickPayRefund");
	}

}
