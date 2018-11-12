package com.bill99.fi.orm.mng;

import java.util.Map;

import com.bill99.fi.orm.entity.Paymentorder;

public interface GatewayDbCheck {

	public boolean checkGatewayDeal(Map<String, String> data, int count);
	public boolean checkGatewayDealByOrgOrderId(Map<String, String> data, int count);
	
	public boolean checkMergeDeal(Map<String, String> data, int count);
	
	public boolean checkGatewayCNPDeal(Map<String, String> data, int count);

	public boolean checkGatewayorder(Map<String, String> data, int count);

	public Paymentorder getSequenceidByOrderid(Map<String, String> data);
	
	public Paymentorder getDealidByOrgorderid(Map<String, String> data);
	
	public Paymentorder getInstallmentSequenceidByOrderid(Map<String, String> data);
	
	public String  getMobileValidateCode(Map<String, String> data);
	
	public boolean  getGateWayFxLogCheck(Map<String, String> data);
	
	public boolean ATAnotifyLogDbCkeck(Map<String, String> data, int count);
	
	public String getRefundOrderIdBySeqId(String SeqID);
	
	public String getSmsNotification(Map<String, String> data);
	
	public boolean checkRechargeWithOffineDealState(Map<String, String> data,
			int count);
	
	public boolean checkMobileGateDelCardState(Map<String, String> data, int count);
	
	public boolean checkAcctIterms(Map<String, String> data);
	
	public boolean checkAcctItermsRfd(Map<String, String> data);
	
	public boolean checkAcctItermsBank(Map<String, String> data);
	
	public boolean checkAcctItermsBankRfd(Map<String, String> data);
	
	public boolean checkAcctItermsOffline(Map<String, String> data);
	
	public boolean checkAcctItermsMsAcct(Map<String, String> data);
	
	public boolean checkAcctItermsMsBank(Map<String, String> data);
	
	public boolean checkAcctItermsMsTolerance(Map<String, String> data);
	
	public boolean checkAcctItermsRecharge(Map<String, String> data);
	
	public boolean checkAcctItermsMsToleranceRfd(Map<String, String> data);
	
	public boolean checkAcctItermsMsAdvanceRfd(Map<String, String> data);
}
