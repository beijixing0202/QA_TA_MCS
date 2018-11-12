package com.bill99.cps.orm;

import shelper.db.Oracle;

public interface OracleDao {
	public Oracle getInstance(String dbtype);

}
