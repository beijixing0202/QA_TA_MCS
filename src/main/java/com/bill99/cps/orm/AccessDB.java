package com.bill99.cps.orm;

import java.util.List;
//import java.util.Map;

import javax.annotation.Resource;

import shelper.environment.Environment;

import com.bill99.cps.common.dto.MgwInfoDto;
import com.bill99.cps.common.dto.TxnDBInfo;
import com.bill99.cps.common.tools.XmlParse;
import com.bill99.qa.framework.jdbc.TaDbHandller;
import shelper.db.DataSourceHolder;

public class AccessDB {
	@Resource
	private TaDbHandller taSeashellDbHandller;
	
	@Resource
	private TaDbHandller taMasposDbHandller;
	 
	
	
	public String querySMSFromDB(String phoneNO){
		return taSeashellDbHandller.queryForObject("seashellDB.getSmscontent", phoneNO);
		
	}
	public String getvalidCode(String phoneNO) {
		return XmlParse.getValidateCode("验证码：","，商户：",querySMSFromDB(phoneNO));
	}
	
	public TxnDBInfo getTxnDBInfo(String extTraceNo){
		DataSourceHolder.setDataSource(Environment.get("cps.maspos.dbtype"));
		return taMasposDbHandller.queryForObject("txnDB.getTxnInfo", extTraceNo);
		
	}  
	
	public boolean updateTermReconFlg(String idTxn){
		DataSourceHolder.setDataSource(Environment.get("cps.maspos.dbtype"));
		taMasposDbHandller.executeUpdate("txnDB.updateTermReconFlg", idTxn);
		
		int num=(Integer) taMasposDbHandller.queryForObject("txnDB.queryTermReconFlgUpdateResult", idTxn);
		
		return num>0;
		
	}
	
	public String purchaseDBcheck(String externalRefNumber){
		DataSourceHolder.setDataSource(Environment.get("cps.maspos.dbtype"));
		List<MgwInfoDto> mgwInfoDtoList=taMasposDbHandller.queryForList("txnDB.purchaseDBcheck", externalRefNumber);
		String queryResult="";
		for(int i=mgwInfoDtoList.size()-1;i>=0;i--){
			MgwInfoDto mgwInfoDto=mgwInfoDtoList.get(i);
			queryResult=queryResult+mgwInfoDto.getTxnFlg()+";"+mgwInfoDto.getTxnType()+";"+mgwInfoDto.getAppType()+";"+mgwInfoDto.getTunnelData()+";"+mgwInfoDto.getAuthNetId();
		}
		return queryResult;
		
	}
	
	
}
