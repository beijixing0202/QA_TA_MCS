package com.bill99.mcs.orm.impl;

import com.bill99.mcs.tools.DbTypeConstants;
import shelper.db.MySql;

import com.bill99.mcs.orm.MySqlDaoServer;

public class MySqlDaoImpl implements MySqlDaoServer{
    private MySql mySqlVpos;
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
	public MySql getInstance(String dbType) {
        if (DbTypeConstants.DB_TYPE_MYSQL_VPOS.equals(dbType)) {
            mySqlVpos = null;
            mySqlVpos = new MySql(vposConnect, vposUserName, vposPassWord);
            return mySqlVpos;
        } else {
            return null;
        }
    }
    
}
