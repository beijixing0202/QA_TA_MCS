package com.bill99.fi.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
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
import com.bill99.fi.service.refund.impl.WebsiteSingleRfdImpl;

public class GateWay3AcctPayRfd  extends BaseTestCase {			
	@Autowired
	private DoSubmit bill99WebSiteLoginSubmit;
	@Autowired
	private DoSubmit doSubmit;
	@Autowired
	private GatewayPageSubmit gatewayPageSubmit;
	@Autowired
	private PaymentController paymentController;	
	@Autowired
	private GatewayDbCheck gatewayDbCheck;
	@Autowired
	private ParameterSignMsg parameterSignMsg;
	@Autowired
	private LogDbCheck logDbCheck;
	
	
	@Test(description = "网关3.0单笔退款-账户支付", dataProvider = "gateWay3AcctPayRfd", timeOut = 2400000, enabled = true)
	
	public void singleRfdAcctPay(Map<String, String> data) throws Exception {
		Reporter.start("当前测试--------：" + data.get("name") + "开始！");
		// 添加一些字段的默认值
			ParameterDispose.addDefaultValue(data);
				data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.gatewayParameter, data));
		// 网关提交
			HttpFixture query = doSubmit.gatewaySubmit(data);
		// 选择支付方式
			gatewayPageSubmit.selectPayType(query, data);
		// 账户支付
			boolean payResult = paymentController.payAccount(query, data);
		// 如果支付成功，进行数据库检查 数据库和商户通知
			if (payResult) {
				Reporter.log("网关账户支付数据库检查结果",
						gatewayDbCheck.checkGatewayDeal(data, 6)&& logDbCheck.notifyLogDbCkeck(data)&& gatewayDbCheck.checkGatewayorder(data, 1));
			} else {
				Reporter.log("网关账户支付", payResult);
					}
		// website登录
		HttpFixture loginHf = bill99WebSiteLoginSubmit.bill99WebSiteLoginSubmit(data);
		Reporter.log("website 登陆成功",loginHf.getResponseBody().contains(data.get("pname")+"，欢迎您！"));
		
		//单笔退款申请
		new WebsiteSingleRfdImpl().singleRfd(loginHf, data);
				
		//风控财务通过
		doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("pemail"), 1);
		Reporter.log("退款过风控处理！");
		
	 	//退款数据库检查
		String orderId;
		orderId= gatewayDbCheck.getRefundOrderIdBySeqId(gatewayDbCheck.getSequenceidByOrderid(data).getSequenceid());
		System.out.println("orderId="+orderId);
		System.err.println("data"+data);
		data.put("orderId", orderId);
		data.put("amount", data.get("orderAmount")+"000");
		data.put("poundage", data.get("orderAmount")+"0");
		//退款数据检查
		if(gatewayDbCheck.checkGatewayDeal(data, 6)&&gatewayDbCheck.checkAcctItermsRfd(data))
		{
			Reporter.end("当前测试--------：" + data.get("name") + "数据库检查成功");	
		}
		Reporter.end("当前测试--------：" + data.get("name") + "结束！");
}
		
	@DataProvider(name = "gateWay3AcctPayRfd")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "gateWay3AcctPayRfd");
		}				
	}