package com.bill99.fi.orm.entity;

import java.io.Serializable;

public class Unionorderlog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3552186642521865312L;
	
	
	private String sequenceid;
	private String payorderid;
	private String payamt;

	public String getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(String sequenceid) {
		this.sequenceid = sequenceid;
	}
	public String getPayorderid() {
		return payorderid;
	}
	public void setPayorderid(String payorderid) {
		this.payorderid = payorderid;
	}
	public String getPayamt() {
		return payamt;
	}
	public void setPayamt(String payamt) {
		this.payamt = payamt;
	}

}
