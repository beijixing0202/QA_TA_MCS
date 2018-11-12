package com.bill99.fo.orm.impl;

import java.util.HashMap;
import java.util.Map;

import com.bill99.fo.orm.SeashellDB;
import com.bill99.ifs.common.dto.DealDto;
import com.bill99.ifs.common.dto.EntryDto;
import com.bill99.ifs.common.dto.PaymentOrderDto;
import com.bill99.qa.framework.jdbc.TaDbHandller;

public class SeashellDBImpl implements SeashellDB {
	
	TaDbHandller taSeashellDbHandller;
	
	public void setTaSeashellDbHandller(TaDbHandller taSeashellDbHandller) {
		this.taSeashellDbHandller = taSeashellDbHandller;
	}


	@Override
	public String qryDealIdFrmDeal(String orderSeqId, String dealCode) {
		// TODO Auto-generated method stub
		Map<String, String> mapPara =new HashMap<String,String>();
		mapPara.put("orderSeqId", orderSeqId);
		mapPara.put("dealCode", dealCode);		
		return taSeashellDbHandller.queryForObject("seashellDB.qryDealIdFrmDeal", mapPara);
	}
	
	@Override
	public String qryStatusFrmWthdrwWrkrOrder(String orderSeqId){
		return taSeashellDbHandller.queryForObject("seashellDB.qryStatusFrmWthdrwWrkrOrder", orderSeqId);
		
	}
	
	@Override
	public int qryCuntFrmPymentorder(String sequenceId,String orderAmount,String orderStatus,String orderCode){
		Map<String, String> mapPara =new HashMap<String,String>();
		mapPara.put("sequenceId", sequenceId);
		mapPara.put("orderAmount", orderAmount);
		mapPara.put("orderStatus", orderStatus);
		mapPara.put("orderCode", orderCode);
		return taSeashellDbHandller.queryForObject("seashellDB.qryCuntFrmPymentorder", mapPara);		
	}
	
	@Override
	public int qryCuntFrmDeal(String orderSeqId,String dealAmount,String dealStatus,String dealCode){
		Map<String, String> mapPara =new HashMap<String,String>();
		mapPara.put("orderSeqId", orderSeqId);
		mapPara.put("dealAmount", dealAmount);
		mapPara.put("dealStatus", dealStatus);
		mapPara.put("dealCode", dealCode);
		return taSeashellDbHandller.queryForObject("seashellDB.qryCuntFrmDeal", mapPara);
	}
	
	@Override
	public int qryCuntFrmEntry(String dealId,String acctCode,String value,String crdr){
		Map<String, String> mapPara =new HashMap<String,String>();
		mapPara.put("dealId", dealId);
		mapPara.put("acctCode", acctCode);
		mapPara.put("value", value);
		mapPara.put("crdr", crdr);
		return taSeashellDbHandller.queryForObject("seashellDB.qryCuntFrmEntry", mapPara);		
	}


	@Override
	public PaymentOrderDto getPaymentOrderByseq(String seq) {
		// TODO Auto-generated method stub
		return taSeashellDbHandller.queryForObject("seashellDB.getPaymentOrderByseq", seq);
	}


	@Override
	public DealDto getDealByDealid(String dealId) {
		// TODO Auto-generated method stub
		return taSeashellDbHandller.queryForObject("seashellDB.getDealByDealid", dealId);
	}


	@Override
	public EntryDto getEntryByMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		return taSeashellDbHandller.queryForObject("seashellDB.getEntryByMap", map);
	}

}
