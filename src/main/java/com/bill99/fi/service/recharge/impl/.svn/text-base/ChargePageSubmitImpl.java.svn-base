package com.bill99.fi.service.recharge.impl;

import java.util.Map;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;



import com.bill99.fi.common.utils.CheckResponse;
import com.bill99.fi.orm.mng.GatewayDbCheck;
import com.bill99.fi.service.recharge.ChargePageSubmmit;


public class ChargePageSubmitImpl implements ChargePageSubmmit {
	
	
	public String bankOnLineCharge(HttpFixture loginHf, Map<String, String> data) {
		//进入账户页面
		loginHf.nextRequest();
		loginHf.setUrl("https://www.99bill.com/website/myaccount/myacctinfo.htm");
		loginHf.Get();  
		System.out.println(loginHf.getResponseBody());
		
//		System.out.println("充值前金额："+CheckResponse
//				 .parseString(
//						 "(?<=<td><b>)(.*?)(?=</strong>（元）</td>)",
//						 
//						 loginHf.getResponseBody()));
		
		//进入充值页面
		loginHf.nextRequest();
		loginHf.setUrl("https://www.99bill.com/fideposit/deposite/bankcarddeposit.htm");
		loginHf.Get();  
		loginHf.nextRequest();
		loginHf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		loginHf.setUrl("https://www.99bill.com/fideposit/deposite/bankcarddeposit.htm");
		//选择银行
		loginHf.addParamValue("bankId", data.get("selectBankId"));
		loginHf.addParamValue("depositAmount", data.get("amount"));
		loginHf.addParamValue("depositAmount_format", data.get("depositAmount_format"));
		//选择充值类型
		loginHf.addParamValue("depositType", data.get("selectPayType"));
		loginHf.addParamValue("netBank", data.get("netBank"));
		loginHf.addParamValue("payeeacctcode", data.get("merchant_id"));
		loginHf.addParamValue("refushDocument", "0");
		loginHf.Post();
		System.err.println("postRes+++"+loginHf.getResponseBody());
		Reporter.log("到银行模拟页面",loginHf.getResponseBody().contains("demobank/payment.jsp"));
		 
		 String orderNo = CheckResponse
				 .parseString(
						 "(?<='orderNo' VALUE=')(.*?)(?='/>)",
						 loginHf.getResponseBody());
		 System.out.println("orderno"+orderNo);
		return orderNo;
		/*loginHf.nextRequest();
		loginHf.setUrl("http://www.99bill.com/bankgateway/bankgateway/card/demobank.htm?merchantId=105110089991048&orderNo="+orderNo+"&payMoney=1.00&result=1&sign="+mac+"");
		loginHf.Get();*/
		
	}
	
	public String payOfflineCharge(HttpFixture loginHf, Map<String, String> data) {
		//进入账户页面
		loginHf.nextRequest();
		loginHf.setUrl("https://www.99bill.com/website/myaccount/myacctinfo.htm");
		loginHf.Get();  
		System.out.println(loginHf.getResponseBody());
		
		System.out.println("充值前金额："+CheckResponse
				 .parseString(
						 "(?<=<td><b>)(.*?)(?=</strong>（元）</td>)",
						 loginHf.getResponseBody()));
		//进入充值页面
		loginHf.nextRequest();
		loginHf.setUrl("https://www.99bill.com/fideposit/deposite/bankcarddeposit.htm");
		loginHf.Get();  
	
		loginHf.nextRequest();
		loginHf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		loginHf.setUrl("https://www.99bill.com/fideposit/deposite/bankcarddeposit.htm");
		loginHf.addParamValue("bankId", data.get("selectBankId"));
		loginHf.addParamValue("depositAmount", data.get("amount"));
		loginHf.addParamValue("depositAmount_format", data.get("depositAmount_format"));
		loginHf.addParamValue("depositType", data.get("selectPayType"));
		loginHf.addParamValue("netBank", data.get("netBank"));
		loginHf.addParamValue("offlineDepositType", data.get("offlineDepositType"));
		loginHf.addParamValue("payeeacctcode", data.get("merchant_id"));
		loginHf.addParamValue("refushDocument", "0");
		loginHf.Post();
        System.out.println(loginHf.getResponseBody());
	
		String dealId = CheckResponse
				 .parseString(
						 "(?<=交易号：)(.*)(?=　<span class=\"text12 textGray\">供您日后查询使用，请牢记)",
						 loginHf.getResponseBody());
		
		//data.put("dealid", dealId);
	
		//Reporter.log("支付成功", loginHf.getResponseBody().contains("class=\"waitsuccess\">线下支付申请提交成功！</div>"));
		Reporter.log("线下支付提交成功!", loginHf.getResponseBody().contains(" class=\"text12B bgdot02\">快钱接受汇款的银行账户：</div>"));
		
		/*boolean result = gatewayDbCheck.checkRechargeWithOffineDealState(data, 1);
		return result;*/
		return dealId;
		
		
		//loginHf.Post();
		
	}
	public void TranceferCharge(HttpFixture loginHf, Map<String, String> data) {
		
	}
	

}
