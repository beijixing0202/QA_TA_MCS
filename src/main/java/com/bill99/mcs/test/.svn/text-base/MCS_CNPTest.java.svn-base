package com.bill99.mcs.test;

import com.bill99.mcs.common.dto.*;
import com.bill99.mcs.orm.DbCheckService;
import com.bill99.mcs.orm.MasposDBAccessService;
import com.bill99.mcs.orm.SeaShellDBAccessService;
import com.bill99.mcs.orm.StldbAccessService;
import com.bill99.mcs.service.ManualClearing;
import com.bill99.mcs.service.McsService;
import com.bill99.mcs.service.Pay;
import com.bill99.qa.framework.testcase.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description: CNP交易
 * Author: Henry.jia
 */
public class MCS_CNPTest extends BaseTestCase {
    @Autowired
    private ManualClearing manualClearing;
    @Autowired
    private DbCheckService dbCheckService;
    @Autowired
    private MasposDBAccessService masposDBAccessService;
    @Autowired
    private SeaShellDBAccessService seaShellDBAccessService;
    @Autowired
    private StldbAccessService stldbAccessService;
    @Autowired
    private McsService mcsService;
    @Autowired
    private Pay pay;
    @Test(description = "MCS_CNP交易", dataProvider = "getData")
    public void CNPTest(Map<String, String> data) throws InterruptedException {
        if (!data.isEmpty()) {
            Reporter.start("----------测试开始----------测试场景：" + data.get("描述") + "--------------");

        }
        System.err.println(data);

        // 发送ATE充值交易请求
        Map<String, String> responseData = pay.getDealData(data);
        System.err.println(responseData.get("idTxn"));
        //修改T_TXN_CTRL交易表信息
        Reporter.log("----------手动结账----------");
        dbCheckService.updateTTxnCtrl(responseData.get("idTxn"));
        Thread.sleep(5000);
        Reporter.log("----------执行今日清分定时任务 mcs.quartz.txn.PostingTodayTxnTrigger----------");
        mcsService.executeClear(responseData.get("idTxn"));
        // 校验T_CLR_TXN_LIST清分表
        TClrTxnList tClrTxnList = masposDBAccessService.queryTClrTxnListTable(responseData.get("idTxn"));

        dbCheckService.checkDataFromTClrTxnListAtMCSClear(tClrTxnList, data);

        // 校验T_STL_LIST结算明细表
        TStlList tStlListOne = masposDBAccessService.queryTStlListTable(responseData.get("idTxn"));



        dbCheckService.checkDataFromTStlListAtMCSClear(tStlListOne, data);

        Reporter.log("----------执行异步记账定时任务 mcs.quartz.cpspe.async.BookkeepingTrigger----------");
        mcsService.cpspeSettle();

        // 校验T_BK_APPLY表
        TBkApply tBkApplyOne = stldbAccessService.queryTBkApplyTable(responseData.get("idTxn"));
        dbCheckService.checkDataFromTBkApply(tBkApplyOne, data);

        // 校验T_BK_RECORD表
        TBkRecord tBkRecordOne = stldbAccessService.queryTBkRecordTable(responseData.get("idTxn"));
        dbCheckService.checkDataFromTBkRecord(tBkRecordOne, data);

//        // 校验T_BK_PE_PAYMENT_ORDER表
//        TBkPePaymentOrder tBkPePaymentOrderOne = stldbAccessService.queryTBkPePaymentOrderTable(responseData.get("idTxn"));
//        dbCheckService.checkDataFromTBkPePaymentOrderAtCSPPEClear(tBkPePaymentOrderOne, data);
//
//        // 校验T_BK_PE_DEAL表
//        List<TBkPeDeal> tBkPeDealsOne = stldbAccessService.queryTBkPeDealTable(tBkPePaymentOrderOne
//                .getIdBkPePaymentOrder());
//        dbCheckService.checkDataFromTBkPeDealAtCSPPEClear(tBkPeDealsOne, data);

        Reporter.log("----------执行结算定时任务 autoSettlementProcessorTrigger----------");
        mcsService.autoSettle();

        // 校验T_STL_LIST结算明细表
        TStlList tStlListTwo = masposDBAccessService.queryTStlListTable(responseData.get("idTxn"));
        dbCheckService.checkDataFromTStlListAtMCSSettlement(tStlListTwo);

        // 校验T_STL_ORDER结算指令表
        TStlOrder tStlOrder = masposDBAccessService.queryTStlOrderTable(tStlListTwo.getIdStlOrder());
        List<TStlList> tStlLists = masposDBAccessService.queryTStlListTableList(tStlListTwo.getIdStlOrder());
        dbCheckService.checkDataFromTStlOrderAtMCSSettlement(tStlOrder, tStlLists);

        // 校验T_BK_APPLY表
        TBkApply tBkApplyTwo = stldbAccessService.queryTBkApplyTable(tStlListTwo.getIdStlOrder());
        dbCheckService.checkDataFromTBkApply(tBkApplyTwo, data);

        // 校验T_BK_RECORD表
        TBkRecord tBkRecordTwo = stldbAccessService.queryTBkRecordTable(tStlListTwo.getIdStlOrder());
        dbCheckService.checkDataFromTBkRecord(tBkRecordTwo, data);

        // 校验T_BK_PE_PAYMENT_ORDER表
        TBkPePaymentOrder tBkPePaymentOrderTwo = stldbAccessService.queryTBkPePaymentOrderTable(tStlListTwo
                .getIdStlOrder());
        dbCheckService.checkDataFromTBkPePaymentOrderAtCSPPESettlement(tBkPePaymentOrderTwo, tStlOrder);

        // 校验T_BK_PE_DEAL表
        List<TBkPeDeal> tBkPeDealsTwo = stldbAccessService.queryTBkPeDealTable(tBkPePaymentOrderTwo
                .getIdBkPePaymentOrder());
        dbCheckService.checkDataFromTBkPeDealAtCSPPESettlement(tBkPeDealsTwo, tStlOrder);

//        // 校验PAYMENTORDER表
//        PaymentOrder paymentOrderOne = seaShellDBAccessService.queryPaymentOrderTable(tStlListOne.getIdStlOrder());
//        dbCheckService.checkDataFromPaymentOrderAtPEClear(paymentOrderOne, data);
//
//        // 校验DEAL表
//        List<Deal> dealsOne = seaShellDBAccessService.queryDealTable(paymentOrderOne.getSequenceid());
//        dbCheckService.checkDataFromDealAtPEClear(dealsOne, data);

        // 校验ENTRY表
        ArrayList<Deal> clearDealList= new ArrayList();
        Deal clearDeal = new Deal();
        clearDeal.setDealid("CPS1001_"+responseData.get("idTxn"));
        clearDealList.add(clearDeal);
        List<Entry> entriesOne = seaShellDBAccessService.queryEntryTableByDealIds(clearDealList);
        dbCheckService.checkDataFromEntryAtPEClear(entriesOne, data);

        // 校验PAYMENTORDER表
        System.err.println("tStlListTwo.getIdStlOrder()"+tStlListTwo.getIdStlOrder());
        PaymentOrder paymentOrderTwo = seaShellDBAccessService.queryPaymentOrderTable(tStlListTwo.getIdStlOrder(),114);
        dbCheckService.checkDataFromPaymentOrderAtPESettlement(paymentOrderTwo, tStlOrder);

        // 校验DEAL表
        List<Deal> dealsTwo = seaShellDBAccessService.queryDealTable(paymentOrderTwo.getSequenceid());
        dbCheckService.checkDataFromDealAtPESettlement(dealsTwo, tStlOrder);

        // 校验ENTRY表
        List<Entry> entriesTwo = seaShellDBAccessService.queryEntryTableByDealIds(dealsTwo);
        dbCheckService.checkDataFromEntryAtPESettlement(entriesTwo, tStlOrder);

        Reporter.end("----------测试结束----------测试场景：" + data.get("描述") + "--------------");

    }


    @AfterClass
    public void afterTest() {

    }
    @DataProvider()
    private Iterator<Object[]> getData(Method method) {
        return ExcelProviderByEnv(this, method.getName());
    }

}
