package com.bill99.ifs.service;

import java.util.List;
import java.util.Map;

import com.bill99.ifs.fcs.api.message.TxnInfo;

public interface CheckIfsDbService {

	/**
	 * 检查消费交易落地数据是否正确
	 * @param map（必传ATE表t_acc_order_master的billOrderNo、orderType、payAmount）
	 * @return
	 */
	boolean checkConsumeService(Map<String, String> map);

	/**
	 * 检查撤销交易落地数据是否正确
	 * @param tradeId（ATE表t_acc_order_master的tradeId）
	 * @return
	 */
	boolean checkRevokeService(Map<String, String> map);

	/**
	 * 检查退货交易落地数据是否正确
	 * @param  map（必传ATE表t_acc_order_master的billOrderNo、orderType、payAmount）
	 * @return
	 */
	boolean checkRefundService(Map<String, String> map);

	/**
	 * 检查全部还款落地数据是否正确
	 * @param txnlist
	 * @return
	 */
	boolean checkRepayAllService(TxnInfo txnInfo);

	/**
	 * 检查单期还款落地数据是否正确
	 * @param txnInfo
	 * @return
	 */
	boolean checkRepayTermService(TxnInfo txnInfo);
}
