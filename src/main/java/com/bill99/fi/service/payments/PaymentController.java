package com.bill99.fi.service.payments;

import java.util.Map;

import shelper.iffixture.HttpFixture;

public interface PaymentController {
	// 支付方式
	public boolean payDemoBankOnce(HttpFixture query, Map<String, String> data);
	
	public boolean payAccount(HttpFixture query, Map<String, String> data);

	public boolean payCNP(HttpFixture query, Map<String, String> data);

	public boolean payOffline(HttpFixture query, Map<String, String> data);

	public boolean payPrepaidCard(HttpFixture query, Map<String, String> data);

	public boolean payMsGatewayCNP(HttpFixture query, Map<String, String> data);

	public boolean gateway3ClickPay(HttpFixture query, Map<String, String> data);
	
	public  boolean gateway3InstallmentPay(HttpFixture query, Map<String, String> data);

	public boolean gateway3DepositPay(HttpFixture query, Map<String, String> data);

	public boolean gateway3Pay(HttpFixture query, Map<String, String> data);
	
	public boolean gateway3ClickPayoff(HttpFixture query, Map<String, String> data);
	
	public boolean mobileGatewayClickFirstPay(HttpFixture query, Map<String, String> data);
	
	public boolean mobileGatewayClickFirstPayQuickPayoff(HttpFixture query, Map<String, String> data);
	
	public boolean mobileGatewayClickPay(HttpFixture query, Map<String, String> data);
	
	public boolean mobileGatewayDepositFirstPay(HttpFixture query, Map<String, String> data);
	
	public boolean mobileGatewayDepositPay(HttpFixture query, Map<String, String> data);
	
	public boolean mobileGatewayInnercardCNP(HttpFixture query, Map<String, String> data);

	public boolean ATApayAccount(HttpFixture query, Map<String, String> data);
	
	public boolean InstallmentPay(HttpFixture query, Map<String, String> data);
	
	public boolean reChargeDemoBank(HttpFixture query, Map<String, String> data);

	public boolean msgpayAccount(HttpFixture query, Map<String, String> data);  

	public boolean payMsGatewayClickPay(HttpFixture query, Map<String, String> data);
}
