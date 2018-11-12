package com.bill99.mcs.orm.impl;

import com.bill99.mcs.orm.OracleDaoService;
import com.bill99.mcs.tools.DbTypeConstants;
import shelper.db.Oracle;

/**
 * Description: Oracle数据库Dao实现类
 * Author: zhenfeng.liu
 * Date: 2017/10/12 16:05
 */
public class OracleDaoImpl implements OracleDaoService {
    private Oracle oracleVpos;
    private String vposConnect;
    private String vposUserName;
    private String vposPassWord;

    public void setVposConnect(String vposConnect) {
        this.vposConnect = vposConnect;
    }

    public void setVposUserName(String vposUserName) {
        this.vposUserName = vposUserName;
    }

    public void setVposPassWord(String vposPassWord) {
        this.vposPassWord = vposPassWord;
    }

    @Override
    public Oracle getInstance(String dbType) {
        if (DbTypeConstants.DB_TYPE_ORACLE_VPOS.equals(dbType)) {
            oracleVpos = null;
            oracleVpos = new Oracle(vposConnect, vposUserName, vposPassWord);
            return oracleVpos;
        } else {
            // TODO: 2017/10/13 其他数据库类型判断
            return null;
        }
    }
}
