package com.bill99.mcs.orm.impl;

import com.bill99.mcs.common.dto.TStlList;
import com.bill99.mcs.common.dto.TStlOrder;
import com.bill99.mcs.common.dto.TxnCtrl;
import com.bill99.mcs.orm.MasposDBAccessService;
import com.bill99.mcs.common.dto.TClrTxnList;
import com.bill99.mcs.orm.MasposDBService;
import com.bill99.mcs.orm.MySqlDaoServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description: maspos数据库操作实现
 * Author: zhenfeng.liu
 * Date: 2017/10/16 9:22
 */
public class MasposDBAccessImpl implements MasposDBAccessService {
    @Autowired
    private MasposDBService masposDBService;
    @Autowired
    private MySqlDaoServer mySqlDaoServer;

    @Override
    public TClrTxnList queryTClrTxnListTable(String idTxn) {
        TClrTxnList tClrTxnList = new TClrTxnList();
        tClrTxnList.setIdTxn(idTxn);
        return masposDBService.queryTClrTxnListInfo(tClrTxnList).get(0);
    }
    
    //增加内容_wsy
    @Override
	public void updateTClrTxnTable(String idTxn) {
    	 TClrTxnList tClrTxnList = new TClrTxnList();
    	 tClrTxnList.setIdTxn(idTxn);
		 masposDBService.updateTClrTxninfo(idTxn);
		
	}


    @Override
    public TStlList queryTStlListTable(String stlSrcRef) {
        TStlList tStlList = new TStlList();
        tStlList.setStlSrcRef(stlSrcRef);
        return masposDBService.queryTStlListInfoByStlSrcRef(tStlList).get(0);
    }

    @Override
    public List<TStlList> queryTStlListTableList(String idStlOrder) {
        TStlList tStlList = new TStlList();
        tStlList.setIdStlOrder(idStlOrder);
        return masposDBService.queryTStlListInfoByIdStlOrder(tStlList);
    }

    @Override
    public TStlOrder queryTStlOrderTable(String idStlOrder) {
        TStlOrder tStlOrder = new TStlOrder();
        tStlOrder.setIdStlOrder(idStlOrder);
        return masposDBService.queryTStlOrderInfo(tStlOrder).get(0);
    }

    
	@Override
	public TxnCtrl queryTxnCtrlTable(String idTxn) {
		TxnCtrl txnCtrl=new TxnCtrl();
		txnCtrl.setIdTxn(idTxn);
        return masposDBService.queryTxnCtrlTable(txnCtrl).get(0);
	}

	


    
}
