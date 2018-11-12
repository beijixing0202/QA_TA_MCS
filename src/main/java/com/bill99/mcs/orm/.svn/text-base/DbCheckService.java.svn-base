package com.bill99.mcs.orm;

import com.bill99.mcs.common.dto.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 数据库校验接口
 * Author: zhenfeng.liu
 * Date: 2017/10/13 9:59
 */
public interface DbCheckService {


    /**
     * CPS时：检查T_TXN_CTRL表数据
     *
     * @param extTraceNo 外部跟踪编号
     * @param data       请求数据
     * @return 表字段-值的键值对
     */
    Map<String, String> checkDataFromTTxnCtrlAtCPS(String extTraceNo, Map<String, String> data);

    /**
     * MCS清分时：检查T_CLR_TXN_LIST表数据
     *
     * @param tClrTxnList T_CLR_TXN_LIST表数据对象
     * @param data        请求数据
     */
    void checkDataFromTClrTxnListAtMCSClear(TClrTxnList tClrTxnList, Map<String, String> data);


    void updateTTxnCtrl(String idTxn);

    void updateTTxnCtrlPix(String sql);


    void checkTStlListNum(String idTxn,Map<String, String> data);



    /**
     * MCS清分时：检查T_STL_LIST表数据
     *
     * @param tStlList T_STL_LIST表数据对象
     * @param data     请求数据
     */
    void checkDataFromTStlListAtMCSClear(TStlList tStlList, Map<String, String> data);

    /**
     * CSPPE清分/结算时：检查T_BK_APPLY表数据
     *
     * @param tBkApply T_BK_APPLY表数据对象
     * @param data     请求数据
     */
    void checkDataFromTBkApply(TBkApply tBkApply, Map<String, String> data);

    /**
     * CSPPE清分/结算时：检查T_BK_RECORD表数据
     *
     * @param tBkRecord T_BK_RECORD表数据对象
     * @param data      请求数据
     */
    void checkDataFromTBkRecord(TBkRecord tBkRecord, Map<String, String> data);

    /**
     * CSPPE清分时：检查T_BK_PE_PAYMENT_ORDER表数据
     *
     * @param tBkPePaymentOrder T_BK_PE_PAYMENT_ORDER表数据对象
     * @param data              请求数据
     */
    void checkDataFromTBkPePaymentOrderAtCSPPEClear(TBkPePaymentOrder tBkPePaymentOrder, Map<String, String> data);

    /**
     * CSPPE结算时：检查T_BK_PE_PAYMENT_ORDER表数据
     *
     * @param tBkPePaymentOrder T_BK_PE_PAYMENT_ORDER表数据对象
     * @param tStlOrder         T_STL_ORDER表数据对象
     */
    void checkDataFromTBkPePaymentOrderAtCSPPESettlement(TBkPePaymentOrder tBkPePaymentOrder, TStlOrder tStlOrder);

    /**
     * CSPPE清分时：检查T_BK_PE_DEAL表数据
     *
     * @param tBkPeDeals T_BK_PE_DEAL表数据对象列表
     * @param data       请求数据
     */
    void checkDataFromTBkPeDealAtCSPPEClear(List<TBkPeDeal> tBkPeDeals, Map<String, String> data);

    void checkDataFromTBkPeDealAtCSPPEClearOne(TBkPeDeal tBkPeDeals, Map<String, String> data);

    /**
     * CSPPE结算时：检查T_BK_PE_DEAL表数据
     *
     * @param tBkPeDeals T_BK_PE_DEAL表数据对象
     * @param tStlOrder  T_STL_ORDER表数据对象
     */
    void checkDataFromTBkPeDealAtCSPPESettlement(List<TBkPeDeal> tBkPeDeals, TStlOrder tStlOrder);

    /**
     * MCS结算时：检查T_STL_LIST表数据
     *
     * @param tStlList T_STL_LIST表数据对象
     */
    void checkDataFromTStlListAtMCSSettlement(TStlList tStlList);

    /**
     * MCS结算时：检查T_STL_ORDER表数据
     *
     * @param tStlOrder T_STL_ORDER表数据对象
     * @param tStlLists T_STL_LIST表数据对象列表
     */
    void checkDataFromTStlOrderAtMCSSettlement(TStlOrder tStlOrder, List<TStlList> tStlLists);

    /**
     * PE清分记账时：检查PAYMENTORDER表数据
     *
     * @param paymentOrder PAYMENTORDER表数据对象
     * @param data         请求数据
     */
    void checkDataFromPaymentOrderAtPEClear(PaymentOrder paymentOrder, Map<String, String> data);

    /**
     * PE结算记账时：检查PAYMENTORDER表数据
     *
     * @param paymentOrder PAYMENTORDER表数据对象
     * @param tStlOrder    T_STL_ORDER表数据对象列表
     */
    void checkDataFromPaymentOrderAtPESettlement(PaymentOrder paymentOrder, TStlOrder tStlOrder);

    /**
     * PE清分记账时：检查DEAL表数据
     *
     * @param deals DEAL表数据对象列表
     * @param data  请求数据
     */
    void checkDataFromDealAtPEClear(List<Deal> deals, Map<String, String> data);

    /**
     * PE结算记账时：检查DEAL表数据
     *
     * @param deals     DEAL表数据对象列表
     * @param tStlOrder T_STL_ORDER表数据对象列表
     */
    void checkDataFromDealAtPESettlement(List<Deal> deals, TStlOrder tStlOrder);

    /**
     * PE清分记账时：检查ENTRY表数据
     *
     * @param entries ENTRY表数据对象列表
     * @param data    请求数据
     */
    void checkDataFromEntryAtPEClear(List<Entry> entries, Map<String, String> data);



    
    
   
    /**
     * PE结算记账时：检查ENTRY表数据
     *
     * @param entries   ENTRY表数据对象列表
     * @param tStlOrder T_STL_ORDER表数据对象列表
     */
    void checkDataFromEntryAtPESettlement(List<Entry> entries, TStlOrder tStlOrder);

	
    void checkDataFromEntryPEClear(List<Entry> entriesOne,
			Map<String, String> data);


    /**
     * MCS清分时：检查T_ACCT_TXN表数据
     *
     * @param tAcctTxn T_ACCT_TXN表数据对象
     * @param data        请求数据
     */
    void checkDataFromTAcctTxn(TAcctTxn tAcctTxn,Map<String, String> data);
    
    /**
     * ACSPE清分记账时：检查PAYMENTORDER表数据
     *
     * @param paymentOrder PAYMENTORDER表数据对象
     * @param data         请求数据
     */
    void checkDataFromPaymentOrderACSPEClear(PaymentOrder paymentOrder, Map<String, String> data); 
    
    void checkDataFromDealACSPEClear(List<Deal> deals, Map<String, String> data);
    
    void checkDataFromEntryACSPEClear(List<Entry> entries,Map<String, String> data );
    /**
     * PIX时：检查T_TXN_CTRL表数据
     *
     * @param idTxn 交易编号
     * @param data       请求数据
     * @return
     * @return 表字段-值的键值对
     */


    Map<String, String> checkDataFromTTxnCtrlPIX(String idTxn, Map<String, String> data);
}
