package com.bill99.mcs.service;

import java.util.Map;

/**
 * Created by wentao.jia on 2018/10/29.
 */
public abstract class AbstractChecker {
    public abstract MysqlChecker mysqlChecker(Map<String, String> data);
    public abstract OracleChecker oracleChecker(Map<String, String> data);
}
