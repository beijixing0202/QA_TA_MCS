package com.bill99.fi.orm.mng.impl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.testng.Reporter;

import com.bill99.fi.orm.dao.IbatisCheckDealState;
import com.bill99.fi.orm.dao.IbatisMasposCheckDealState;
import com.bill99.fi.orm.entity.Paymentorder;
import com.bill99.fi.orm.mng.GatewayDbCheck;

public class GatewayDbCheckImpl implements GatewayDbCheck {

	private IbatisCheckDealState ibatisCheckDealState;
	private IbatisCheckDealState checkGatewayRefundDealState;
    private IbatisMasposCheckDealState ibatisMasposCheckDealState;

	public IbatisCheckDealState getIbatisCheckDealState() {
		return ibatisCheckDealState;
	}

	public void setIbatisCheckDealState(IbatisCheckDealState ibatisCheckDealState) {
		this.ibatisCheckDealState = ibatisCheckDealState;
	}

	public IbatisCheckDealState getCheckGatewayRefundDealState() {
		return checkGatewayRefundDealState;
	}

	public void setCheckGatewayRefundDealState(IbatisCheckDealState checkGatewayRefundDealState) {
		this.checkGatewayRefundDealState = checkGatewayRefundDealState;
	}
	
	public IbatisMasposCheckDealState getIbatisMasposCheckDealState() {
		return ibatisMasposCheckDealState;
	}

	public void setIbatisMasposCheckDealState(IbatisMasposCheckDealState ibatisMasposCheckDealState) {
		this.ibatisMasposCheckDealState = ibatisMasposCheckDealState;
	}

	


	public boolean checkGatewayDeal(Map<String, String> data, int count) {
		boolean result = false;
		int i = 0;
		do {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.ibatisCheckGatewayDealState(data) == count;
			System.out.println("result~~~~~~~~~~~~~~~~~~"+result);
			i++;
		} while ((!result) && i < 100);
		Reporter.log("paymentorder表、deal表、entry表数据检查",result);
		return result;
	}

	
	public boolean checkGatewayDealByOrgOrderId(Map<String, String> data, int count) {
		boolean result = false;
		int i = 0;
		do {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.ibatisCheckGatewayDealStateByOrgOrderId(data) == count;
			i++;
		} while ((!result) && i < 100);

		return result;
	}

	public boolean checkMergeDeal(Map<String, String> data, int count) {
		boolean result = false;
		int i = 0;
		do {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.ibatisCheckMergeDealState(data) == count;
			i++;
		} while ((!result) && i < 10);

		return result;
	}

	public boolean checkGatewayCNPDeal(Map<String, String> data, int count) {
		boolean result = false;
		int i = 0;
		do {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.checkCNPDealState(data) == count;
			i++;
		} while ((!result) && i < 10);

		return result;
	}

	public boolean checkGatewayorder(Map<String, String> data, int count) {
		boolean result = ibatisCheckDealState.checkGatewayorder(data) == count;
		Reporter.log("gatewayorder表检查",result);
		return result;
	}

	public Paymentorder getSequenceidByOrderid(Map<String, String> data){
		int i=1;
		Paymentorder paymentorder=null;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paymentorder=ibatisCheckDealState.getSequenceidByOrderid(data);
			i++;
			System.out.println("paymentOrder---------------------"+paymentorder);
		}while ((null == paymentorder) && i < 100);
		
		
		return paymentorder;
		
	}

	public Paymentorder getInstallmentSequenceidByOrderid(Map<String, String> data) {
		return ibatisCheckDealState.getInstallmentSequenceidByOrderid(data);
	}

	public String getMobileValidateCode(Map<String, String> data) {
		int i = 1;
		String result = null;
		do {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.getMobileValidateCodeFromInfDb(data);

			if(!StringUtils.hasLength(result)){
				result = ibatisCheckDealState.getMobileValidateCode(data);
			}
			if(StringUtils.hasLength(result)){
				break;
			}
			i++;
		} while ((null == result) && i < 10);
		
//		if(!StringUtils.hasLength(result)){
//			do {
//				try {
//					Thread.sleep(i * 500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				result = ibatisCheckDealState.getMobileValidateCode(data);
//				i++;
//			} while ((null == result) && i < 10);
//		}

		return result;
	}

	public boolean getGateWayFxLogCheck(Map<String, String> data) {

		String fxSequenId = ibatisCheckDealState.getGateWayFxLogSequenId(data.get("orderId"));
		String sid2 = (Long.parseLong(fxSequenId) - 1) + "";
		String sid8 = (Long.parseLong(fxSequenId) + 1) + "";

		boolean flag2 = ibatisCheckDealState.checkFxlogType2(sid2).equals("1");
		boolean flag8 = ibatisCheckDealState.checkFxlogType8(sid8).equals("1");

		return flag2 & flag8;
	}

	public boolean ATAnotifyLogDbCkeck(Map<String, String> data, int count) {
		boolean result = false;
		int i = 0;
		do {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.ibatisCheckAtaNotifyLogDbCheck(data) == count;
			i++;
		} while ((!result) && i < 10);

		return result;
	}
	
	public String getRefundOrderIdBySeqId(String SeqID){
		return ibatisCheckDealState.getRefundOrderIdBySeqId(SeqID);
	}
	
	public String getSmsNotification(Map<String, String> data) {
		int i = 1;
		String result = null;
		do {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.getSmsNotification(data);
			i++;
		} while ((null == result) && i < 10);

		return result;
	}
	
	public boolean checkRechargeWithOffineDealState(Map<String, String> data, int count) {
		boolean result = false;
		int i = 0;
		do {
			try {
				Thread.sleep(i * 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ibatisCheckDealState.getRechargeWithOffineDealState(data)== count;
			i++;
		} while ((!result) && i < 10);

		return result;
		
	
	}
	public boolean checkMobileGateDelCardState(Map<String, String> data, int count){
		boolean result = false;
		int i = 0;
		do{
			try{
				Thread.sleep(i*500);
			}catch(InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();	
			}
			result= ibatisMasposCheckDealState.ibatisMasCheckDelCardState(data)==count;
			i++;
		}while((!result)&& i < 10);
		
		
		return result;
		
	}
	public Paymentorder getDealidByOrgorderid(Map<String, String> data){
		int i=1;
		Paymentorder paymentorder=null;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paymentorder=ibatisCheckDealState.getDealidByOrgorderid(data);
			i++;
		}while ((null == paymentorder) && i < 50);
		
		return paymentorder;
	}
	public boolean checkAcctIterms(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderid(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	
	public boolean checkAcctItermsRfd(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidRfd(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsBank(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidBank(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsBankRfd(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidBankRfd(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsOffline(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidOffline(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsMsAcct(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidMsAcct(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsMsBank(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidMsBank(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsMsTolerance(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidMsTolerance(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	
	public boolean checkAcctItermsRecharge(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidRecharge(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsMsToleranceRfd(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidMsToleranceRfd(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
	public boolean checkAcctItermsMsAdvanceRfd(Map<String, String> data){
		int i=1;
		boolean rasult=false;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rasult=ibatisCheckDealState.getEntryParamByOrderidMsAdvanceRfd(data);
			i++;
		}while ((false == rasult) && i < 50);
		
		return rasult;
	}
}
