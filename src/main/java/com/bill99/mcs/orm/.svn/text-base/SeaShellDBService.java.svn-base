package com.bill99.mcs.orm;

import com.bill99.mcs.common.dto.Deal;
import com.bill99.mcs.common.dto.Entry;
import com.bill99.mcs.common.dto.PaymentOrder;
import com.bill99.mcs.common.dto.TAcctTxn;

import java.util.List;

/**
 * Description: seashell数据库基础服务
 * Author: zhenfeng.liu
 * Date: 2017/10/18 15:13
 */
public interface SeaShellDBService {
    /**
     * 通过T_ACCT_TXN表数据对象查询T_ACCT_TXN表数据
     *
     * @param tAcctTxn T_ACCT_TXN对象
     * @return T_ACCT_TXN表数据对象列表
     */
    List <TAcctTxn> queryTAcctTxnInfo (TAcctTxn tAcctTxn);
    /**
     * 通过PAYMENTORDER表数据对象查询T_STL_ORDER表数据
     *
     * @param paymentOrder PAYMENTORDER对象
     * @return PAYMENTORDER表数据对象列表
     */
    List<PaymentOrder> queryPaymentOrderInfo(PaymentOrder paymentOrder);

    /**
     * 通过DEAL表数据对象查询DEAL表数据
     *
     * @param deal DEAL对象
     * @return DEAL表数据对象列表
     */
    List<Deal> queryDealInfo(Deal deal);

    /**
     * 通过ENTRY表数据对象查询ENTRY表数据
     *
     * @param entry ENTRY对象
     * @return ENTRY表数据对象列表
     */
    List<Entry> queryEntryInfo(Entry entry);

    /**
     * 通过DEAL表的dealId字段列表查询ENTRY表数据
     *
     * @param dealIds DEAL表的dealId字段列表
     * @return ENTRY表数据对象列表
     */
    List<Entry> queryEntryInfoByDealIds(List<String> dealIds);
}
