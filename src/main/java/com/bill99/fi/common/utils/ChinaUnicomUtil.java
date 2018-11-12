package com.bill99.fi.common.utils;

import java.util.Map;

import org.testng.Reporter;

import com.linkage.udp.request.PayReqObj;
import com.linkage.udp.service.UDPPayOrgService;
import com.linkage.udp.signEngine.CryptogramUtil;
import com.linkage.udp.signEngine.SignException;

public class ChinaUnicomUtil {

	public static String UnCreateSign(Map<String, String> data) {
		String objStr = null;
		PayReqObj obj = new PayReqObj();
		obj.setPayOrderID(data.get("PayOrderID"));
		obj.setPartnerID(data.get("PartnerID"));
		obj.setGoodsName(data.get("GoodsName"));
		obj.setGoodsDesc(data.get("GoodsDesc"));
		obj.setPayAmt(data.get("PayAmt"));
		obj.setPayTime(data.get("PayTime"));
		obj.setPageRedirectUrl(data.get("PageRedirectUrl"));
		obj.setServerToServerUrl(data.get("ServerToServerUrl"));
		obj.setBuyTel(data.get("BuyTel"));
		obj.setMP(data.get("MP"));
		obj.setPayChannelType(data.get("PayChannelType"));
		obj.setPayChannelID(data.get("PayChannelID"));
		obj.setMerchantsID(data.get("MerchantsID"));
		obj.setServiceType(data.get("ServiceType"));
		obj.setCurrency(data.get("Currency"));
		obj.setReserved2(data.get("Reserved2"));
		if(data.keySet().contains("IsPayByInstallment")){
			obj.setIsPayByInstallment(data.get("IsPayByInstallment"));
		}
		if(data.keySet().contains("Installments")){
			obj.setInstallments(data.get("Installments"));
		}
		try {
			objStr = UDPPayOrgService.createSignAndEncryptObject(obj, data.get("key"), "SHA", "DES");
		} catch (SignException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("加签失败！", false);
		}
		System.out.println("+++++++++objStr:"+objStr);
		return objStr;

	}
	public static String decryptSign(String strTobeDeCrypted,String strKey){
		
		String plainStr="";
		try {
			plainStr= CryptogramUtil.Decrypt(strTobeDeCrypted, strKey,  new byte[8]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("解密失败！", false);
		}
		
		return plainStr;
	}
	
	
	
	public static String unResponseBodyDispose(String responseBody){
		return	responseBody.split("recvMerchantInfoAction.htm'>|</FORM>")[1].replace("<INPUT TYPE='HIDDEN' NAME='", "").replace("' VALUE='", "=").replace("'/>", "&");
	}
}
