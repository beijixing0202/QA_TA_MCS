package com.bill99.mcs.orm;

import shelper.db.MySql;

public interface MySqlDaoServer {
    /**
     * Description: Mysql数据库Dao
     * Author: shengyuan.wang
     */
    MySql getInstance(String dbType);
}
