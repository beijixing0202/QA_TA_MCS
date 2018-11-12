package com.bill99.mcs.orm;

import com.bill99.mcs.common.dto.TBkApply;
import com.bill99.mcs.common.dto.TBkPeDeal;
import com.bill99.mcs.common.dto.TBkPePaymentOrder;
import com.bill99.mcs.common.dto.TBkRecord;
import com.bill99.mcs.common.dto.TMcsCtrl;

import java.util.List;

/**
 * Description: stldb数据库服务
 * Author: zhenfeng.liu
 * Date: 2017/10/17 12:49
 */
public interface StldbAccessService {
    /**
     * 通过交易编号查询T_MCS_CTRL表数据
     *
     * @param idTxn 交易编号
     * @return T_MCS_CTRL表对象
     */
    TMcsCtrl queryTMcsCtrlTable (String idTxn);
    /**
     * 通过交易编号查询T_BK_APPLY表数据
     *
     * @param reference 交易编号
     * @return T_BK_APPLY表对象
     */
    TBkApply queryTBkApplyTable(String reference);

    /**
     * 通过交易编号查询T_BK_RECORD表数据
     *
     * @param reference 交易编号
     * @return T_BK_RECORD表对象
     */
    TBkRecord queryTBkRecordTable(String reference);

    /**
     * 通过交易编号查询T_BK_PE_PAYMENT_ORDER表数据
     *
     * @param orderId 交易编号
     * @return T_BK_PE_PAYMENT_ORDER表对象
     */
    TBkPePaymentOrder queryTBkPePaymentOrderTable(String orderId);

    /**
     * 通过订单编号查询T_BK_PE_DEAL表数据
     *
     * @param idBkPePaymentOrder 订单编号
     * @return T_BK_PE_DEAL表对象
     */
    List<TBkPeDeal> queryTBkPeDealTable(String idBkPePaymentOrder);


}
