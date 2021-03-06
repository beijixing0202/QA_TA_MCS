package com.bill99.mcs.orm;

import java.util.List;

import com.bill99.mcs.common.dto.TClrTxnList;
import com.bill99.mcs.common.dto.TStlList;
import com.bill99.mcs.common.dto.TStlOrder;
import com.bill99.mcs.common.dto.TxnCtrl;

/**
 * Description: maspos数据库基础服务
 * Author: zhenfeng.liu
 * Date: 2017/10/16 9:47
 */
public interface MasposDBService {
    /**
     * 通过T_txn_ctrl表数据对象查询T_txn_ctrl表数据
     *
     * @param txnCtrl T_txn_ctrl对象
     * @return T_txn_ctrl表数据对象列表
     */
    List<TxnCtrl> queryTxnCtrlTable(TxnCtrl txnCtrl);

    /**
     * 通过T_CLR_TXN_LIST表数据对象查询T_CLR_TXN_LIST表数据
     *
     * @param tClrTxnList T_CLR_TXN_LIST对象
     * @return T_CLR_TXN_LIST表数据对象列表
     */
    List<TClrTxnList> queryTClrTxnListInfo(TClrTxnList tClrTxnList);
    
    
    //新增内容_wsy
    //void updateTClrTxninfo(TClrTxnList tClrTxnList);
    void updateTClrTxninfo(String idTxn);
   

    /**
     * 通过T_STL_LIST表数据对象查询T_STL_LIST表数据
     *
     * @param tStlList T_STL_LIST对象
     * @return T_STL_LIST表数据对象列表
     */
    List<TStlList> queryTStlListInfoByStlSrcRef(TStlList tStlList);



    /**
     * 通过T_STL_LIST表数据对象查询T_STL_LIST表数据
     *
     * @param tStlList T_STL_LIST对象
     * @return T_STL_LIST表数据对象列表
     */
    List<TStlList> queryTStlListInfoByIdStlOrder(TStlList tStlList);

    /**
     * 通过T_STL_ORDER表数据对象查询T_STL_ORDER表数据
     *
     * @param tStlOrder T_STL_ORDER对象
     * @return T_STL_ORDER表数据对象列表
     */
    List<TStlOrder> queryTStlOrderInfo(TStlOrder tStlOrder);

	




	

	
    
 
    
    
}
