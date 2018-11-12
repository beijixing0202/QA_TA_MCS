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
import com.bill99.fi.service.payments.PaymentController;
import com.bill99.fi.service.refund.impl.WebsiteSingleRfdImpl;

public class SingleRfdBankPay  extends BaseTestCase {			
	@Autowired
	private DoSubmit bill99WebSiteLoginSubmit;
	@Autowired
	private DoSubmit doSubmit;
	@Autowired
	private PaymentController paymentController;	
	@Autowired
	private GatewayDbCheck gatewayDbCheck;
	@Autowired
	private ParameterSignMsg parameterSignMsg;
	@Autowired
	private LogDbCheck logDbCheck;

	@Test(description = "网关单笔退款-银行卡支付", dataProvider = "singleRfdBankPay", timeOut = 600000, enabled = true)
		public void singleRfdBankPay(Map<String, String> data) throws Exception {
		Reporter.start("当前测试--------：" + data.get("name") + "开始！");
		// 添加一些字段的默认值
		ParameterDispose.addDefaultValue(data);
		data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.gatewayParameter, data));
		
		// 网关提交
		HttpFixture query = doSubmit.gatewaySubmit(data);
		// 模拟银行支付，及检查商户通知页面是否返回成功
		boolean payResult = paymentController.payDemoBankOnce(query, data);
		// 如果支付成功，进行数据库检查 数据库和商户通知
		if (payResult) {
			Reporter.log("网关银行卡支付数据库检查结果",
					gatewayDbCheck.checkGatewayDeal(data, 4)&& logDbCheck.notifyLogDbCkeck(data)&& gatewayDbCheck.checkGatewayorder(data, 1));
		} else {
			Reporter.log("网关银行卡支付", payResult);
				}
		// website登录
		HttpFixture loginHf = bill99WebSiteLoginSubmit.bill99WebSiteLoginSubmit(data);
		Reporter.log("website 登陆成功",loginHf.getResponseBody().contains(data.get("pname")+"，欢迎您！"));
		
		//单笔退款申请
		new WebsiteSingleRfdImpl().singleRfd(loginHf, data);
		
		//风控财务通过
		doSubmit.refundRiskAndFinance(data.get("orderId"), data.get("pemail"), 1);
		Reporter.log("退款过风控，过财务处理！");
		
 		//退款数据库检查
		String orderId;
		orderId= gatewayDbCheck.getRefundOrderIdBySeqId(gatewayDbCheck.getSequenceidByOrderid(data).getSequenceid());
		data.put("orderId", orderId);
		data.put("amount", data.get("orderAmount")+"000");
		data.put("poundage", data.get("orderAmount")+"0");
		
		
		//退款数据检查
		if(gatewayDbCheck.checkGatewayDeal(data, 6)&&gatewayDbCheck.checkAcctItermsBankRfd(data))
		{
			Reporter.end("当前测试--------：" + data.get("name") + "数据库检查成功");	
		}
		Reporter.end("当前测试--------：" + data.get("name") + "结束！");
		/*Reporter.log("退款数据库检查结果", gatewayDbCheck.checkGatewayDeal(data, 6));		
		
		Reporter.end("当前测试--------：" + data.get("name") + "结束！");*/
}
		
	@DataProvider(name = "singleRfdBankPay")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "singleRfdBankPay");
		}			
	}