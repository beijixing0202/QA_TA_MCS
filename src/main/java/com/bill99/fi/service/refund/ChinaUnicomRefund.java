package com.bill99.fi.service.refund;

import java.util.Map;

import shelper.iffixture.HttpFixture;

public interface ChinaUnicomRefund {
     public void refundrequest(HttpFixture query,Map<String,String> data);
}
