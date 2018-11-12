package com.bill99.fi.service.refund;

import java.util.Map;

import shelper.iffixture.HttpFixture;

public interface WebsiteSingleRfd  {
	
	public void singleRfd(HttpFixture query,Map<String, String> data);

}