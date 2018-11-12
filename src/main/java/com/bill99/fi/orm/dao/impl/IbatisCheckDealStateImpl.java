package com.bill99.fi.orm.dao.impl;

import java.util.ArrayList;
import java.util.Map;

import com.bill99.qa.framework.jdbc.TaDbHandller;
import com.bill99.fi.orm.dao.IbatisCheckDealState;
import com.bill99.fi.orm.entity.Paymentorder;
import com.bill99.fi.orm.entity.Unionorderlog;

public class IbatisCheckDealStateImpl implements IbatisCheckDealState {

	private TaDbHandller taSeashellDbHandller;
	private TaDbHandller  taInfoDbHandller;

	public TaDbHandller getTaSeashellDbHandller() {
		return taSeashellDbHandller;
	}

	public void setTaSeashellDbHandller(TaDbHandller taSeashellDbHandller) {
		this.taSeashellDbHandller = taSeashellDbHandller;
		                          
	}
	

	public void setTaInfoDbHandller(TaDbHandller taInfoDbHandller) {
		this.taInfoDbHandller = taInfoDbHandller;
	}

	public int ibatisCheckGatewayDealState(Map<String, String> data) {
		
		System.err.println(data);

		return Integer.valueOf(taSeashellDbHandller.queryForObject("TestSeashellDb.ibatisCheckGatewayDealState", data).toString());
	}

	public int ibatisCheckGatewayDealStateByOrgOrderId(Map<String, String> data) {
		
		return Integer.valueOf(taSeashellDbHandller.queryForObject("TestSeashellDb.ibatisCheckGatewayDealStateByOrgOrderId", data).toString());
	}
	
	public int ibatisCheckMergeDealState(Map<String, String> data) {

		return Integer.valueOf(taSeashellDbHandller.queryForObject("TestSeashellDb.ibatisCheckMergeDealState", data).toString());
	}

	public int checkGatewayorder(Map<String, String> data) {

		return Integer.valueOf(taSeashellDbHandller.queryForObject("TestSeashellDb.checkGatewayorder", data).toString());
	}

	public Paymentorder getSequenceidByOrderid(Map<String, String> data) {

		return taSeashellDbHandller.queryForObject("TestSeashellDb.getSequenceidByOrderid", data);
	}

	public Paymentorder getInstallmentSequenceidByOrderid(Map<String, String> data) {

		return taSeashellDbHandller.queryForObject("TestSeashellDb.getInstallmentSequenceidByOrderid", data);
	}

	public String getMobileValidateCode(Map<String, String> data) {

		return taSeashellDbHandller.queryForObject("TestSeashellDb.getMobileValidateCode", data);
	}
	
	public String getMobileValidateCodeFromInfDb(Map<String, String> data) {

		return taInfoDbHandller.queryForObject("TestInfoDb.getMobileValidateCode", data);
	}

	public int checkCNPDealState(Map<String, String> data) {

		return Integer.valueOf(taSeashellDbHandller.queryForObject("TestSeashellDb.checkCNPDealState", data).toString());
	}

	public String getGateWayFxLogSequenId(String data) {
		return taSeashellDbHandller.queryForObject("TestSeashellDb.getGateWayFxLogSequenId", data);
	}

	public String checkFxlogType2(String data) {
		return taSeashellDbHandller.queryForObject("TestSeashellDb.fxlogtype2", data);
	}

	public String checkFxlogType8(String data) {
		return taSeashellDbHandller.queryForObject("TestSeashellDb.fxlogtype8", data);
	}

	public int ibatisCheckAtaNotifyLogDbCheck(Map<String, String> data) {

		return Integer.valueOf(taSeashellDbHandller.queryForObject("TestSeashellDb.ibatisCheckAtaNotifyLogDbCheck", data).toString());
	} 
	

	public Unionorderlog getUnionorderlog(String orderId) {

		return taSeashellDbHandller.queryForObject("TestSeashellDb.getUnionorderlog", orderId);

	}
	
	public String getRefundOrderIdBySeqId(String SeqID){
		
		return taSeashellDbHandller.queryForObject("TestSeashellDb.getRefundOrderIdBySeqId", SeqID);
	}
	
    public String getSmsNotification(Map<String, String> data){
		
		return taSeashellDbHandller.queryForObject("TestSeashellDb.getSmsNotification", data);
	}
    
    public int getRechargeWithOffineDealState(Map<String, String> data) {

		return Integer.valueOf(taSeashellDbHandller.queryForObject("TestSeashellDb.getRechargeWithOffineDealState",data).toString());
	}
    
