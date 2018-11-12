package com.bill99.mcs.orm.impl;

import com.bill99.mcs.common.dto.TBkApply;
import com.bill99.mcs.common.dto.TBkPeDeal;
import com.bill99.mcs.common.dto.TBkPePaymentOrder;
import com.bill99.mcs.common.dto.TBkRecord;
import com.bill99.mcs.orm.StlDBService;
import com.bill99.qa.framework.jdbc.TaDbHandller;
import com.bill99.mcs.common.dto.TMcsCtrl;

import java.util.List;

/**
 * Description: stldb数据库基础服务实现
 * Author: zhenfeng.liu
 * Date: 2017/10/17 12:28
 */
public class StlDBImpl implements StlDBService {
    private TaDbHandller taStlDbHandller;
    private String username;
    private String password;

    public void setTaStlDbHandller(TaDbHandller taStlDbHandller) {
        this.taStlDbHandller = taStlDbHandller;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<TMcsCtrl> queryTMcsCtrlInfo(TMcsCtrl tMcsCtrl) {
        return taStlDbHandller.queryForList("TMcsCtrl.getTMcsCtrl", tMcsCtrl);
    }

    @Override
    public List<TBkApply> queryTBkApplyInfo(TBkApply tBkApply) {
        return taStlDbHandller.queryForList("TBkApply.getTBkApply", tBkApply);
    }

    @Override
    public List<TBkRecord> queryTBkRecordInfo(TBkRecord tBkRecord) {
        return taStlDbHandller.queryForList("TBkRecord.getTBkRecord", tBkRecord);
    }

    @Override
    public List<TBkPePaymentOrder> queryTBkRecordInfo(TBkPePaymentOrder tBkPePaymentOrder) {
        return taStlDbHandller.queryForList("TBkPePaymentOrder.getTBkPePaymentOrder", tBkPePaymentOrder);
    }

    @Override
    public List<TBkPeDeal> queryTBkPeDealInfo(TBkPeDeal tBkPeDeal) {
        return taStlDbHandller.queryForList("TBkPeDeal.getTBkPeDeal", tBkPeDeal);
    }

}
