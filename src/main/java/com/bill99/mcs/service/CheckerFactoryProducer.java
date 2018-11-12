package com.bill99.mcs.service;

import com.bill99.mcs.common.helper.DealType;

import java.util.Map;

/**
 * Created by wentao.jia on 2018/10/29.
 */
public class CheckerFactoryProducer {
    public static AbstractChecker getFactory(Map<String, String> data){
        if (data.get("MockType").equals(DealType.CPS_CNP.getEntryType())){
            return new OracleCheckerFactory();
        }
        else {
            return new MysqlCheckerFactory();
        }
    }
}
