package com.bill99.mcs.test;

import com.bill99.mcs.common.dto.*;
import com.bill99.mcs.orm.*;
import com.bill99.mcs.service.McsService;
import com.bill99.mcs.service.Pay;
import com.bill99.qa.framework.testcase.BaseTestCase;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description: ATE 充值
 * Author: shengyuan.wang
 */
public class MCS_AcctDepoistTest extends BaseTestCase {

   
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

    
    @Test(description = "MCS_ATE充值交易", dataProvider = "getData")
    public void AteAcctDepositPay(Map<String, String> data) throws InterruptedException {
        if (!data.isEmpty()) {
            Reporter.start("----------测试开始----------测试场景：" + data.get("描述") + "--------------");

            
        }


       // dbCheckService.checkDataFromTTxnCtrlPIX("",data);
        
        // 发送ATE充值交易请求
        Map<String, String> responseData = pay.getDealData(data);

        dbCheckService.updateTTxnCtrl(responseData.get("refIdTxn"));//authnetid=00010100

        Reporter.log("----------执行今日清分定时任务 mcs.quartz.txn.PostingTodayTxnTrigger----------");
        mcsService.executeClear(responseData.get("refIdTxn"));
        
        // 校验T_ACCT_TXN交易控制表
        TAcctTxn tAcctTxn = seaShellDBAccessService.queryTAcctTxnTable(responseData.get("tradeId"));
        dbCheckService.checkDataFromTAcctTxn(tAcctTxn,data);
        
        // 校验T_TXN_CTRL交易控制表
       Map<String, String> dbData = dbCheckService.checkDataFromTTxnCtrlAtCPS(responseData.get("billOrderNo"), data);
             
        // 校验T_CLR_TXN_LIST清分表
       TClrTxnList tClrTxnList = masposDBAccessService.queryTClrTxnListTable(dbData.get("ID_TXN"));
       dbCheckService.checkDataFromTClrTxnListAtMCSClear(tClrTxnList, data);
       
       // 校验T_STL_LIST结算明细表
        dbCheckService.checkTStlListNum(dbData.get("ID_TXN"),data);

       Reporter.log("----------执行异步记账定时任务 mcs.quartz.cpspe.async.BookkeepingTrigger----------");
       mcsService.cpspeSettle();
       
       // 校验T_BK_APPLY表
       TBkApply tBkApplyOne = stldbAccessService.queryTBkApplyTable(dbData.get("ID_TXN"));
       dbCheckService.checkDataFromTBkApply(tBkApplyOne, data);
       
       // 校验T_BK_RECORD表
       TBkRecord tBkRecordOne = stldbAccessService.queryTBkRecordTable(dbData.get("ID_TXN"));
       dbCheckService.checkDataFromTBkRecord(tBkRecordOne, data);
       
       // 校验T_BK_PE_PAYMENT_ORDER表
       TBkPePaymentOrder tBkPePaymentOrderOne = stldbAccessService.queryTBkPePaymentOrderTable(dbData.get("ID_TXN"));
       dbCheckService.checkDataFromTBkPePaymentOrderAtCSPPEClear(tBkPePaymentOrderOne, data);

       // 校验T_BK_PE_DEAL表
       List<TBkPeDeal> tBkPeDeals = stldbAccessService.queryTBkPeDealTable(tBkPePaymentOrderOne
               .getIdBkPePaymentOrder());
       dbCheckService.checkDataFromTBkPeDealAtCSPPEClearOne(tBkPeDeals.get(0), data);
     

       // 校验PAYMENTORDER表
        Thread.sleep(5000);
        System.err.println(dbData.get("ID_TXN"));
       PaymentOrder paymentOrderOne = seaShellDBAccessService.queryPaymentOrderTable(dbData.get("ID_TXN"),711);
       dbCheckService.checkDataFromPaymentOrderAtPEClear(paymentOrderOne, data);


       // 校验DEAL表
       List<Deal> dealsOne = seaShellDBAccessService.queryDealTable(paymentOrderOne.getSequenceid());
       dbCheckService.checkDataFromDealAtPEClear(dealsOne, data);

       // 校验ENTRY表
       List<Entry> entriesOne = seaShellDBAccessService.queryEntryTableByDealIds(dealsOne);
       dbCheckService.checkDataFromEntryPEClear(entriesOne, data);
      
       // ACS 调PE记账
       // 校验PAYMENTORDER表
       PaymentOrder acsPEpaymentOrderOne = seaShellDBAccessService.queryPaymentOrderTable(responseData.get("tradeId"),711);
       dbCheckService.checkDataFromPaymentOrderACSPEClear(acsPEpaymentOrderOne, data);


       // 校验DEAL表
       List<Deal> acsPEdealsOne = seaShellDBAccessService.queryDealTable(acsPEpaymentOrderOne.getSequenceid());
       dbCheckService.checkDataFromDealACSPEClear(acsPEdealsOne, data);

       // 校验ENTRY表
       List<Entry> acsPEentriesOne = seaShellDBAccessService.queryEntryTableByDealIds(acsPEdealsOne);
       dbCheckService.checkDataFromEntryACSPEClear(acsPEentriesOne, data);

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

