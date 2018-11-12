package com.bill99.fi.service.recharge;

import java.util.Map;

import shelper.iffixture.HttpFixture;

public interface ChargePageSubmmit {
	
	public String bankOnLineCharge(HttpFixture query,Map<String, String> data);
	
	public void TranceferCharge(HttpFixture query,Map<String, String> data);
	
	public String payOfflineCharge(HttpFixture loginHf, Map<String, String> data);

}
