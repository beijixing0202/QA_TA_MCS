package com.bill99.mcs.service;

import com.bill99.mcs.common.helper.TxnType;

import java.util.Map;

/**
 * Created by wentao.jia on 2018/9/20.
 */
public class DealFactoryProducer {
    public static AbstractDeal getFactory(Map<String, String> data){
        if (data.get("txnType").equals(TxnType.PAY.getEntryType())){
            return new PayFactory();
        }
        else if(data.get("txnType").equals(TxnType.RFD.getEntryType())){
            return new RfdFactory();
        }
        else {
            return null;
        }
    }
}
