package com.bill99.mcs.service.impl;

import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;
import org.testng.Reporter;

import shelper.iffixture.HttpFixture;

//import com.bill99.mcs.page.IdsLoginPageHttp;
//import com.bill99.mcs.page.IdsManualCutOffPageHttp;
import com.bill99.mcs.page.McsWebLoginPageHttp;
import com.bill99.mcs.page.McsWebMerchantItemsIntoAccountPageHttp;
import com.bill99.mcs.page.McsWebSettlementOrderQueryPageHttp;
import com.bill99.mcs.service.McsService;
import com.bill99.qa.ta.cps.orm.mng.CpsDbMng;
import com.bill99.qa.ta.monitor.mng.MonitorQuartzFrameworkMng;

public class McsServiceImpl implements McsService {

    private HttpFixture hf;

    private String mcsUrl;
    private String idsUrl;
    private String cpsUser;
    private String cpsPass;
    private String fscUser;
    private String fscPass;


    MonitorQuartzFrameworkMng monitorQuartzFrameworkMng;
    CpsDbMng cpsDbMng;

    public void setMcsUrl(String mcsUrl) {
        this.mcsUrl = mcsUrl;
    }

    public void setIdsUrl(String idsUrl) {
        this.idsUrl = idsUrl;
    }

    public void setCpsUser(String cpsUser) {
        this.cpsUser = cpsUser;
    }

    public void setCpsPass(String cpsPass) {
        this.cpsPass = cpsPass;
    }

    public void setFscUser(String fscUser) {
        this.fscUser = fscUser;
    }

    public void setFscPass(String fscPass) {
        this.fscPass = fscPass;
    }

    public void setMonitorQuartzFrameworkMng(
            MonitorQuartzFrameworkMng monitorQuartzFrameworkMng) {
        this.monitorQuartzFrameworkMng = monitorQuartzFrameworkMng;
    }

    public void setCpsDbMng(CpsDbMng cpsDbMng) {
        this.cpsDbMng = cpsDbMng;
    }

    public McsServiceImpl() {
        hf = new HttpFixture();
    }


