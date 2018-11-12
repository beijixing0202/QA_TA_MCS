package com.bill99.mcs.orm;

import com.bill99.mcs.common.dto.TBkApply;
import com.bill99.mcs.common.dto.TBkPeDeal;
import com.bill99.mcs.common.dto.TBkPePaymentOrder;
import com.bill99.mcs.common.dto.TBkRecord;
import com.bill99.mcs.common.dto.TMcsCtrl;

import java.util.List;

/**
 * Description: stldb数据库基础服务
 * Author: zhenfeng.liu
 * Date: 2017/10/17 12:28
 */
public interface StlDBService {
    /**
     * 通过T_MCS_CTRL表数据对象查询T_MCS_CTRL表数据
     *
     * @param tMcsCtrl T_MCS_CTRL表对象
     * @return T_MCS_CTRL表数据对象列表
     */
    List<TMcsCtrl> queryTMcsCtrlInfo (TMcsCtrl tMcsCtrl);
    /**
     * 通过T_BK_APPLY表数据对象查询T_STL_ORDER表数据
     *
     * @param tBkApply T_BK_APPLY对象
     * @return T_BK_APPLY表数据对象列表
     */
    List<TBkApply> queryTBkApplyInfo(TBkApply tBkApply);

    /**
     * 通过T_BK_Record表数据对象查询T_BK_Record表数据
     *
     * @param tBkRecord T_BK_Record对象
     * @return T_BK_Record表数据对象列表
     */
    List<TBkRecord> queryTBkRecordInfo(TBkRecord tBkRecord);

    /**
     * 通过T_BK_PE_PAYMENT_ORDER表数据对象查询T_BK_PE_PAYMENT_ORDER表数据
     *
     * @param tBkPePaymentOrder T_BK_PE_PAYMENT_ORDER对象
     * @return T_BK_PE_PAYMENT_ORDER表数据对象列表
     */
    List<TBkPePaymentOrder> queryTBkRecordInfo(TBkPePaymentOrder tBkPePaymentOrder);

    /**
     * 通过T_BK_PE_DEAL表数据对象查询T_BK_PE_PAYMENT_ORDER表数据
     *
     * @param tBkPeDeal T_BK_PE_DEAL对象
     * @return T_BK_PE_DEAL表数据对象列表
     */
    List<TBkPeDeal> queryTBkPeDealInfo(TBkPeDeal tBkPeDeal);


}
