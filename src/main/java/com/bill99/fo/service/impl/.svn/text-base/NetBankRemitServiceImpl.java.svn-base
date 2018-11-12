package com.bill99.fo.service.impl;

import org.openqa.selenium.WebDriver;

import com.bill99.fo.pageobject.FscLoginPage;
import com.bill99.fo.pageobject.HandBatchPage;
import com.bill99.fo.pageobject.RemitCheckPage;
import com.bill99.fo.pageobject.RemitResultHandlePage;
import com.bill99.fo.service.NetBankRemitService;

public class NetBankRemitServiceImpl implements NetBankRemitService {
	
	@Override
	public boolean fscLogin(WebDriver dr,String username, String passwd){
		FscLoginPage fscLoginPage = new FscLoginPage(dr);
		return fscLoginPage.login(username, passwd);
	}
	
	@Override
	public String doBatchId(WebDriver dr,String orderSeqId, int channelIndex) throws Exception{
		HandBatchPage handBatchPage = new HandBatchPage(dr);
		return handBatchPage.massPayRequest(orderSeqId, channelIndex);
	}

	@Override
	public void doRemit(WebDriver dr,String batchId,String bankNameChange,String checkTotalNum,String checkTotalAmount) {
		// TODO Auto-generated method stub		
		RemitCheckPage remitCheckPage = new RemitCheckPage(dr);
		remitCheckPage.checkedBatchInforQuery(batchId, bankNameChange, checkTotalNum, checkTotalAmount);
		
		RemitResultHandlePage remitResultHandlePage = new RemitResultHandlePage(dr);
		remitResultHandlePage.bankRemit(batchId, checkTotalAmount);

	}

}
