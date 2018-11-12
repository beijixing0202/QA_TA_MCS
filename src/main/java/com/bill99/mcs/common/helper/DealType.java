package com.bill99.mcs.common.helper;

/**
 * Created by wentao.jia on 2018/9/20.
 */
public enum DealType {
    ATE_PAY("ATE_PAY"),
    ACCT_DEPOSIT("ACCT_DEPOSIT"),
    PIX_CUP("PIX_CUP"),
    CPS_CNP("CPS_CNP"),
    ATE_RFD("ATE_RFD"),
    PIX_CUP_RFD("PIX_CUP_RFD");


    //入口类型
    private String entryType;


    private DealType(String entryType) {
        this.entryType = entryType;

    }

    public String getEntryType() {
        return entryType;
    }




}
