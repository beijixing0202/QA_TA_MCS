package com.bill99.cps.orm.impl;

import com.bill99.cps.common.utils.MgwFinal;
import com.bill99.cps.orm.OracleDao;

import shelper.db.Oracle;

public class OracleDaoImpl implements OracleDao {
	
	private Oracle oracle_seashell;
	private Oracle oracle_cps;
	private Oracle oracle_ccs;
	private Oracle oracle_oqs;
	
	private String cpsconnect;
	private String seashellconnect;
	private String cps02connect;
	private String ccs02connect;
	


	private String cpsname ;
	private String cpspassw;
	private String seashellname;
	private String seashellpassw;
	private String cps02name;
	private String cps02passw;
	private String ccs02name;
	private String ccs02passw;	
	

	public void setCpsconnect(String cpsconnect) {
		this.cpsconnect = cpsconnect;
	}

	public void setSeashellconnect(String seashellconnect) {
		this.seashellconnect = seashellconnect;
	}

	public void setCps02connect(String cps02connect) {
		this.cps02connect = cps02connect;
	}
	
	public void setCcs02connect(String ccs02connect) {
		this.ccs02connect = ccs02connect;
	}

	public void setCpsname(String cpsname) {
		this.cpsname = cpsname;
	}

	public void setCpspassw(String cpspassw) {
		this.cpspassw = cpspassw;
	}

	public void setSeashellname(String seashellname) {
		this.seashellname = seashellname;
	}

	public void setSeashellpassw(String seashellpassw) {
		this.seashellpassw = seashellpassw;
	}

	public void setCps02name(String cps02name) {
		this.cps02name = cps02name;
	}

	public void setCps02passw(String cps02passw) {
		this.cps02passw = cps02passw;
	}
	
	public void setCcs02name(String ccs02name) {
		this.ccs02name = ccs02name;
	}

	public void setCcs02passw(String ccs02passw) {
		this.ccs02passw = ccs02passw;
	}


    //三种数据库访问形式
	@Override
	public Oracle getInstance(String dbtype) {
		if (MgwFinal.DB_TYPE_ORCLE_CPS.equals(dbtype)) {
			   oracle_cps = null;
				oracle_cps = new Oracle(cpsconnect,
						cpsname,
						cpspassw);

			return oracle_cps;
		}
		if (MgwFinal.DB_TYPE_ORCLE_SEASHELL.equals(dbtype)) {
			    oracle_seashell= null;
				oracle_seashell = new Oracle(seashellconnect,
						seashellname,
						seashellpassw);

			return oracle_seashell;
		}
		if (MgwFinal.DB_TYPE_ORCLE_OQS.equals(dbtype)) {
			    oracle_oqs = null;
				oracle_oqs = new Oracle(cps02connect,
						cps02name,
						cps02passw);

			return oracle_oqs;
		}
		if (MgwFinal.DB_TYPE_ORCLE_CCS.equals(dbtype)) {
		    oracle_ccs = null;
			oracle_ccs = new Oracle(ccs02connect,
					ccs02name,
					ccs02passw);

		return oracle_ccs;
	}
		return null ;
	}

}
