package com.bill99.fi.service.gateway.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

import com.bill99.fi.common.helper.ParametersInitialization;
import com.bill99.fi.orm.mng.GatewayDbCheck;
import com.bill99.fi.service.gateway.GatewayPageSubmit;

public class GatewayPageSubmitImpl implements GatewayPageSubmit {
	private ParametersInitialization parametersInitialization;
	private GatewayDbCheck gatewayDbCheck;
	

	public void fillPayerInfo(HttpFixture query, Map<String, String> data) {
		// 补充付款人信息
		query.nextRequest();
		String payerNameUrl = null;
		try {
			payerNameUrl = URLEncoder.encode(
					URLEncoder.encode(data.get("payerName"), "utf-8"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		query.setUrl("http://www.99bill.com/gateway/hwPay.htm?method=checkRcsInfo&identityname="
				+ payerNameUrl
				+ "&identitycard="
				+ data.get("payerIdentityCard")
				+ "&cardNo="
				+ data.get("cardNo"));
		query.Post();
	}

	public void selectPayType(HttpFixture query, Map<String, String> data) {
		// 支付方式选择
		query.nextRequest();
		query.setUrl(parametersInitialization.getGatewayPayTypeUrl()
				+ "?method=selectPayType&payType=" + data.get("selectPayType"));
		//System.err.println(query.toString());
		query.Get();
		System.out.println("1===="+parametersInitialization.getGatewayPayTypeUrl()
				+ "?method=selectPayType&payType=" + data.get("selectPayType"));
		 System.out.println("2===="+query.getResponseBody());
	}

	public void selectBank(HttpFixture query, Map<String, String> data) {
		// 银行选择
		query.nextRequest();
		query.setUrl(parametersInitialization.getGatewayBankUrl()
				+ "?target=https://www.99bill.com/seashell/html/website/quotaMsg/quota_"
				+ data.get("selectBankId").toLowerCase() + ".html");
		query.Get();
		// 提交到银行支付
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getGatewayToBankUrl()
				+ "?method=selectBank&bankId=" + data.get("selectBankId")
				+ "&payerEmail=test@test.com");
		query.addRequestBody("bankId=" + data.get("selectBankId")
				+ "&method=selectBank&selectedCoupon=&selectedLoyalCardNumber=");
		query.Post();
	}

	// 网关B2B
	public void selectB2BBank(HttpFixture query, Map<String, String> data) {
		// 提交到银行支付
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getGatewayToB2BUrl()
				+ "?method=selectBank&bankId=" + data.get("selectBankId"));
		// + "&payerEmail=test@test.com");
		query.addRequestBody("bank=" + data.get("selectBankId")
				+ "&payerBankAcctNo=&selectbank=" + data.get("selectBankId")
				+ "&payerEmail=jiang@qa.99bill.com");
		query.Post();
		// System.out.println("==1:"+query.getResponseBody());
		// System.out.println("==2:"+query.getResponseheaders());
		// System.out.println("==3:"+query.getResponseheader("location") );

	}

	public void msGatewayselectPayType(HttpFixture query,
			Map<String, String> data) {
		// 支付方式选择
		query.nextRequest();
		query.setUrl(parametersInitialization.getMsGatewayPayTypeUrl()
				+ "?method=selectPayType&payType=" + data.get("selectPayType"));
		query.Get();
		System.out.println("=======1:======"+query.getResponseBody());
	}

	public void msGatewayselectBank(HttpFixture query, Map<String, String> data) {
		// 银行选择
		query.nextRequest();
		query.setUrl(parametersInitialization.getMsGatewayBankUrl()
				+ "?target=https://www.99bill.com/seashell/html/website/quotaMsg/quota_"
				+ data.get("selectBankId").toLowerCase() + ".html");
		query.Get();
		// 提交到银行支付
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMsGatewayToBankUrl()
				+ "?method=selectBank&bankId=" + data.get("selectBankId")
				+ "&payerEmail=test@test.com");
		query.addRequestBody("bankId=" + data.get("selectBankId")
				+ "&method=selectBank&selectedCoupon=&selectedLoyalCardNumber=");
		query.Post();
		// System.out.println("=======1:======"+query.getResponseBody());
	}

	public void msGatewayselectB2BBank(HttpFixture query,
			Map<String, String> data) {
		// 提交到银行支付
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMsGatewayToB2BUrl()
				+ "?method=selectBank&bankId=" + data.get("selectBankId"));
		query.addRequestBody("bank=" + data.get("selectBankId")
				+ "&payerBankAcctNo=&selectbank=" + data.get("selectBankId")
				+ "&payerEmail=jiang@qa.99bill.com");
		query.Post();
		// System.out.println("==1:"+query.getResponseBody());
		// System.out.println("==2:"+query.getResponseheaders());
		// System.out.println("==3:"+query.getResponseheader("location") );

	}

	public void selectCNPBank(HttpFixture query, Map<String, String> data) {
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/creditCardPay.htm?method=selectBank");
		query.addRequestBody("bankId=" + data.get("selectBankId"));
		query.Post();
	}

	public void selectClickPayBank(HttpFixture query, Map<String, String> data) {
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectBank&bankId=BOC&selectPan=");
		query.addRequestBody("bankId=" + data.get("selectBankId")
				+ "&historySelPayWay=");
		query.Post();
		System.out.println("3+++++++++++++++++++++++++++++"+query.getResponseBody());

	}

	public void selectDepositPayBank(HttpFixture query, Map<String, String> data) {
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		//stage2 query.setUrl("http://www.99bill.com/gateway/debitCardPay.htm?method=selectBank&bankId=BOC&selectPan=");
		query.setUrl("http://www.99bill.com/gateway/debitCardPay.htm?method=selectBank&bankId=BOC&selectPan=");
		query.addRequestBody("bankId=" + data.get("selectBankId"));
		query.Post();

	}

	public void deleteMobilePayBank(HttpFixture query, Map<String, String> data) {
		
		Reporter.log("开始删除绑定银行卡");
		// 点设置
		query.nextRequest();
		//query.setUrl("http://www.99bill.com/mobilegateway/mobilePayAction.htm?method=showBindCard");
		query.setUrl("http://www.99bill.com/mobilegateway/mobilePayAction.htm?method=showBindCard&bankId="+data.get("bankId")+"&cardType="+data.get("cardType")+"&showPan="+data.get("showPan"));
		
		query.Get();
		//System.out.println("======7======="+query.getResponseBody());
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/mobilegateway/mobilePayAction.htm?method=removeBindCards");
		query.addRequestBody("&bindCards="+data.get("bindCards"));
		query.Post();
		//System.out.println("======8======="+query.getResponseBody());
		query.nextRequest();
		/*query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");*/
		query.setUrl("http://www.99bill.com/mobilegateway/mobileGateway.htm?method=index");
    	query.addRequestBody("&method=index"+"&first_url=1");
		query.Post();
		
		
		//System.out.println("======9======="+query.getResponseBody());
	}

	public ParametersInitialization getParametersInitialization() {
		return parametersInitialization;
	}

	public void setParametersInitialization(ParametersInitialization parametersInitialization) {
		this.parametersInitialization = parametersInitialization;
	}
	public GatewayDbCheck getGatewayDbCheck(){
		return gatewayDbCheck;
	}
 
	public void setGatewayDbCheck(GatewayDbCheck gatewayDbCheck){
		this.gatewayDbCheck = gatewayDbCheck;
	}
}
