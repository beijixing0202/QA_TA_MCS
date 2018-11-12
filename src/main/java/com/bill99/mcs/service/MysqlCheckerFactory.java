package com.bill99.mcs.service;

import com.bill99.mcs.service.impl.MysqlCheckerImpl;

import java.util.Map;

/**
 * Created by wentao.jia on 2018/10/29.
 */
public class MysqlCheckerFactory extends AbstractChecker{
    @Override
    public MysqlCheckerImpl mysqlChecker(Map<String, String> data){
        return new MysqlCheckerImpl();
    }
    @Override
    public  OracleChecker oracleChecker(Map<String, String> data){
        return null;
    }
}
