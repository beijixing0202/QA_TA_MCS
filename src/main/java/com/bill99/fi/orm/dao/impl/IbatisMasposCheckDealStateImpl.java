package com.bill99.fi.orm.dao.impl;

import java.util.Map;

import com.bill99.qa.framework.jdbc.TaDbHandller;
import com.bill99.fi.orm.dao.IbatisMasposCheckDealState;

public class IbatisMasposCheckDealStateImpl implements IbatisMasposCheckDealState{
	
	private TaDbHandller taMasposDbHandller ;

	public TaDbHandller getTaMasposDbHandller() {
		return taMasposDbHandller;
	}
	public void setTaMasposDbHandller(TaDbHandller taMasposDbHandller) {
		 this.taMasposDbHandller = taMasposDbHandller;
	}
	/*public int ibatisMasCheckDealState(Map<String, String> data) {

		return Integer.valueOf(taMasposDbHandller.queryForObject("VopsMasDb.ibatisMasCheckDealState",data).toString());
	}*/
   
	public int ibatisMasCheckDelCardState(Map<String, String> data){
    	
    	return Integer.valueOf(taMasposDbHandller.queryForObject("VopsMasDb.ibatisMasCheckDelCardState",data).toString());
    }
	

	

}
