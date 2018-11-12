package com.bill99.mcs.orm.impl;

import com.bill99.mcs.common.dto.TStlList;
import com.bill99.mcs.common.dto.TStlOrder;
import com.bill99.mcs.common.dto.TxnCtrl;
import com.bill99.mcs.orm.MasposDBService;
import com.bill99.mcs.common.dto.TClrTxnList;
import com.bill99.qa.framework.jdbc.TaDbHandller;

import java.util.List;

/**
 * Description: maspos数据库基础服务实现
 * Author: zhenfeng.liu
 * Date: 2017/10/16 9:47
 */
public class MasposDBImpl implements MasposDBService {
    private TaDbHandller taMasposDbHandller;
    private String username;
    private String password;

    public void setTaMasposDbHandller(TaDbHandller taMasposDbHandller) {
        this.taMasposDbHandller = taMasposDbHandller;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //新增内容_wsy
    @Override
	public void updateTClrTxninfo(String idTxn) {
    	
    	taMasposDbHandller.executeUpdate("idTxn", idTxn);
    	
		
		
	}
    
    @Override
    public List<TClrTxnList> queryTClrTxnListInfo(TClrTxnList tClrTxnList) {
  
   
        return taMasposDbHandller.queryForList("TClrTxnList.getTClrTxnList", tClrTxnList);
    }

    
    
    @Override
    public List<TStlList> queryTStlListInfoByStlSrcRef(TStlList tStlList) {
        return taMasposDbHandller.queryForList("TStlList.getTStlListByStlSrcRef", tStlList);
    }


    @Override
    public List<TStlList> queryTStlListInfoByIdStlOrder(TStlList tStlList) {
        return taMasposDbHandller.queryForList("TStlList.getTStlListByIdStlOrder", tStlList);
    }

    @Override
    public List<TStlOrder> queryTStlOrderInfo(TStlOrder tStlOrder) {
        return taMasposDbHandller.queryForList("TStlOrder.getTStlOrder", tStlOrder);
    }

	@Override
	public List<TxnCtrl> queryTxnCtrlTable(TxnCtrl txnCtrl) {

        return  taMasposDbHandller.queryForList("TxnCtrl.getTxnCtrl",txnCtrl);
	}

	
    
}
