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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description: ATE 3.0绑卡
 * Author: zhenfeng.liu
 * Date: 2017/10/10 17:05
 */
public class BDCA_AteBindCardTest extends BaseTestCase {
   /* @Autowired
    private AteBindCardSubmitService ateBindCardSubmitService;*/

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

    @Test(description = "BDCA Ate绑卡不叠加权益T+1消费", dataProvider = "getData")
    public void testAteBindCardNoCouponT1Pay(Map<String, String> data) {
        if (!data.isEmpty()) {
            Reporter.start("----------测试开始----------测试场景：" + data.get("描述") + "--------------");
            
        }

        // 发送Ate绑卡不叠加权益T+1消费请求
        Map<String, String> responseData = pay.getDealData(data);
        

        // 校验T_TXN_CTRL交易控制表
        Map<String, String> dbData = dbCheckService.checkDataFromTTxnCtrlAtCPS(responseData.get("billOrderNo"), data);
        System.err.println(dbData.get("ID_TXN"));
        //修改T_TXN_CTRL交易表信息
        dbCheckService.updateTTxnCtrl(dbData.get("ID_TXN"));
        
        Reporter.log("----------执行今日清分定时任务 mcs.quartz.txn.PostingTodayTxnTrigger----------");
        mcsService.executeClear(dbData.get("ID_TXN"));
             
        
          
        // 校验T_CLR_TXN_LIST清分表
        TClrTxnList tClrTxnList = masposDBAccessService.queryTClrTxnListTable(dbData.get("ID_TXN"));
        
        dbCheckService.checkDataFromTClrTxnListAtMCSClear(tClrTxnList, data);

        // 校验T_STL_LIST结算明细表
        TStlList tStlListOne = masposDBAccessService.queryTStlListTable(dbData.get("ID_TXN"));
        
       
        
        dbCheckService.checkDataFromTStlListAtMCSClear(tStlListOne, data);

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
        List<TBkPeDeal> tBkPeDealsOne = stldbAccessService.queryTBkPeDealTable(tBkPePaymentOrderOne
                .getIdBkPePaymentOrder());
        dbCheckService.checkDataFromTBkPeDealAtCSPPEClear(tBkPeDealsOne, data);

        Reporter.log("----------执行结算定时任务 autoSettlementProcessorTrigger----------");
        mcsService.autoSettle();

        // 校验T_STL_LIST结算明细表
        TStlList tStlListTwo = masposDBAccessService.queryTStlListTable(dbData.get("ID_TXN"));
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

        // 校验PAYMENTORDER表
        PaymentOrder paymentOrderOne = seaShellDBAccessService.queryPaymentOrderTable(dbData.get("ID_TXN"),710);
        dbCheckService.checkDataFromPaymentOrderAtPEClear(paymentOrderOne, data);

        // 校验DEAL表
        List<Deal> dealsOne = seaShellDBAccessService.queryDealTable(paymentOrderOne.getSequenceid());
        dbCheckService.checkDataFromDealAtPEClear(dealsOne, data);

        // 校验ENTRY表
        List<Entry> entriesOne = seaShellDBAccessService.queryEntryTableByDealIds(dealsOne);
        dbCheckService.checkDataFromEntryAtPEClear(entriesOne, data);

        // 校验PAYMENTORDER表
        PaymentOrder paymentOrderTwo = seaShellDBAccessService.queryPaymentOrderTable(tStlListTwo.getIdStlOrder(),714);
        dbCheckService.checkDataFromPaymentOrderAtPESettlement(paymentOrderTwo, tStlOrder);

        // 校验DEAL表
        List<Deal> dealsTwo = seaShellDBAccessService.queryDealTable(paymentOrderTwo.getSequenceid());
        dbCheckService.checkDataFromDealAtPESettlement(dealsTwo, tStlOrder);

        // 校验ENTRY表
        List<Entry> entriesTwo = seaShellDBAccessService.queryEntryTableByDealIds(dealsTwo);
        dbCheckService.checkDataFromEntryAtPESettlement(entriesTwo, tStlOrder);

        Reporter.end("----------测试结束----------测试场景：" + data.get("描述") + "--------------");
    }

//    @Test(description = "BDCA Ate绑卡不叠加T+1退货", dataProvider = "getData")
//    public void testAteBindCardNoCouponT1Refund(Map<String, String> data) {
//
//    }
//
//    @Test(description = "BDCA Ate绑卡叠加权益T+1消费", dataProvider = "getData")
//    public void testAteBindCardWithCouponT1Pay(Map<String, String> data) {
//
//    }
//
//    @Test(description = "BDCA Ate绑卡叠加权益T+1退货", dataProvider = "getData")
//    public void testBindCardWithCouponT1Refund(Map<String, String> data) {
//
//    }

    @AfterClass
    public void afterTest() {

    }

    @DataProvider()
    private Iterator<Object[]> getData(Method method) {
        return ExcelProviderByEnv(this, method.getName());
    }
    
    @Test
    public void test(){
    	
    }
}

