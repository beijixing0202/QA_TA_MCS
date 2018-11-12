package com.bill99.mcs.orm;

import com.bill99.mcs.common.dto.Deal;
import com.bill99.mcs.common.dto.Entry;
import com.bill99.mcs.common.dto.PaymentOrder;
import com.bill99.mcs.common.dto.TAcctTxn;

import java.util.List;
import java.util.Map;

/**
 * Description: seashell数据库服务
 * Author: zhenfeng.liu
 * Date: 2017/10/18 15:48
 */
public interface SeaShellDBAccessService {
    /**
     * 通过交易编号查询T_ACCT_TXN表数据
     *
     * @param srcRef ATE TradeId
     * @return T_ACCT_TXN表数据对象
     */
    TAcctTxn queryTAcctTxnTable(String srcRef);
    /**
     * 通过交易编号查询PAYMENTORDER表数据
     *
     * @param orderId 交易编号
     * @return PAYMENTORDER表数据对象
     */
    PaymentOrder queryPaymentOrderTable(String orderId);
    /**
     * 通过交易编号查询PAYMENTORDER表数据
     *
     * @param orderId 交易编号
     * @param orderCode 交易code
     * @return PAYMENTORDER表数据对象
     */
    PaymentOrder queryPaymentOrderTable(String orderId,int orderCode);
    /**
     * 通过订单流水号查询DEAL表数据
     *
     * @param orderSeqId 订单流水号
     * @return DEAL表数据列表
     */
    List<Deal> queryDealTable(String orderSeqId);

    /**
     * 通过订单号查询ENTRY表数据
     *
     * @param dealId 订单号
     * @return ENTRY表数据列表
     */
    List<Entry> queryEntryTable(String dealId);

    /**
     * 通过DEAL表dealId字段列表查询ENTRY表数据
     *
     * @param deals DEAL表数据列表
     * @return ENTRY表数据列表
     */
    List<Entry> queryEntryTableByDealIds(List<Deal> deals);


}
