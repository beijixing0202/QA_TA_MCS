package com.bill99.ifs.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;


import com.alibaba.fastjson.JSONObject;
import com.bill99.fo.orm.SeashellDB;
import com.bill99.ifs.common.dto.DealDto;
import com.bill99.ifs.common.dto.EntryDto;
import com.bill99.ifs.common.dto.FcsTxnDto;
import com.bill99.ifs.common.dto.PEDto;
import com.bill99.ifs.common.dto.PaymentOrderDto;
import com.bill99.ifs.fcs.api.message.TxnInfo;
import com.bill99.ifs.orm.IfsDao;
import com.bill99.ifs.service.CheckIfsDbService;

public class CheckIfsDbServiceImpl implements CheckIfsDbService {
	public IfsDao ifsDao;
	public SeashellDB seashellDB;
	public IfsDao getIfsDao() {
		return ifsDao;
	}

	public void setIfsDao(IfsDao ifsDao) {
		this.ifsDao = ifsDao;
	}

	public SeashellDB getSeashellDB() {
		return seashellDB;
	}

	public void setSeashellDB(SeashellDB seashellDB) {
		this.seashellDB = seashellDB;
	}

	@Override
	public boolean checkConsumeService(Map<String, String> map) {
		FcsTxnDto fcsTxnDto = ifsDao.getfcsTxnByOutTradeNo(map.get("billOrderNo"));
		Reporter.log("fcsTxnDto=" + JSONObject.toJSONString(fcsTxnDto));
		if (!checkFcsConsume(map,fcsTxnDto)) {
			Reporter.log("消费落地fcs系统数据校验不通过！");
			return false;
		}
		Map<String, String> dbMap=new HashMap<String, String>();
		dbMap.put("accountNmbr", fcsTxnDto.getTxn_acct_no());
		dbMap.put("transactionCode", "6099");
		Map<String, Object>transactionMap=ifsDao.getTransactionByAcctNbrAndCode(dbMap);
		Reporter.log("transactionMap="+JSONObject.toJSONString(transactionMap));
		if(!checkWiseConsume(fcsTxnDto,transactionMap)){
			Reporter.log("消费落地wise系统数据校验不通过！");
			return false;
		}
		if (!"0".equals(fcsTxnDto.getFee_rate())) {
			if (!checkDpmConsume(fcsTxnDto, transactionMap)) {
				Reporter.log("消费落地Dpm系统数据校验不通过！");
				return false;
			}
		}else{
			Reporter.log("费率为0，无需交易dpm");
		}
		return true;
	}

