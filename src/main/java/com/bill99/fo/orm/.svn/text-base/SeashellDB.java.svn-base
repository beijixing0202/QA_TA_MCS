package com.bill99.fo.orm;

import java.util.Map;

import com.bill99.ifs.common.dto.DealDto;
import com.bill99.ifs.common.dto.EntryDto;
import com.bill99.ifs.common.dto.PaymentOrderDto;

public interface SeashellDB {
	
	public String qryDealIdFrmDeal(String orderSeqId,String dealCode);
	
	//工单表withdrawworkorder查询工单状态
	public String qryStatusFrmWthdrwWrkrOrder(String orderSeqId);
	
	//查询paymentorder表的笔数
	public int qryCuntFrmPymentorder(String sequenceId,String orderAmount,String orderStatus,String orderCode);
	
	//查询deal表记账笔数
	public int qryCuntFrmDeal(String orderSeqId,String dealAmount,String dealStatus,String dealCode);
	
	//查询entry表记账笔数
	public int qryCuntFrmEntry(String dealId,String acctCode,String value,String crdr);
	
	//根据sequenceid查询paymentorder表
   public PaymentOrderDto getPaymentOrderByseq(String seq);
   
	//根据dealid查询deal表
   public DealDto getDealByDealid(String dealId);
   
	//根据dealid和crdr查询deal表
   public EntryDto getEntryByMap(Map<String, String>map);
}