    /**
     * 今日清分job:mcs.quartz.txn.PostingTodayTxnTrigger
     *
     * @param idTxn
     * @return
     */
    @Override
    public boolean executeClear(String idTxn) {
        //执行今日清分job
        System.out.print("==========开始执行清分任务================");
        cpsDbMng.setTxnReconStatusBeforeClear(idTxn);
        try {
            monitorQuartzFrameworkMng.runJob(cpsUser, cpsPass, "CPS.MCS.QUARTZ", "mcs.quartz.txn" +
                    ".PostingTodayTxnTrigger");
//            Thread.sleep(8000);
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpsDbMng.checkTxnToClearList(idTxn);

    }

    @Override
    public boolean  executeClearPix(String idTxn){
        //执行今日清分job
        System.out.print("==========开始执行清分任务================");
        try {
            monitorQuartzFrameworkMng.runJob(cpsUser, cpsPass, "CPS.MCS.QUARTZ", "mcs.quartz.txn.MySQL_PostingTodayTxnTrigger");
//            Thread.sleep(8000);
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpsDbMng.checkTxnToClearList(idTxn);

    }

    /**
     * @Description: cpspe结算job:mcs.quartz.cpspe.async.BookkeepingTrigger
     */
    public void cpspeSettle() {
        //执行cpspe结算job
        //cpsDbMng.setTxnReconStatusBeforeClear(idTxn);
        try {
            monitorQuartzFrameworkMng.runJob(cpsUser, cpsPass, "CPS.MCS.QUARTZ", "mcs.quartz.cpspe.async" +
                    ".BookkeepingTrigger");
//            Thread.sleep(5000);
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return cpsDbMng.checkTxnToClearList(idTxn);

    }

    /**
     * @Description: 自动结算job:autoSettlementProcessorTrigger
     */
    public void autoSettle() {
        //执行autoSettlement结算job
        //cpsDbMng.setTxnReconStatusBeforeClear(idTxn);
        try {
            monitorQuartzFrameworkMng.runJob(cpsUser, cpsPass, "CPS.MCS", "autoSettlementProcessorTrigger");
//            Thread.sleep(8000);
            TimeUnit.SECONDS.sleep(119);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return cpsDbMng.checkTxnToClearList(idTxn);

    }

    /**
     * @Description: 余额T0, 今日推送job：acs.quartz.acctTxn.PostingTodayTxnTrigger,结算
     */
    public void acsT0Settle() {
        //执行autoSettlement结算job
        //cpsDbMng.setTxnReconStatusBeforeClear(idTxn);
        try {
            monitorQuartzFrameworkMng.runJob(fscUser, fscPass, "fsc.acs.quartz",
                    "acs.quartz.acctTxn.PostingTodayTxnTrigger");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description: 余额T1, 昨日清分job：acs.quartz.acctTxn.PostingYesterdayTrigger,结算
     */
    public void acsT1Settle() {
        //执行autoSettlement结算job
        //cpsDbMng.setTxnReconStatusBeforeClear(idTxn);
        try {
            monitorQuartzFrameworkMng.runJob(fscUser, fscPass, "fsc.acs.quartz",
                    "acs.quartz.acctTxn.PostingYesterdayTrigger");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public String merchantItemsEnteredInAccount(String merchantId,
                                                String sessionID) {
        McsWebMerchantItemsIntoAccountPageHttp mcsWebMerchantItemsIntoAccountPageHttp = new
                McsWebMerchantItemsIntoAccountPageHttp(
                hf, mcsUrl);
        Reporter.log("商户入账，生成结算指令");
        mcsWebMerchantItemsIntoAccountPageHttp.settleMerchant(merchantId, 0, 1,
                sessionID);
        Date reconDate = cpsDbMng.getReconDate();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String stlOrderId = "";

        for (int i = 0; i < 50; i++) {
            stlOrderId = cpsDbMng.getIdStlOrder(merchantId,
                    new SimpleDateFormat("yyyy-MM-dd").format(reconDate));
            if (StringUtils.hasLength(stlOrderId)) {
                break;
            }

        }

        Reporter.log("查询生成的结算指令编号：" + stlOrderId);
        return stlOrderId;
    }

    @Override
    public boolean executeSettleOrder(String settleOrderId, String sessionID) {
        McsWebSettlementOrderQueryPageHttp mcsWebSettlementOrderQueryPageHttp = new McsWebSettlementOrderQueryPageHttp(
                hf, mcsUrl);
        Reporter.log("执行前指令的结算状态："
                + cpsDbMng.getSettleOrderStatus(settleOrderId));
        boolean checkStatusBeforeFlag = cpsDbMng.getSettleOrderStatus(
                settleOrderId).equals("0");
        Reporter.log("执行结算指令：" + settleOrderId);
        mcsWebSettlementOrderQueryPageHttp.excuteSettleOrder(settleOrderId,
                "tester", "99bill.com", sessionID);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reporter.log("执行后指令的结算状态："
                + cpsDbMng.getSettleOrderStatus(settleOrderId));
        boolean checkStatusAferFlag = cpsDbMng.getSettleOrderStatus(
                settleOrderId).equals("1");
        return checkStatusBeforeFlag && checkStatusAferFlag;

    }

    @Override
    public String mcsLogin(String username, String password, String verifyCode) {
        McsWebLoginPageHttp mcsWebLoginPageHttp = new McsWebLoginPageHttp(hf,
                mcsUrl);
        return mcsWebLoginPageHttp.login(username, password, verifyCode);

    }

    @Override
    public boolean idsManualCutOff() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date reconDateBeforeCutOff = cpsDbMng.getReconDate();
        Reporter.log("日切前的对账日期：" + sdf.format(reconDateBeforeCutOff));
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(reconDateBeforeCutOff);
//		cal.add(Calendar.DATE, 1);
//		String expectedReconDate = sdf.format(cal.getTime());
//		String actualReconDate = "";
//		Reporter.log("登录IDS系统开始手工日切");
//		IdsLoginPageHttp IdsLoginPageHttp = new IdsLoginPageHttp(hf, idsUrl);
//		IdsLoginPageHttp.login("zhenghz", "1qaz@2wsx");
//		IdsManualCutOffPageHttp manualCutOffPage = new IdsManualCutOffPageHttp(
//				hf, idsUrl);
//		if (manualCutOffPage.executeCutOff()) {
//			Date reconDateAfterCutOff = cpsDbMng.getReconDate();
//			Reporter.log("日切后的对账日期：" + sdf.format(reconDateAfterCutOff));
//			cal.setTime(reconDateAfterCutOff);
//			actualReconDate = sdf.format(cal.getTime());
//		}
//		return expectedReconDate.equals(actualReconDate);
        return true;
    }

    @Override
    public boolean clearAndSettle(String merchantId, String idTxn) {
        executeClear(idTxn);
        idsManualCutOff();
        cpspeSettle();
//		String sessionId = mcsLogin("chongpeng.yan@99bill.com", "super@130815", "333");
//		String orderId = merchantItemsEnteredInAccount(merchantId, sessionId);
//		executeSettleOrder(orderId, sessionId);
        autoSettle();
        String txnSettleStatus = "";
        for (int i = 0; i < 50; i++) {
            txnSettleStatus = cpsDbMng.getTxnSettleStatus(merchantId, idTxn);
            if (txnSettleStatus.equals("2")) {
                break;
            } else
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        Reporter.log(idTxn + "完成清结算后查询交易结算状态：" + txnSettleStatus);

        return txnSettleStatus.equals("2") || txnSettleStatus.equals("1");
    }

    @Override
    public boolean jobRfdSettle(String merchantId, String idTxn) {

        idsManualCutOff();
        autoSettle();
        executeClear(idTxn);
        cpspeSettle();
        //String sessionId = mcsLogin("chongpeng.yan@99bill.com", "super@130815", "333");
        //String orderId = merchantItemsEnteredInAccount(merchantId, sessionId);
        //executeSettleOrder(orderId, sessionId);

        String txnSettleStatus = "";
        for (int i = 0; i < 50; i++) {
            txnSettleStatus = cpsDbMng.getTxnSettleStatus(merchantId, idTxn);
            if (txnSettleStatus.equals("2")) {
                break;
            } else
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        Reporter.log(idTxn + "完成清结算后查询交易结算状态：" + txnSettleStatus);

        return txnSettleStatus.equals("2") || txnSettleStatus.equals("1");
    }

    @Override
    public boolean preClear(String idTxn) {

        idsManualCutOff();
        //autoSettle();
        executeClear(idTxn);
        cpspeSettle();
        //String sessionId = mcsLogin("chongpeng.yan@99bill.com", "super@130815", "333");
        //String orderId = merchantItemsEnteredInAccount(merchantId, sessionId);
        //executeSettleOrder(orderId, sessionId);
        boolean txnSettleStatus = false;
        for (int i = 0; i < 50; i++) {
            txnSettleStatus = cpsDbMng.checkTxnToClearList(idTxn);
            if (txnSettleStatus) {
                break;
            } else
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        Reporter.log(idTxn + "清分job执行后后查询交易清分记录：" + txnSettleStatus);

        return txnSettleStatus;
    }

    @Override
    public String idTxn2StlOrderId(String merchantId) {

        Date reconDate = cpsDbMng.getReconDate();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String stlOrderId = "";

        for (int i = 0; i < 50; i++) {
            stlOrderId = cpsDbMng.getIdStlOrder(merchantId,
                    new SimpleDateFormat("yyyy-MM-dd").format(reconDate));
            if (StringUtils.hasLength(stlOrderId)) {
                break;
            }

        }

        Reporter.log("查询生成的结算指令编号：" + stlOrderId);
        return stlOrderId;
    }

}
