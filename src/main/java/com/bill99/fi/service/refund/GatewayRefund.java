package com.bill99.fi.service.refund;

import java.util.Map;

import shelper.iffixture.HttpFixture;

public interface GatewayRefund {
	
	public boolean refundRequest(Map<String, String> data);

	public void refundRequestAndRisk(Map<String, String> data);

	public void refundRequestAndRiskAndFinance(Map<String, String> data);
	
	public void entrustRefund(HttpFixture query,Map<String,String> data);
	public void apiRefund(HttpFixture query,Map<String,String> data);
}