	@Override
	public boolean checkRevokeService(Map<String, String> map) {
		Map<String, String> dbMap=new HashMap<String, String>();
		dbMap.put("billOrderNo", map.get("billOrderNo"));
		dbMap.put("functionCode", "11");
		FcsTxnDto fcsTxnDto = ifsDao.getfcsTxnByOutTradeNoAndType(dbMap);
		Reporter.log("fcsTxnDto=" + JSONObject.toJSONString(fcsTxnDto));
		if(!checkFcsRevoke(map, fcsTxnDto)){
			Reporter.log("撤销落地fcs系统数据校验不通过！");
			return false;
		}
		Map<String, String> dbTranMap=new HashMap<String, String>();
		dbTranMap.put("accountNmbr", fcsTxnDto.getTxn_acct_no());
		dbTranMap.put("transactionCode", "6199");
		Map<String, Object>transactionMap=ifsDao.getTransactionByAcctNbrAndCode(dbTranMap);
		Reporter.log("transactionMap="+JSONObject.toJSONString(transactionMap));
		if(!checkWiseRevoke(fcsTxnDto)){
			Reporter.log("撤销落地wise系统数据校验不通过！");
			return false;
		}
		if(!checkDpmRevoke(transactionMap, fcsTxnDto)){
			Reporter.log("撤销落地dpm系统数据校验不通过！");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkRefundService(Map<String, String> map) {
		Map<String, String> dbMap=new HashMap<String, String>();
		dbMap.put("billOrderNo", map.get("billOrderNo"));
		dbMap.put("functionCode", "12");
		FcsTxnDto fcsTxnDto = ifsDao.getfcsTxnByAcctNoAndType(dbMap);
		Reporter.log("fcsTxnDto="+JSONObject.toJSONString(fcsTxnDto));
		if(!checkFcsRefund(map, fcsTxnDto)){
			Reporter.log("退货落地fcs系统数据校验不通过！");
			return false;
		}
		Map<String, Object>transactionMap=new HashMap<String, Object>();
		if("12".equals(map.get("orgRefundStatus"))){
			Map<String, String> dbTranMap=new HashMap<String, String>();
			dbTranMap.put("accountNmbr", fcsTxnDto.getTxn_acct_no());
			dbTranMap.put("transactionCode", "6199");
			transactionMap=ifsDao.getTransactionByAcctNbrAndCode(dbTranMap);
		}

		Reporter.log("transactionMap="+JSONObject.toJSONString(transactionMap));

		if(!checkWiseRefund(fcsTxnDto)){
			Reporter.log("退货落地wise系统数据校验不通过！");
			return false;
		}
		if(!checkDpmRefund(transactionMap, fcsTxnDto)){
			Reporter.log("退货落地dpm系统数据校验不通过！");
			return false;
		}
		return true;
	}

	public boolean checkFcsConsume(Map<String, String> map,FcsTxnDto fcsTxnDto) {
		//校验各个状态的值
		if (!("10".equals(fcsTxnDto.getStatus())
				&& "20".equals(fcsTxnDto.getConsume_type())
				&& "10".equals(fcsTxnDto.getFunction_code())
				&& "2".equals(fcsTxnDto.getTxn_flag()) && map.get("orderType")
				.equals(fcsTxnDto.getOrder_type()))) {
			Reporter.log("校验各个状态不一致");
			return false;
		}
		//校验金额是否一致
		BigDecimal amtbDecimal=new BigDecimal(map.get("payAmount"));
		String amt=amtbDecimal.divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP).toString();
		if(!amt.equals(fcsTxnDto.getPay_amount())){
			Reporter.log("校验金额不一致");
			return false;
		}
		//校验额度类型是否一致
		if("110009".equals(map.get("orderType"))||"110015".equals(map.get("orderType"))){
			if(!"3".equals(fcsTxnDto.getUse_limit_type())){
				Reporter.log("校验额度类型不一致");
				return false;
			}
		}else{
			if(!"0".equals(fcsTxnDto.getUse_limit_type())){
				Reporter.log("校验额度类型不一致");
				return false;
			}
		}
		return true;
	}

	public boolean checkWiseConsume(FcsTxnDto fcsTxnDto,Map<String, Object> map) {
		String type=ifsDao.getAcct1TypeByAcctNbr(fcsTxnDto.getTxn_acct_no());
		String num=ifsDao.getAcct2CountByAcctNbr(fcsTxnDto.getTxn_acct_no());
		String amt=ifsDao.getApplicationAmtByAcctNbr(fcsTxnDto.getTxn_acct_no());
		String sumAmt=ifsDao.getInstallDetailSumAmtByAcctNbr(fcsTxnDto.getTxn_acct_no());
		Map<String, String>installMap=ifsDao.getInstallAmtAndStatusByAcctNbr(fcsTxnDto.getTxn_acct_no());
		Reporter.log("type="+type+"；num="+num+"；amt="+amt+"；sumAmt="+sumAmt+"；installMap="+JSONObject.toJSONString(installMap));
		//校验类型、条数、状态是否正确
		if(!(fcsTxnDto.getUse_limit_type().equals(type)&&"1".endsWith(num)&&"0".equals(installMap.get("STATUS")))){
			Reporter.log("校验类型、条数、状态不一致");
			return false;
		}
		//校验各项金额是否正确
		BigDecimal fcsAmt=new BigDecimal(fcsTxnDto.getPay_amount());
		BigDecimal feeAmt=fcsAmt.multiply(new BigDecimal(fcsTxnDto.getFee_rate()), new MathContext(2));
		BigDecimal tranAmt=(BigDecimal)map.get("TRAN_AMNT");
		if (!(fcsAmt.compareTo(new BigDecimal(amt)) == 0
				&& fcsAmt.compareTo(new BigDecimal(sumAmt)) == 0
				&& feeAmt.compareTo(tranAmt) == 0)) {
			Reporter.log("校验各项金额不一致");
			return false;
		}

		return true;
	}

	public boolean checkDpmConsume(FcsTxnDto fcsTxnDto,Map<String, Object> map) {
		Map<String, Object> acctReqMap=ifsDao.getAcctRequestByEntryId(map.get("REFERENCE_NMBR").toString());
		Reporter.log("acctReqMap="+JSONObject.toJSONString(acctReqMap));
		Map<String, Object> peTxnMap=ifsDao.getPeTxnByexternalTxnId(acctReqMap.get("ID_TXN_ACCT_REQUEST").toString());
		Reporter.log("peTxnMap="+JSONObject.toJSONString(peTxnMap));
		Map<String, Object>dataReqMap=ifsDao.getDataRequsetByAcctNbr(fcsTxnDto.getTxn_acct_no());
		Reporter.log("dataReqMap="+JSONObject.toJSONString(dataReqMap));
		Map<String, Object>txnLegMap=ifsDao.getTxnLegByAcctNbr(fcsTxnDto.getTxn_acct_no());
		Reporter.log("txnLegMap="+JSONObject.toJSONString(txnLegMap));

		//检查t_txn_acct_request表数据
		BigDecimal fcsAmt=new BigDecimal(fcsTxnDto.getPay_amount()).multiply(new BigDecimal(1000));
		BigDecimal fcsFee=fcsAmt.multiply(new BigDecimal(fcsTxnDto.getFee_rate()));
		BigDecimal txnAmt=new BigDecimal(acctReqMap.get("TXN_AMT").toString());
		if(!("S".equals(acctReqMap.get("ACCT_STATUS").toString())&&txnAmt.compareTo(fcsFee)==0)){
			Reporter.log("校验t_txn_acct_request表数据不一致");
			return false;
		}
		//检查t_atm_pe_txn pe表数据
		BigDecimal amount= (BigDecimal)peTxnMap.get("FUND_IN_AMOUNT");
		if(!("S".equals(peTxnMap.get("STATUS").toString())&&amount.compareTo(fcsFee)==0)){
			Reporter.log("校验t_atm_pe_txn pe表数据不一致");
			return false;
		}
		//检查t_txn_data_request表数据
		BigDecimal saleAmt=(BigDecimal)dataReqMap.get("SALES_AMT");
		BigDecimal discount=new BigDecimal(fcsTxnDto.getMerchant_discount());
		BigDecimal feeRate=(BigDecimal)dataReqMap.get("REDUCE_FEE_RATE");

		if (!( "S".equals(dataReqMap.get("STATUS").toString())
				&& saleAmt.compareTo(fcsAmt) == 0 && feeRate
					.compareTo(discount) == 0)){
			Reporter.log("校验t_txn_data_request表数据不一致");
			return false;
		}
			// 检查t_txn_clr_list_leg表数据
		BigDecimal fee=new BigDecimal("0");
		if("3".equals(fcsTxnDto.getUse_limit_type())){
			fee=fcsAmt.multiply(feeRate);
		}else{
			fee=fcsAmt.multiply(new BigDecimal("0.004"));
		}
		BigDecimal legFee=(BigDecimal)txnLegMap.get("FEE");
		BigDecimal stlAmt=fcsAmt.subtract(fee);
		BigDecimal legStlAmt=(BigDecimal)txnLegMap.get("STL_AMT");
		if (!( "N".equals(txnLegMap.get("ACCT_STATUS").toString())
				&& "1".equals(txnLegMap.get("VALID_FLAG").toString())
				&& legFee.compareTo(fee) == 0 && legStlAmt.compareTo(stlAmt) == 0)) {
			Reporter.log("fee="+fee);
			Reporter.log("校验t_txn_clr_list_leg表数据不一致");
			return false;
		}

		// 个人分期手续费记账检查
		PEDto feePeDto = new PEDto();
		feePeDto.setOrderCode("717");
		feePeDto.setDealCode("739");
		feePeDto.setPaymentServicePkgcode("776");
		feePeDto.setCrAcct("5151100010002");
		feePeDto.setDrAcct("1260100020001");
		if (!checkPe(peTxnMap, feePeDto)) {
			Reporter.log("个人分期手续费记账有误");
			return false;
		}
		return true;
	}

	public boolean checkFcsRevoke(Map<String, String> map,FcsTxnDto fcsTxnDto) {
		//校验撤销交易金额是否一致
				BigDecimal amtbDecimal=new BigDecimal(map.get("payAmount"));
				String amt=amtbDecimal.divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP).toString();
				if(!amt.equals(fcsTxnDto.getPay_amount())){
					Reporter.log("校验撤销交易金额不一致");
					return false;
				}
				//校验撤销交易状态是否正确
				if(!("10".equals(fcsTxnDto.getStatus())&&"31".equals(fcsTxnDto.getConsume_type()))){
					Reporter.log("校验撤销交易状态不一致");
					return false;
				}
				//校验原始交易的状态是否正确
				Map<String, String> dbMap=new HashMap<String, String>();
				dbMap.put("billOrderNo", map.get("billOrderNo"));
				dbMap.put("functionCode", "10");
				FcsTxnDto orgFcsTxnDto = ifsDao.getfcsTxnByOutTradeNoAndType(dbMap);
				Reporter.log("orgFcsTxnDto=" + JSONObject.toJSONString(fcsTxnDto));
				if(!"11".equals(orgFcsTxnDto.getStatus())){
					Reporter.log("校验原始交易的状态不一致");
					return false;
				}
		return true;
	}

