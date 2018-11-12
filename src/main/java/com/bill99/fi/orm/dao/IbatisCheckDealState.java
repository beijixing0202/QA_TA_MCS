package com.bill99.fi.orm.dao;

import java.util.ArrayList;
import java.util.Map;

import com.bill99.fi.orm.entity.Paymentorder;
import com.bill99.fi.orm.entity.Unionorderlog;

public interface IbatisCheckDealState {

	public int ibatisCheckGatewayDealState(Map<String, String> data);
	
	public int ibatisCheckGatewayDealStateByOrgOrderId(Map<String, String> data);
	
	public int ibatisCheckMergeDealState(Map<String, String> data);
	
	public int checkCNPDealState (Map<String, String> data);
	
	public int checkGatewayorder(Map<String, String> data);

	public Paymentorder getDealidByOrgorderid(Map<String, String> data);
	
	public Paymentorder getSequenceidByOrderid(Map<String, String> data);
	
	public Paymentorder getInstallmentSequenceidByOrderid(Map<String, String> data);
	
	public String getMobileValidateCode(Map<String, String> data);
	
	public String getGateWayFxLogSequenId(String data);
	
	public String checkFxlogType2(String data);
	
	public String checkFxlogType8(String data);
	
	public int ibatisCheckAtaNotifyLogDbCheck(Map<String, String> data);
	
	public Unionorderlog getUnionorderlog(String orderId);

	public String getRefundOrderIdBySeqId(String SeqID);
	
	public String getSmsNotification(Map<String, String> data);
	
	public int getRechargeWithOffineDealState( Map<String, String> data);

	public String getMobileValidateCodeFromInfDb(Map<String, String> data);

	public boolean getEntryParamByOrderid(Map<String, String> data);
	
	public boolean getEntryParamByOrderidRfd(Map<String, String> data);
	
	public boolean getEntryParamByOrderidBank(Map<String, String> data);
	
	public boolean getEntryParamByOrderidBankRfd(Map<String, String> data);
	
	public boolean getEntryParamByOrderidOffline(Map<String, String> data);

	public boolean getEntryParamByOrderidMsAcct(Map<String, String> data);
	
	public boolean getEntryParamByOrderidMsBank(Map<String, String> data);
	
	public boolean getEntryParamByOrderidMsTolerance(Map<String, String> data);
	
	public boolean getEntryParamByOrderidRecharge(Map<String, String> data);
	
	public boolean getEntryParamByOrderidMsToleranceRfd(Map<String, String> data);

	public boolean getEntryParamByOrderidMsAdvanceRfd(Map<String, String> data);

	
}
