package com.bill99.mcs.orm.impl;

import com.bill99.mcs.common.dto.Deal;
import com.bill99.mcs.common.dto.Entry;
import com.bill99.mcs.common.dto.PaymentOrder;
import com.bill99.mcs.common.dto.TAcctTxn;
import com.bill99.mcs.orm.SeaShellDBAccessService;
import com.bill99.mcs.orm.SeaShellDBService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: seashell数据库服务实现
 * Author: zhenfeng.liu
 * Date: 2017/10/18 15:48
 */
public class SeaShellDBAccessImpl implements SeaShellDBAccessService {
    @Autowired
    private SeaShellDBService seaShellDBService;

    @Override
    public TAcctTxn queryTAcctTxnTable(String srcRef){
        TAcctTxn tAcctTxn = new TAcctTxn();
        tAcctTxn.setSrcRef(srcRef);
        return seaShellDBService.queryTAcctTxnInfo(tAcctTxn).get(0);
    }
    @Override
    public PaymentOrder queryPaymentOrderTable(String orderId) {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setOrderId(orderId);
//        paymentOrder.setLastupdatetime(DateUtils.parse("20171001"));
        return seaShellDBService.queryPaymentOrderInfo(paymentOrder).get(0);
    }
    @Override
    public PaymentOrder queryPaymentOrderTable(String orderId,int orderCode) {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setOrderId(orderId);
        paymentOrder.setOrdercode(orderCode);
        return seaShellDBService.queryPaymentOrderInfo(paymentOrder).get(0);
    }
    @Override
    public List<Deal> queryDealTable(String orderSeqId) {
        Deal deal = new Deal();
        deal.setOrderSeqId(orderSeqId);
        return seaShellDBService.queryDealInfo(deal);
    }

    @Override
    public List<Entry> queryEntryTable(String dealId) {
        Entry entry = new Entry();
        entry.setDealId(dealId);
        return seaShellDBService.queryEntryInfo(entry);
    }

    @Override
    public List<Entry> queryEntryTableByDealIds(List<Deal> deals) {
        List<String> dealIds = new ArrayList<String>();
        for (Deal deal: deals) {
            dealIds.add(deal.getDealid());
        }
        return seaShellDBService.queryEntryInfoByDealIds(dealIds);
    }



}
