package com.bill99.cps.test;

import java.sql.Statement;

import shelper.data.DataTable;
import shelper.db.Oracle;

public class Dbmap {
	
	public static void main(String[] args) {
		Oracle db =new Oracle("(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.6.135)(PORT = 1530))(ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.6.136)(PORT = 1530)))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = billtaf.99bill.com)))", "QAMODIFY",
				"gubh0o7s");
		String pa = db.query
				("Select orderid,submitacctcode,ordercode,productname,orderamount,payeracctcode,payeeacctcode,orderstatus From Seashell.paymentorder  Where Sequenceid = '72299560'");
		String deal = db
				.query("Select d.* From Seashell.Deal d Where d.Orderseqid = '72299560' and d");
//		
//		String dealid=peAllDto.get
//		String entry = db.query("Select d.* From Seashell.Entry d Where d.dealid = '7958167'");
		System.out.println(deal);
		
		db.closeDBcon();
	}

}
