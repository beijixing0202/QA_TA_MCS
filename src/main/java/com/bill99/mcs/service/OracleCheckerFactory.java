package com.bill99.mcs.service;

import com.bill99.mcs.service.impl.MysqlCheckerImpl;
import com.bill99.mcs.service.impl.OracleCheckerImpl;

import java.util.Map;

/**
 * Created by wentao.jia on 2018/10/29.
 */
public class OracleCheckerFactory extends AbstractChecker{
    @Override
    public MysqlCheckerImpl mysqlChecker(Map<String, String> data){
        return null;
    }
    @Override
    public  OracleCheckerImpl oracleChecker(Map<String, String> data){
        return new OracleCheckerImpl();
    }
}