    public Paymentorder getDealidByOrgorderid(Map<String, String> data){
    	
    	System.out.println("orgorderid+++++++++++++++++++"+data.get("orgorderID"));
    	
    	return taSeashellDbHandller.queryForObject("TestSeashellDb.getDealidWithOrgorderId",data);
    }
    public boolean getEntryParamByOrderid(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid1",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid2",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid3",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid4",data);
        System.out.println("result4"+result4);
        int result5=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid5",data);
        System.out.println("result5"+result5);
        int result6=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid6",data);
        System.out.println("result6"+result6);
        result = result1+result2+result3+result4+result5+result6 == 6;
    	return result;
    }
    
    public boolean getEntryParamByOrderidRfd(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid11",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid12",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid13",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid14",data);
        System.out.println("result4"+result4);
        int result5=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid15",data);
        System.out.println("result5"+result5);
        int result6=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid16",data);
        System.out.println("result6"+result6);
        result = result1+result2+result3+result4+result5+result6 == 6;
    	return result;
    }
    
    public boolean getEntryParamByOrderidBank(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid21",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid22",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid23",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid24",data);
        System.out.println("result4"+result4);
        result = result1+result2+result3+result4== 4;
    	return result;
    }
    
    public boolean getEntryParamByOrderidBankRfd(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid31",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid32",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid33",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid34",data);
        System.out.println("result4"+result4);
        result = result1+result2+result3+result4== 4;
    	return result;
    }
    public boolean getEntryParamByOrderidOffline(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid41",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid42",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid43",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid44",data);
        System.out.println("result4"+result4);
        result = result1+result2+result3+result4== 4;
    	return result;
    }

    public boolean getEntryParamByOrderidMsAcct(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid51",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid52",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid53",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid54",data);
        System.out.println("result4"+result4);
        int result5=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid55",data);
        System.out.println("result5"+result5);
        int result6=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid56",data);
        System.out.println("result6"+result6);
        int result7=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid57",data);
        System.out.println("result7"+result7);
        int result8=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid58",data);
        System.out.println("result8"+result8);
        result = result1+result2+result3+result4+result5+result6+result7+result8 == 8;
    	return result;
    }
    public boolean getEntryParamByOrderidMsBank(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid59",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid510",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid511",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid54",data);
        System.out.println("result4"+result4);
        int result5=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid512",data);
        System.out.println("result5"+result5);
        int result6=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid513",data);
        System.out.println("result6"+result6);
        int result7=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid514",data);
        System.out.println("result7"+result7);
        int result8=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid515",data);
        System.out.println("result8"+result8);
        result = result1+result2+result3+result4+result5+result6+result7+result8 == 8;
    	return result;
    }
    public boolean getEntryParamByOrderidMsTolerance(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid59",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid510",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid514",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid515",data);
        System.out.println("result4"+result4);
        int result5=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid516",data);
        System.out.println("result5"+result5);
        int result6=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid517",data);
        System.out.println("result6"+result6);
        int result7=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid54",data);
        System.out.println("result7"+result7);
        int result8=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid511",data);
        System.out.println("result8"+result8);
        int result9=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid512",data);
        System.out.println("result9"+result9);
        int result10=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid513",data);
        System.out.println("result10"+result10);
        result = result1+result2+result3+result4+result5+result6+result7+result8+result9+result10 == 10;
    	return result;
    }
    
    public boolean getEntryParamByOrderidRecharge(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid25",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid26",data);
        System.out.println("result2"+result2);
        result = result1+result2 == 2;
    	return result;
    }

    public boolean getEntryParamByOrderidMsToleranceRfd(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid71",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid72",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid73",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid74",data);
        System.out.println("result4"+result4);
        int result5=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid75",data);
        System.out.println("result5"+result5);
        int result6=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid76",data);
        System.out.println("result6"+result6);
        result = result1+result2+result3+result4+result5+result6 == 6;
    	return result;
    }
    public boolean getEntryParamByOrderidMsAdvanceRfd(Map<String, String>data){
    	boolean result = false;
    	int result1 = taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid61",data);
    	System.out.println("result1"+result1);
        int result2= taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid62",data);
        System.out.println("result2"+result2);
        int result3=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid63",data);
        System.out.println("result3"+result3);
        int result4=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid64",data);
        System.out.println("result4"+result4);
        int result5=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid65",data);
        System.out.println("result5"+result5);
        int result6=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid66",data);
        System.out.println("result6"+result6);
        int result7=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid67",data);
        System.out.println("result7"+result7);
        int result8=taSeashellDbHandller.queryForObject("TestSeashellDb.getAcctItermsWithOrderid68",data);
        System.out.println("result8"+result8);
        result = result1+result2+result3+result4+result5+result6+result7+result8 == 8;
    	return result;
    }
    
}
