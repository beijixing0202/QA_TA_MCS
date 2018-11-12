package com.bill99.ifs.orm;

import java.util.Map;

import com.bill99.ifs.common.dto.FcsTxnDto;

public interface IfsDao {

	/**
	 * 根据txnAcctNo查询fcs_txn表信息
	 * @param txnAcctNo
	 * @return
	 */
	public FcsTxnDto getfcsTxnByTxnAcctNo(String txnAcctNo);
	
	/**
	 * 根据outTradeNo查询fcs_txn表信息
	 * @param outTradeNo
	 * @return
	 */
	public FcsTxnDto getfcsTxnByOutTradeNo(String outTradeNo);
	
	/**
	 * 根据tradeId查询fcs_txn表信息
	 * @param tradeId
	 * @return
	 */
	public FcsTxnDto getfcsTxnByOutTradeNoAndType(Map<String, String> map);
	/**
	 * 根据member_code和use_limit_type查询fcs_txn表可以退货的数据信息
	 * @param map
	 * @return
	 */
	public FcsTxnDto getRefundTxnByMemberAndType(Map<String, String> map);
	
	/**
	 * 根据outTradeNo查询fcs_txn表的退货交易信息
	 * @param outTradeNo
	 * @return
	 */
	public FcsTxnDto getfcsTxnByAcctNoAndType(Map<String, String> map);
	
	/**
	 * 根据account_nmbr查看表LM_ACCOUNT1的TRANSACTION_TYPE字段
	 * @param acctNbr
	 * @return
	 */
	public String getAcct1TypeByAcctNbr(String acctNbr);
	
	/**
	 * 根据account_nmbr查看表LM_ACCOUNT2的条数
	 * @param acctNbr
	 * @return
	 */
	public String getAcct2CountByAcctNbr(String acctNbr);
	
	/**
	 * 根据account_nmbr查看表LM_APPLICATION的tran_amt字段
	 * @param acctNbr
	 * @return
	 */
	public String getApplicationAmtByAcctNbr(String acctNbr);
	
	/**
	 * 根据account_nmbr查看表LM_INSTALLMENT_TRAN的principal和status字段
	 * @param acctNbr
	 * @return
	 */
	public Map<String, String> getInstallAmtAndStatusByAcctNbr(String acctNbr);
	
	/**
	 * 根据account_nmbr查看表LM_INSTALLMENT_TRAN_D的sum(tran_amt)字段
	 * @param acctNbr
	 * @return
	 */
	public String getInstallDetailSumAmtByAcctNbr(String acctNbr);
	/**
	 * 根据account_nmbr查看表LM_INSTALLMENT_TRAN_D的非还款状态的个数
	 * @param acctNbr
	 * @return
	 */
	public int getRepayInstallDetailCountByAcctNbr(String acctNbr);
	/**
	 * 根据account_nmbr查看表lm_transaction的tran_amnt和reference_nmbr字段
	 * @param acctNbr
	 * @return
	 */
	public Map<String, Object> getTransactionByAcctNbrAndCode(Map<String, String> map);
	
	/**
	 * 根据entryId查看表t_txn_acct_request的id，txn_amt，acct_status字段
	 * @param entryId
	 * @return
	 */
	public Map<String, Object> getAcctRequestByEntryId(String entryId);
	
	/**
	 * 根据acctNbr查看表t_txn_data_request的txn_type，sales_amt，status，reduce_fee_rate字段
	 * @param acctNbr
	 * @return
	 */
	public Map<String, Object> getDataRequsetByAcctNbr(String acctNbr);
	/**
	 * 根据source_ref查看表t_txn_data_request的txn_type，sales_amt，status，reduce_fee_rate字段
	 * @param acctNbr
	 * @return
	 */
	public Map<String, Object> getDataRequsetByRef(String ref);
	/**
	 * 根据acctNbr查看表t_txn_clr_list_leg的stl_amt，fee，txn_type，acct_status，valid_flag字段
	 * @param acctNbr
	 * @return
	 */
	public Map<String, Object> getTxnLegByAcctNbr(String acctNbr);
	/**
	 * 根据source_ref查看表t_txn_clr_list_leg的stl_amt，fee，txn_type，acct_status，valid_flag字段
	 * @param acctNbr
	 * @return
	 */
	public Map<String, Object> getTxnLegByAcctRef(String ref);
	
	/**
	 * 根据source_ref和txn_type查看表t_txn_clr_list_leg的stl_amt，fee，txn_type，acct_status，valid_flag字段
	 * @param acctNbr
	 * @return
	 */
	public Map<String, Object> getTxnLegByRefAndType(Map<String, String> map);
	
	/**
	 * 根据externalTxnId查看表t_atm_pe_txn pe的deal_id，order_seq_id，status，fund_in_amount字段
	 * @param externalTxnId
	 * @return
	 */
	public Map<String, Object> getPeTxnByexternalTxnId(String externalTxnId);
	
	/**
	 * 根据account_nmbr、billing_flag、pymt_flag查LM_INSTALLMENT_TRAN_D表的个数
	 * @param map
	 * @return
	 */
	public int getCountTranDetByMap(Map<String, String> map);
	
	/**
	 * 根据source_ref更新T_CASH_DRAW_ORDER表的fo_status为1
	 * @param ref
	 */
	public  void updateCashDrawByRef(String ref);
	
	/**
	 * 根据MemberCode更新T_CASH_DRAW_ORDER表的fo_status为3
	 * @param ref
	 */
	public  void updateCashDrawByMemberCode(String MemberCode);
	
	/**
	 * 根据account_nmbr和term查询LM_INSTALLMENT_TRAN_D的还款状态
	 * @param map
	 * @return
	 */
	public int getCountInstallDetailByAcctNbrAndTerm(Map<String, String> map);
}