	public boolean checkWiseRevoke(FcsTxnDto fcsTxnDto) {
		Map<String, String> dbMap=new HashMap<String, String>();
		dbMap.put("accountNmbr", fcsTxnDto.getTxn_acct_no());
		dbMap.put("billingFlag", "04");
		dbMap.put("pymtFlag", "02");
		int count=ifsDao.getCountTranDetByMap(dbMap);
		Reporter.log("count="+count);
		if(0<count){
			Reporter.log("LM_INSTALLMENT_TRAN_D分期主表会更改状态不一致");
			return false;
		}
		return true;
	}

	public boolean checkDpmRevoke(Map<String, Object> map,FcsTxnDto fcsTxnDto){
		Map<String, Object>dataReqMap=ifsDao.getDataRequsetByRef(fcsTxnDto.getId());
		Reporter.log("dataReqMap="+JSONObject.toJSONString(dataReqMap));
		Map<String, Object>txnLegMap=ifsDao.getTxnLegByAcctRef(fcsTxnDto.getId());
		Reporter.log("txnLegMap="+JSONObject.toJSONString(txnLegMap));
		Map<String, Object> acctReqMap=ifsDao.getAcctRequestByEntryId(map.get("REFERENCE_NMBR").toString());
		Reporter.log("acctReqMap="+JSONObject.toJSONString(acctReqMap));
		Map<String, Object> peTxnMap=ifsDao.getPeTxnByexternalTxnId(acctReqMap.get("ID_TXN_ACCT_REQUEST").toString());
		Reporter.log("peTxnMap="+JSONObject.toJSONString(peTxnMap));

		BigDecimal fcsAmt=new BigDecimal(fcsTxnDto.getPay_amount()).multiply(new BigDecimal(1000));
		BigDecimal fcsFee=(BigDecimal)map.get("TRAN_AMNT");
	     fcsFee=fcsFee.multiply(new BigDecimal(1000));
		BigDecimal saleAmt=(BigDecimal)dataReqMap.get("SALES_AMT");
		if(!(fcsAmt.compareTo(saleAmt)==0&&"4101".equals(dataReqMap.get("TXN_TYPE"))&&"S".equals(dataReqMap.get("STATUS")))){
			Reporter.log("校验t_txn_data_request表不一致");
			return false;
		}
		if(!("4101".equals(txnLegMap.get("TXN_TYPE"))&&"N".equals(txnLegMap.get("ACCT_STATUS"))&&"1".equals(txnLegMap.get("VALID_FLAG")))){
			Reporter.log("校验t_txn_clr_list_leg表不一致");
			return false;
		}
		BigDecimal acctAmt=(BigDecimal)acctReqMap.get("TXN_AMT");
		if(!("S".equals(acctReqMap.get("ACCT_STATUS"))&&fcsFee.compareTo(acctAmt)==0)){
			Reporter.log("校验t_txn_acct_request表不一致");
			return false;
		}
		BigDecimal amount= (BigDecimal)peTxnMap.get("FUND_IN_AMOUNT");
		if(!("S".equals(peTxnMap.get("STATUS").toString())&&amount.compareTo(fcsFee)==0)){
			Reporter.log("校验t_atm_pe_txn pe表数据不一致");
			return false;
		}

		//退货返还个人分期手续费记账检查
		PEDto feePeDto=new PEDto();
		feePeDto.setOrderCode("723");
		feePeDto.setDealCode("753");
		feePeDto.setPaymentServicePkgcode("790");
		feePeDto.setCrAcct("1260100020001");
		feePeDto.setDrAcct("5151100010002");
		if (!checkPe(peTxnMap, feePeDto)) {
			Reporter.log("退货返还个人分期手续费记账有误");
			return false;
		}
		return true;
	}

