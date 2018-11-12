package com.bill99.fo.service;

import org.openqa.selenium.WebDriver;

public interface NetBankRemitService {
	
	public boolean fscLogin(WebDriver dr,String username, String passwd);
	public String doBatchId(WebDriver dr,String orderSeqId, int channelIndex) throws Exception;
	public void doRemit(WebDriver dr,String batchId,String bankNameChange,String checkTotalNum,String checkTotalAmount);

}
