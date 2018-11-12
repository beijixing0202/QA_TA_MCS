package com.bill99.ifs.orm.impl;

import java.util.Map;

import com.bill99.ifs.common.dto.FcsTxnDto;
import com.bill99.ifs.orm.IfsDao;
import com.bill99.qa.framework.jdbc.TaDbHandller;

public class IfsDaoImpl implements IfsDao {

	public TaDbHandller taWiseDbHandller;
	public void setTaWiseDbHandller(TaDbHandller taWiseDbHandller) {
		this.taWiseDbHandller = taWiseDbHandller;
	}
	@Override
	public FcsTxnDto getfcsTxnByOutTradeNo(String billOrderNo) {
		return taWiseDbHandller.queryForObject("wise.getfcsTxnByOutTradeNo", billOrderNo);
	}
	@Override
	public String getAcct1TypeByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getAcct1TypeByAcctNbr", acctNbr);
	}
	@Override
	public String getAcct2CountByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getAcct2CountByAcctNbr", acctNbr);
	}
	@Override
	public String getApplicationAmtByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getApplicationAmtByAcctNbr", acctNbr);
	}
	@Override
	public Map<String, String> getInstallAmtAndStatusByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getInstallAmtAndStatusByAcctNbr", acctNbr);
	}
	@Override
	public String getInstallDetailSumAmtByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getInstallDetailSumAmtByAcctNbr", acctNbr);
	}
	@Override
	public Map<String, Object> getTransactionByAcctNbrAndCode(Map<String, String> map) {
		return taWiseDbHandller.queryForObject("wise.getTransactionByAcctNbrAndCode", map);
	}
	@Override
	public Map<String, Object> getAcctRequestByEntryId(String entryId) {
		return taWiseDbHandller.queryForObject("wise.getAcctRequestByEntryId", entryId);
	}
	@Override
	public Map<String, Object> getDataRequsetByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getDataRequsetByAcctNbr", acctNbr);
	}
	@Override
	public Map<String, Object> getTxnLegByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getTxnLegByAcctNbr", acctNbr);
	}
	@Override
	public Map<String, Object> getPeTxnByexternalTxnId(String externalTxnId) {
		return taWiseDbHandller.queryForObject("wise.getPeTxnByexternalTxnId", externalTxnId);
	}
	@Override
	public FcsTxnDto getfcsTxnByOutTradeNoAndType(Map<String, String> map) {
		return taWiseDbHandller.queryForObject("wise.getfcsTxnByOutTradeNoAndType", map);
	}
	@Override
	public int getCountTranDetByMap(Map<String, String> map) {
		return taWiseDbHandller.queryForObject("wise.getCountTranDetByMap", map);
	}
	@Override
	public Map<String, Object> getDataRequsetByRef(String ref) {
		return taWiseDbHandller.queryForObject("wise.getDataRequsetByRef", ref);
	}
	@Override
	public Map<String, Object> getTxnLegByAcctRef(String ref) {
		return taWiseDbHandller.queryForObject("wise.getTxnLegByRef", ref);
	}
	@Override
	public FcsTxnDto getRefundTxnByMemberAndType(Map<String, String> map) {
		return taWiseDbHandller.queryForObject("wise.getRefundTxnByMemberAndType", map);
	}
	@Override
	public void updateCashDrawByRef(String ref) {
		taWiseDbHandller.executeUpdate("wise.updateCashDrawByRef", ref);
	}
	@Override
	public FcsTxnDto getfcsTxnByAcctNoAndType(Map<String, String> map) {
		return taWiseDbHandller.queryForObject("wise.getfcsTxnByAcctNoAndType", map);
	}
	@Override
	public Map<String, Object> getTxnLegByRefAndType(Map<String, String> map) {
		return taWiseDbHandller.queryForObject("wise.getTxnLegByRefAndType", map);
	}
	@Override
	public void updateCashDrawByMemberCode(String MemberCode) {
		taWiseDbHandller.executeUpdate("wise.updateCashDrawByMemberCode", MemberCode);
	}
	@Override
	public FcsTxnDto getfcsTxnByTxnAcctNo(String txnAcctNo) {
		return taWiseDbHandller.queryForObject("wise.getfcsTxnByTxnAcctNo", txnAcctNo);
	}
	@Override
	public int getRepayInstallDetailCountByAcctNbr(String acctNbr) {
		return taWiseDbHandller.queryForObject("wise.getRepayInstallDetailCountByAcctNbr", acctNbr);
	}
	@Override
	public int getCountInstallDetailByAcctNbrAndTerm(Map<String, String> map) {
		return taWiseDbHandller.queryForObject("wise.getCountInstallDetailByAcctNbrAndTerm", map);
	}

	
}