	public boolean checkFcsRefund(Map<String, String> map,FcsTxnDto fcsTxnDto) {
		//校验退货交易金额是否一致
		BigDecimal amtbDecimal=new BigDecimal(map.get("payAmount"));
		String amt=amtbDecimal.divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP).toString();
		if(!amt.equals(fcsTxnDto.getPay_amount())){
			Reporter.log("校验撤销交易金额不一致");
			return false;
		}
		//校验退货交易状态是否正确
		String status="";
		if("32".equals(map.get("orgRefundStatus"))){
			status="20";
		}else{
			status="10";
		}
		if(!(status.equals(fcsTxnDto.getStatus())&&"30".equals(fcsTxnDto.getConsume_type()))){
			Reporter.log("校验撤销交易状态不一致");
			return false;
		}
		//校验原始交易的状态是否正确
		Map<String, String> dbMap=new HashMap<String, String>();
		dbMap.put("billOrderNo", map.get("billOrderNo"));
		dbMap.put("functionCode", "10");
		FcsTxnDto orgFcsTxnDto = ifsDao.getfcsTxnByAcctNoAndType(dbMap);
		Reporter.log("orgFcsTxnDto=" + JSONObject.toJSONString(fcsTxnDto));
		if(!map.get("orgRefundStatus").equals(orgFcsTxnDto.getStatus())){
			Reporter.log("校验原始交易的状态不一致");
			return false;
		}
		return true;
	}

	public boolean checkWiseRefund(FcsTxnDto fcsTxnDto) {
		Map<String, String> dbMap=new HashMap<String, String>();
		dbMap.put("accountNmbr", fcsTxnDto.getTxn_acct_no());
		dbMap.put("billingFlag", "04");
		dbMap.put("pymtFlag", "02");
		int count=ifsDao.getCountTranDetByMap(dbMap);
		Reporter.log("count="+count);
		if(0<count){
			Reporter.log("LM_INSTALLMENT_TRAN_D分期主表会更改状态不一致");
			return false;
		}
		return true;
	}

	public boolean checkDpmRefund(Map<String, Object> map,FcsTxnDto fcsTxnDto){
		Map<String, Object>dataReqMap=ifsDao.getDataRequsetByRef(fcsTxnDto.getId());
		Reporter.log("dataReqMap="+JSONObject.toJSONString(dataReqMap));
		BigDecimal fcsAmt=new BigDecimal(fcsTxnDto.getPay_amount()).multiply(new BigDecimal(1000));
		BigDecimal saleAmt=(BigDecimal)dataReqMap.get("SALES_AMT");
		String txnType="";
		if("110009".equals(fcsTxnDto.getOrder_type())){
			txnType="1001";
		}else {
			txnType="2001";
		}
		if(!(fcsAmt.compareTo(saleAmt)==0&&txnType.equals(dataReqMap.get("TXN_TYPE"))&&"S".equals(dataReqMap.get("STATUS")))){
			Reporter.log("校验t_txn_data_request表不一致");
			return false;
		}
		if(!map.isEmpty()){
			Map<String, Object> acctReqMap = ifsDao.getAcctRequestByEntryId(map.get("REFERENCE_NMBR").toString());
			Reporter.log("acctReqMap=" + JSONObject.toJSONString(acctReqMap));
			Map<String, Object> peTxnMap = ifsDao.getPeTxnByexternalTxnId(acctReqMap.get("ID_TXN_ACCT_REQUEST").toString());
			Reporter.log("peTxnMap=" + JSONObject.toJSONString(peTxnMap));

			BigDecimal fcsFee = (BigDecimal) map.get("TRAN_AMNT");
			fcsFee = fcsFee.multiply(new BigDecimal(1000));
			BigDecimal acctAmt = (BigDecimal) acctReqMap.get("TXN_AMT");
			if (!("S".equals(acctReqMap.get("ACCT_STATUS")) && fcsFee.compareTo(acctAmt) == 0)) {
				Reporter.log("校验t_txn_acct_request表不一致");
				return false;
			}
			BigDecimal amount = (BigDecimal) peTxnMap.get("FUND_IN_AMOUNT");
			if (!("S".equals(peTxnMap.get("STATUS").toString()) && amount.compareTo(fcsFee) == 0)) {
				Reporter.log("校验t_atm_pe_txn pe表数据不一致");
				return false;
			}

			// 退货返还个人分期手续费记账检查
			PEDto feePeDto = new PEDto();
			feePeDto.setOrderCode("723");
			feePeDto.setDealCode("753");
			feePeDto.setPaymentServicePkgcode("790");
			feePeDto.setCrAcct("1260100020001");
			feePeDto.setDrAcct("5151100010002");
			if (!checkPe(peTxnMap, feePeDto)) {
				Reporter.log("退货返还个人分期手续费记账有误");
				return false;
			}
		}
		//判断是否现金分期交易，如果是走现金分期记账流程，否则走普通交易流程
		if("110009".equals(fcsTxnDto.getOrder_type())){
			Map<String, Object>txnLegMap=ifsDao.getTxnLegByAcctRef(fcsTxnDto.getId());
			Reporter.log("txnLegMap="+JSONObject.toJSONString(txnLegMap));
			if(!("1001".equals(txnLegMap.get("TXN_TYPE"))&&"S".equals(txnLegMap.get("ACCT_STATUS"))&&"0".equals(txnLegMap.get("VALID_FLAG")))){
				Reporter.log("校验t_txn_clr_list_leg表不一致");
				return false;
			}
			Map<String, Object> cashBankMap = ifsDao.getPeTxnByexternalTxnId(txnLegMap.get("ID").toString()+ "_CASHBACK");
			Reporter.log("peTxnMap=" + JSONObject.toJSONString(cashBankMap));

			//现金贷交易失败退货记账检查
			PEDto cashPeDto = new PEDto();
			// 将pe的检查预期值塞到peDto中
			cashPeDto.setOrderCode("723");
			cashPeDto.setDealCode("895");
			cashPeDto.setPaymentServicePkgcode("1108");
			cashPeDto.setCrAcct("1280100020001");
			cashPeDto.setDrAcct("1011100010844");
			if (!checkPe(cashBankMap, cashPeDto)) {
				Reporter.log("现金贷交易失败退货记账有误");
				return false;
			}
		}else{
			Map<String, String>daoMap=new HashMap<String, String>();
			//检查退货交易
			daoMap.put("source_ref", fcsTxnDto.getId());
			daoMap.put("type", "2001");
			Map<String, Object>refundTxnLegMap=ifsDao.getTxnLegByRefAndType(daoMap);
			Reporter.log("refundTxnLegMap="+JSONObject.toJSONString(refundTxnLegMap));
			if(!("S".equals(refundTxnLegMap.get("ACCT_STATUS"))&&"0".equals(refundTxnLegMap.get("VALID_FLAG")))){
				Reporter.log("退货交易校验t_txn_clr_list_leg表不一致");
				return false;
			}
			Map<String, Object> refundTxnPeMap = ifsDao.getPeTxnByexternalTxnId(refundTxnLegMap.get("ID").toString());
			Reporter.log("refundTxnPeMap=" + JSONObject.toJSONString(refundTxnPeMap));
			PEDto txnPeDto = new PEDto();
			txnPeDto.setOrderCode("723");
			txnPeDto.setDealCode("752");
			txnPeDto.setPaymentServicePkgcode("789");
			txnPeDto.setCrAcct("1212100010001");
			txnPeDto.setDrAcct("1280100010001");
			if (!checkPe(refundTxnPeMap, txnPeDto)) {
				Reporter.log("退货交易记账有误");
				return false;
			}

			//检查收支分离
			daoMap.put("type", "9998");
			Map<String, Object>refundStlLegMap=ifsDao.getTxnLegByRefAndType(daoMap);
			Reporter.log("refundStlLegMap="+JSONObject.toJSONString(refundStlLegMap));
			if(!("S".equals(refundStlLegMap.get("ACCT_STATUS"))&&"0".equals(refundStlLegMap.get("VALID_FLAG")))){
				Reporter.log("收支分离校验t_txn_clr_list_leg表不一致");
				return false;
			}
			Map<String, Object> refundStlPeMap = ifsDao.getPeTxnByexternalTxnId(refundStlLegMap.get("ID").toString());
			Reporter.log("refundStlPeMap=" + JSONObject.toJSONString(refundStlPeMap));
			PEDto stlPeDto = new PEDto();
			stlPeDto.setOrderCode("724");
			stlPeDto.setDealCode("850");
			stlPeDto.setPaymentServicePkgcode("880");
			stlPeDto.setCrAcct("1280100010001");
			stlPeDto.setDrAcct("1011100010844");
			if (!checkPe(refundStlPeMap, stlPeDto)) {
				Reporter.log("收支分离记账有误");
				return false;
			}

			//检查贴息、手续费转入收入户
			daoMap.put("type", "9993");
			Map<String, Object>refundfeeLegMap=ifsDao.getTxnLegByRefAndType(daoMap);
			Reporter.log("refundfeeLegMap="+JSONObject.toJSONString(refundfeeLegMap));
			if(!("N".equals(refundfeeLegMap.get("ACCT_STATUS"))&&"0".equals(refundStlLegMap.get("VALID_FLAG")))){
				Reporter.log("贴息、手续费转入收入户校验t_txn_clr_list_leg表不一致");
				return false;
			}
		}
		return true;
	}


	public boolean checkPe(Map<String, Object> peTxnMap,PEDto peDto){
		//检查PE相关数据
		BigDecimal amount= (BigDecimal)peTxnMap.get("FUND_IN_AMOUNT");
		PaymentOrderDto paymentOrderDto = seashellDB.getPaymentOrderByseq(peTxnMap.get("ORDER_SEQ_ID").toString());
		Reporter.log("paymentOrderDto="+ JSONObject.toJSONString(paymentOrderDto));
		DealDto dealDto = seashellDB.getDealByDealid(peTxnMap.get("DEAL_ID").toString());
		Reporter.log("dealDto=" + JSONObject.toJSONString(dealDto));
		// 设置查看entry表的参数，根据借贷方向查各个分录
		Map<String, String> crMap = new HashMap<String, String>();
		crMap.put("dealid", peTxnMap.get("DEAL_ID").toString());
		crMap.put("crdr", "2");
		Map<String, String> drMap = new HashMap<String, String>();
		drMap.put("dealid", peTxnMap.get("DEAL_ID").toString());
		drMap.put("crdr", "1");
		EntryDto crEntryDto = seashellDB.getEntryByMap(crMap);
		EntryDto drEntryDto = seashellDB.getEntryByMap(drMap);
		Reporter.log("crEntryDto=" + JSONObject.toJSONString(crEntryDto));
		Reporter.log("drEntryDto=" + JSONObject.toJSONString(drEntryDto));

		// 检查表paymentOrder数据
		if (!(peDto.getOrderCode().equals(paymentOrderDto.getOrderCode()) && amount
				.compareTo(new BigDecimal(paymentOrderDto.getOrderAmount())) == 0)) {
			Reporter.log("校验paymentOrder表数据不一致");
			return false;
		}
		// 检查表deal数据
		if (!(peDto.getDealCode().equals(dealDto.getDealCode())
				&& peDto.getPaymentServicePkgcode().equals(
						dealDto.getPaymentServicePkgcode()) && amount
					.compareTo(new BigDecimal(dealDto.getDealAmount())) == 0)) {
			Reporter.log("校验deal表数据不一致");
			return false;
		}
		// 检查表entry数据
		if (!(peDto.getCrAcct().equals(crEntryDto.getAcctcode())
				&& amount.compareTo(new BigDecimal(crEntryDto.getValue())) == 0
				&& peDto.getDrAcct().equals(drEntryDto.getAcctcode()) && amount
					.compareTo(new BigDecimal(drEntryDto.getValue())) == 0)) {
			Reporter.log("校验entry表数据不一致");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkRepayAllService(TxnInfo txnInfo) {

		if(!checkFcsRepay(txnInfo)){
			Reporter.log("全部还款后落地fcs数据校验不通过！");
			return false;
		}
		List<Map<String, Object>>transactionList=getRepayTransactionList(txnInfo);
		if(!checkWiseRepayAll(txnInfo)){
			Reporter.log("全部还款后落地wise数据校验不通过！");
			return false;
		}
		if(!checkDpmRepay(txnInfo,transactionList)){
			Reporter.log("全部还款后落地dpm数据校验不通过！");
			return false;
		}
		return true;
	}
	public boolean checkFcsRepay(TxnInfo txnInfo){
		FcsTxnDto fcsTxnDto=ifsDao.getfcsTxnByTxnAcctNo(txnInfo.getTxnAcctNo());
		Reporter.log("fcsTxnDto=" + JSONObject.toJSONString(fcsTxnDto));
		String status="";
		if(txnInfo.getCurrentStage()<0){
			status="20";
		}else if(new BigDecimal(txnInfo.getCurrentStage()).compareTo(new BigDecimal(fcsTxnDto.getStages()))==0){
			status="20";
		}else{
			status="21";
		}
		if(!status.equals(fcsTxnDto.getStatus())){
			return false;
		}
		return true;
	}
	public List<Map<String, Object>> getRepayTransactionList(TxnInfo txnInfo){
		List<Map<String, Object>>transactionList=new ArrayList<Map<String, Object>>();
		Map<String, String> dbMap=new HashMap<String, String>();
		dbMap.put("accountNmbr", txnInfo.getTxnAcctNo());
		dbMap.put("transactionCode", "2020");
		Map<String, Object>transactionPaymentMap=ifsDao.getTransactionByAcctNbrAndCode(dbMap);
		Reporter.log("transactionPaymentMap="+JSONObject.toJSONString(transactionPaymentMap));
		transactionList.add(transactionPaymentMap);
		BigDecimal paymentAmt=new BigDecimal(transactionPaymentMap.get("TRAN_AMNT").toString());
		BigDecimal feeAmt=new BigDecimal(0);
		BigDecimal overFeeAmt=new BigDecimal(0);
		BigDecimal totalAmt=new BigDecimal(txnInfo.getRepayAmt()).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(totalAmt.compareTo(paymentAmt)>0){
			dbMap.put("transactionCode", "2062");
			Map<String, Object>transactionFeeMap=ifsDao.getTransactionByAcctNbrAndCode(dbMap);
			if(!(transactionFeeMap==null)){
				Reporter.log("transactionFeeMap="+JSONObject.toJSONString(transactionPaymentMap));
				transactionList.add(transactionFeeMap);
				feeAmt=new BigDecimal(transactionFeeMap.get("TRAN_AMNT").toString());
			}

			if(totalAmt.compareTo(paymentAmt.add(feeAmt))>0){
				dbMap.put("transactionCode", "2061");
				Map<String, Object>transactionOverFeeMap=ifsDao.getTransactionByAcctNbrAndCode(dbMap);
				if(!(transactionOverFeeMap==null)){
					Reporter.log("transactionOverFeeMap="+JSONObject.toJSONString(transactionOverFeeMap));
					transactionList.add(transactionOverFeeMap);
					overFeeAmt=new BigDecimal(transactionOverFeeMap.get("TRAN_AMNT").toString());
				}

			}
		}
		Reporter.log("lm_transaction的记账金额校验！",totalAmt.compareTo(paymentAmt.add(feeAmt).add(overFeeAmt))==0);
		return transactionList;
	}
	public boolean checkWiseRepayAll(TxnInfo txnInfo){
		int num=ifsDao.getRepayInstallDetailCountByAcctNbr(txnInfo.getTxnAcctNo());
		Reporter.log("num="+num);
		if(num>0){
			Reporter.log("LM_INSTALLMENT_TRAN_D表的状态验证不通过！");
			return false;
		}
		return true;
	}
	public boolean checkWiseRepayPart(TxnInfo txnInfo){
		Map<String, String>map=new HashMap<String, String>();
		map.put("acctNbr", txnInfo.getTxnAcctNo());
		map.put("term", txnInfo.getCurrentStage().toString());
		int num=ifsDao.getCountInstallDetailByAcctNbrAndTerm(map);
		Reporter.log("num="+num);
		if(num>0){
			Reporter.log("LM_INSTALLMENT_TRAN_D表的状态验证不通过！");
			return false;
		}
		return true;
	}
	public boolean checkDpmRepay(TxnInfo txnInfo,List<Map<String, Object>> list){
		for(int i=0;i<list.size();i++){
			Map<String, Object> acctReqMap=ifsDao.getAcctRequestByEntryId(list.get(i).get("REFERENCE_NMBR").toString());
			Reporter.log(list.get(i).get("REFERENCE_NMBR")+"acctReqMap="+JSONObject.toJSONString(acctReqMap));
			Map<String, Object> peTxnMap=ifsDao.getPeTxnByexternalTxnId(acctReqMap.get("ID_TXN_ACCT_REQUEST").toString());
			Reporter.log(list.get(i).get("REFERENCE_NMBR")+"peTxnMap="+JSONObject.toJSONString(peTxnMap));
			//检查t_txn_acct_request表数据
			BigDecimal wiseAmt=new BigDecimal(list.get(i).get("TRAN_AMNT").toString()).multiply(new BigDecimal(1000));
			BigDecimal txnAmt=new BigDecimal(acctReqMap.get("TXN_AMT").toString());
			if(!("S".equals(acctReqMap.get("ACCT_STATUS").toString())&&txnAmt.compareTo(wiseAmt)==0)){
				Reporter.log("校验t_txn_acct_request表数据不一致");
				return false;
			}
			//检查t_atm_pe_txn pe表数据
			BigDecimal amount= (BigDecimal)peTxnMap.get("FUND_IN_AMOUNT");
			if(!("S".equals(peTxnMap.get("STATUS").toString())&&amount.compareTo(wiseAmt)==0)){
				Reporter.log("校验t_atm_pe_txn pe表数据不一致");
				return false;
			}
			// 还款记账检查
			PEDto PeDto = new PEDto();
			if("2020".equals(list.get(i).get("TRANSACTION_CODE").toString())){
				PeDto.setOrderCode("720");
				PeDto.setDealCode("870");
				PeDto.setPaymentServicePkgcode("1022");
				PeDto.setCrAcct("1212100010001");
				PeDto.setDrAcct("1022100013126");

			}else if ("2062".equals(list.get(i).get("TRANSACTION_CODE").toString())){
				PeDto.setOrderCode("721");
				PeDto.setDealCode("871");
				PeDto.setPaymentServicePkgcode("1023");
				PeDto.setCrAcct("1260100020001");
				PeDto.setDrAcct("1022100013126");
			}else {
				PeDto.setOrderCode("722");
				PeDto.setDealCode("874");
				PeDto.setPaymentServicePkgcode("1025");
				PeDto.setCrAcct("1260100020002");
				PeDto.setDrAcct("1022100013126");
			}
			if (!checkPe(peTxnMap, PeDto)) {
				Reporter.log("还款记账有误");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkRepayTermService(TxnInfo txnInfo) {

		if(!checkFcsRepay(txnInfo)){
			Reporter.log("全部还款后落地fcs数据校验不通过！");
			return false;
		}
		List<Map<String, Object>>transactionList=getRepayTransactionList(txnInfo);
		if(!checkWiseRepayPart(txnInfo)){
			Reporter.log("全部还款后落地wise数据校验不通过！");
			return false;
		}
		if(!checkDpmRepay(txnInfo,transactionList)){
			Reporter.log("全部还款后落地dpm数据校验不通过！");
			return false;
		}
		return true;
	}
}
