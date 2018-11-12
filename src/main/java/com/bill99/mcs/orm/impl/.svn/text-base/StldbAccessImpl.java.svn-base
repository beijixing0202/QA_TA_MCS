package com.bill99.mcs.orm.impl;

import com.bill99.mcs.common.dto.TBkApply;
import com.bill99.mcs.common.dto.TBkPeDeal;
import com.bill99.mcs.common.dto.TBkPePaymentOrder;
import com.bill99.mcs.common.dto.TBkRecord;
import com.bill99.mcs.common.dto.TMcsCtrl;
import com.bill99.mcs.orm.StlDBService;
import com.bill99.mcs.orm.StldbAccessService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description: stldb数据库操作实现
 * Author: zhenfeng.liu
 * Date: 2017/10/17 12:49
 */
public class StldbAccessImpl implements StldbAccessService {
    @Autowired
    private StlDBService stlDBService;

    @Override
    public TMcsCtrl queryTMcsCtrlTable(String idTxn) {
        TMcsCtrl tMcsCtrl = new TMcsCtrl();
        tMcsCtrl.setIdTxn(idTxn);
        return stlDBService.queryTMcsCtrlInfo(tMcsCtrl).get(0);
    }

    @Override
    public TBkApply queryTBkApplyTable(String reference) {
        TBkApply tBkApply = new TBkApply();
        tBkApply.setReference(reference);
        return stlDBService.queryTBkApplyInfo(tBkApply).get(0);
    }

    @Override
    public TBkRecord queryTBkRecordTable(String reference) {
        TBkRecord tBkRecord = new TBkRecord();
        tBkRecord.setReference(reference);
        return stlDBService.queryTBkRecordInfo(tBkRecord).get(0);
    }

    @Override
    public TBkPePaymentOrder queryTBkPePaymentOrderTable(String orderId) {
        TBkPePaymentOrder tBkPePaymentOrder = new TBkPePaymentOrder();
        tBkPePaymentOrder.setOrderId(orderId);
        return stlDBService.queryTBkRecordInfo(tBkPePaymentOrder).get(0);
    }

    @Override
    public List<TBkPeDeal> queryTBkPeDealTable(String idBkPePaymentOrder) {
        TBkPeDeal tBkPeDeal = new TBkPeDeal();
        tBkPeDeal.setIdBkPePaymentOrder(idBkPePaymentOrder);
        return stlDBService.queryTBkPeDealInfo(tBkPeDeal);
    }


}
