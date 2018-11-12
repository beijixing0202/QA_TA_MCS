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

public class MsgatewayBankPay extends BaseTestCase {
	@Autowired
	private ParameterSignMsg parameterSignMsg;
	@Autowired
	private DoSubmit doSubmit;
	@Autowired
	private GatewayPageSubmit gatewayPageSubmit;
	@Autowired
	private PaymentController paymentController;
	@Autowired
	private GatewayDbCheck gatewayDbCheck;
	@Autowired
	private LogDbCheck logDbCheck;
	
	
 @Test(description="分账网关银行卡支付", dataProvider="msgatewayBankPay", timeOut=60000,enabled=true)
	 public void msgatewayBankPay(Map<String,String> data) throws Exception{
	 Reporter.start("当前测试------："+data.get("name")+"开始！");
	// 添加一些字段的默认值
	 ParameterDispose.addDefaultValue(data);
	 if (("").equals(data.get("signMsg"))) {

			data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.msgatewayParameter, data));
		}

	// 网关提交
		HttpFixture query = doSubmit.msGatewaySubmit(data);
    //选择支付方式
		gatewayPageSubmit.msGatewayselectPayType(query, data);	
			
	// 选择银行
		gatewayPageSubmit.msGatewayselectBank(query, data);
	// 模拟银行支付，及检查商户通知页面是否返回成功
		boolean payResult = paymentController.payDemoBankOnce(query, data);
	// 如果支付成功，进行数据库检查 数据库和商户通知
		if (payResult) {
			data.put("amount",data.get("orderAmount")+"0");
			data.put("halfAmount",  Integer.toString(Integer.parseInt(data.get("orderAmount")+"0")/2));
			data.put("poundage",Integer.toString(Integer.parseInt(data.get("orderAmount")+"0")/100));
			data.put("halfAmountAfterPoundage",Integer.toString((Integer.parseInt(data.get("halfAmount")))-(Integer.parseInt(data.get("poundage")))));
			System.err.println("data"+data);

			Reporter.log(data.get("name"), gatewayDbCheck.checkGatewayDeal(data, 8) && logDbCheck.notifyLogDbCkeck(data) && gatewayDbCheck.checkAcctItermsMsBank(data));

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

	@DataProvider(name = "msgatewayBankPay")
	public Iterator<Object[]> data4test() throws IOException {
		return ExcelProviderByEnv(this, "msgatewayBankPay");
	}
}

