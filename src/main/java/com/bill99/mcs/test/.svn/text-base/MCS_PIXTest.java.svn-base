package com.bill99.mcs.test;

import com.bill99.mcs.common.dto.*;
import com.bill99.mcs.orm.DbCheckService;
import com.bill99.mcs.orm.MasposDBAccessService;
import com.bill99.mcs.orm.SeaShellDBAccessService;
import com.bill99.mcs.orm.StldbAccessService;
import com.bill99.mcs.service.McsService;
import com.bill99.mcs.service.Pay;
import com.bill99.qa.framework.testcase.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MCS_PIXTest extends BaseTestCase {
    @Autowired
    private DbCheckService dbCheckService;

    @Autowired
    private McsService mcsService;

    @Autowired
    private MasposDBAccessService masposDBAccessService;

    @Autowired
    private StldbAccessService stldbAccessService;

    @Autowired
    private SeaShellDBAccessService seaShellDBAccessService;

    @Autowired
    private Pay pay;

    @BeforeClass
    public void beforeTest() {

    }
    @Test(description = "MCS_PIX交易", dataProvider = "getData")
    public void PIXTest(Map<String, String> data) throws InterruptedException {
        if (!data.isEmpty()) {
            Reporter.start("----------测试开始----------测试场景：" + data.get("描述") + "--------------");

        }
        System.err.println(data);

        // 发送PIX消费
        Map<String, String> responseData = pay.getDealData(data);
        dbCheckService.updateTTxnCtrlPix("update t_txn_ctrl set stl_flg='1',term_recon_flg='C' WHERE id_txn="+ responseData.get("idTxn"));
        Thread.sleep(5000);
        Reporter.log("----------执行今日清分定时任务 mcs.quartz.txn.MySQL_PostingTodayTxnTrigger----------");
        mcsService.executeClearPix(responseData.get("idTxn"));
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
