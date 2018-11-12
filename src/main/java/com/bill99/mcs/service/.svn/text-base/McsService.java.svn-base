package com.bill99.mcs.service;

public interface McsService {
    /**
     * 今日清分job:mcs.quartz.txn.PostingTodayTxnTrigger
     *
     * @param idTxn
     * @return
     */
    boolean executeClear(String idTxn);

    boolean executeClearPix(String idTxn);

    /**
     * 结算job：autoSettlementProcessorTrigger
     */
    void autoSettle();

    /**
     * 异步记账job：mcs.quartz.cpspe.async.BookkeepingTrigger
     */
    void cpspeSettle();

    /**
     * 手工日切
     */
    boolean idsManualCutOff();

    /**
     * mcs登录
     *
     * @param username
     * @param password
     * @param verifyCode
     * @return
     */
    String mcsLogin(String username, String password, String verifyCode);

    /**
     * 商户入账
     *
     * @param merchantId
     * @return
     */
    String merchantItemsEnteredInAccount(String merchantId, String sessionID);

    /**
     * 执行结算指令
     *
     * @param orderId
     * @return
     */
    boolean executeSettleOrder(String orderId, String sessionID);

    /**
     * 完成所有清结算操作
     *
     * @param merchantId
     * @param idTxn
     * @return
     */

    boolean clearAndSettle(String merchantId, String idTxn);

    /**
     * 退货结算清分
     *
     * @param merchantId
     * @param idTxn
     * @return
     */

    boolean jobRfdSettle(String merchantId, String idTxn);

    /**
     * 预授权结算清分
     *
     * @param idTxn
     * @return
     */
    boolean preClear(String idTxn);

    /**
     * 根据交易编号获取结算指令
     *
     * @param idTxn
     * @return
     */
    String idTxn2StlOrderId(String idTxn);
}
