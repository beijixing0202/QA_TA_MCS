package com.bill99.fi.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
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

public class MsgatewayaccountPay extends BaseTestCase{
	
	@Autowired
	private ParameterSignMsg parameterSignMsg;
	@Autowired
	private DoSubmit doSubmit;
	@Autowired
	private GatewayDbCheck gatewayDbCheck;
	@Autowired
	private PaymentController paymentController;
	@Autowired
	private LogDbCheck logDbCheck;
	@Autowired
	private GatewayPageSubmit gatewayPageSubmit;

	
  @Test(description = "分账网关账户支付", dataProvider = "msgatewayaccountPay", timeOut=240000,enabled= true)
  public void msgatewayaccountPay(Map<String, String> data) {
	  Reporter.start("当前测试------：" + data.get("name")+ "开始！");
	  //添加一些默认值
	  ParameterDispose.addDefaultValue(data);
	  if(("").equals(data.get("signMsg")))
	  {
		  data.put("signMsg", parameterSignMsg.SignMsg(ParameterSource.msgatewayParameter, data));
	  }
	  
	 //网页提交
	  HttpFixture query = doSubmit.msGatewaySubmit(data);
	//选择支付方式
	gatewayPageSubmit.msGatewayselectPayType(query, data);	
	// 账户支付
	boolean payResult = paymentController.msgpayAccount(query, data);
	
	if (payResult) {
		data.put("amount",data.get("orderAmount")+"0");
		data.put("halfAmount",  Integer.toString(Integer.parseInt(data.get("orderAmount")+"0")/2));
		data.put("poundage",Integer.toString(Integer.parseInt(data.get("orderAmount")+"0")/50));
		data.put("halfAmountAfterPoundage",Integer.toString((Integer.parseInt(data.get("halfAmount")))-(Integer.parseInt(data.get("poundage")))));
		System.err.println("data"+data);
		Reporter.log(data.get("name"),
			gatewayDbCheck.checkGatewayDeal(data, 8) && logDbCheck.notifyLogDbCkeck(data) && gatewayDbCheck.checkGatewayorder(data, 1) && gatewayDbCheck.checkAcctItermsMsAcct(data));

		} else {
		Reporter.log(data.get("name"), false);

	}
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @DataProvider(name = "msgatewayaccountPay")
  public Iterator<Object[]> accountPay() throws IOException 
  {
	  return ExcelProviderByEnv(this, "msgatewayaccountPay");
  }


}
