package com.bill99.mcs.common.dto;

import java.util.Date;

/**
 * Created by wentao.jia on 2018/9/26.
 */
public class TMcsCtrl {
    private String ctrlId;
    private String appSrc;
    private String bizType;
    private String status;
    private String idTxn;
    private String origIdTxn;
    private String txnType;
    private String appType;
    private Date txnTime;
    private String amt;
    private String cur;
    private Date reconDate;
    private Date valueDate;
    private String txnFlg;
    private String postingFlg;
    private String stlFlg;
    private String txnStatus;
    private String cnpFlg;
    private String quickRfdFlag;
    private String srvEntryMode;
    private String srvCondCode;
    private String srvChanType;
    private String srvChanTraceNo;
    private String acquirerId;
    private String onUs;
    private String feeAmt;
    private String feeRate;
    private String discAmt;
    private String discRate;
    private String entryCvv2Flag;
    private String memberCode;
    private String stlMemberCode;
    private String merchantId;
    private String stlMerchantId;
    private String mcc;
    private String authNetId;
    private String authTime;
    private String authMerchantId;
    private String authTerminalId;
    private String authRef;
    private String authTraceNo;
    private String authInvoiceNo;
    private String authBatchNo;
    private String authCode;
    private String authExpTime;
    private String authRespCode;
    private String authReconFlg;
    private String idAuthTermBatch;
    private String terminalId;
    private String terminalName;
    private Date termTxnTime;
    private String termReconFlg;
    private String termTraceNo;
    private String termInvoiceNo;
    private String termBatchNo;
    private String extTraceNo;
    private String issuerId;
    private String pan;
    private String sPan;
    private String cardType;
    private String cardName;
    private String cardOrg;
    private String forCardOrg;
    private String rewardPts;
    private String termInMonths;
    private String pdtId;
    private String riskLv;
    private String riskResult;
    private String riskStatus;
    private String tunnelData;
    private Date crtTime;
    private Date uptTime;
    private Date expDate;
    private String stlBizFlg;
    private String billingAmt;

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public void setTxnTime(Date txnTime) {
        this.txnTime = txnTime;
    }

    public void setStlFlg(String stlFlg) {
        this.stlFlg = stlFlg;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public void setPostingFlg(String postingFlg) {
        this.postingFlg = postingFlg;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public void setAppSrc(String appSrc) {
        this.appSrc = appSrc;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getCur() {
        return cur;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public String getStlFlg() {
        return stlFlg;
    }

    public String getBizType() {
        return bizType;
    }

    public String getPostingFlg() {
        return postingFlg;
    }

    public String getAppType() {
        return appType;
    }

    public String getAppSrc() {
        return appSrc;
    }

    public String getAmt() {
        return amt;
    }

    public Date getTxnTime() {
        return txnTime;
    }

    public Date getReconDate() {
        return reconDate;
    }

    public String getCtrlId() {
        return ctrlId;
    }

    public String getIdTxn() {
        return idTxn;
    }

    public String getOrigIdTxn() {
        return origIdTxn;
    }

    public String getStatus() {
        return status;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public String getCnpFlg() {
        return cnpFlg;
    }

    public String getTxnFlg() {
        return txnFlg;
    }

    public void setCtrlId(String ctrlId) {
        this.ctrlId = ctrlId;
    }

    public String getQuickRfdFlag() {
        return quickRfdFlag;
    }

    public void setIdTxn(String idTxn) {
        this.idTxn = idTxn;
    }

    public void setOrigIdTxn(String origIdTxn) {
        this.origIdTxn = origIdTxn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public String getSrvEntryMode() {
        return srvEntryMode;
    }

    public void setReconDate(Date reconDate) {
        this.reconDate = reconDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public void setStlMemberCode(String stlMemberCode) {
        this.stlMemberCode = stlMemberCode;
    }

    public String getSrvChanTraceNo() {
        return srvChanTraceNo;
    }

    public void setCnpFlg(String cnpFlg) {
        this.cnpFlg = cnpFlg;
    }

    public void setTunnelData(String tunnelData) {
        this.tunnelData = tunnelData;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public void setTxnFlg(String txnFlg) {
        this.txnFlg = txnFlg;
    }

    public void setAuthNetId(String authNetId) {
        this.authNetId = authNetId;
    }

    public String getTunnelData() {
        return tunnelData;
    }

    public String getStlMemberCode() {
        return stlMemberCode;
    }

    public void setQuickRfdFlag(String quickRfdFlag) {
        this.quickRfdFlag = quickRfdFlag;
    }

    public void setBillingAmt(String billingAmt) {
        this.billingAmt = billingAmt;
    }

    public String getSrvChanType() {
        return srvChanType;
    }

    public String getBillingAmt() {
        return billingAmt;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public String getSrvCondCode() {
        return srvCondCode;
    }

    public String getAuthNetId() {
        return authNetId;
    }

    public String getOnUs() {
        return onUs;
    }

    public void setSrvEntryMode(String srvEntryMode) {
        this.srvEntryMode = srvEntryMode;
    }

    public void setSrvChanTraceNo(String srvChanTraceNo) {
        this.srvChanTraceNo = srvChanTraceNo;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public Date getExpDate() {
        return expDate;
    }

    public Date getTermTxnTime() {
        return termTxnTime;
    }

    public void setSrvChanType(String srvChanType) {
        this.srvChanType = srvChanType;
    }

    public Date getUptTime() {
        return uptTime;
    }

    public String getAuthBatchNo() {
        return authBatchNo;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    public String getAuthExpTime() {
        return authExpTime;
    }

    public String getAuthInvoiceNo() {
        return authInvoiceNo;
    }

    public String getAuthMerchantId() {
        return authMerchantId;
    }

    public String getAuthReconFlg() {
        return authReconFlg;
    }

    public String getAuthRef() {
        return authRef;
    }

    public String getAuthRespCode() {
        return authRespCode;
    }

    public String getAuthTerminalId() {
        return authTerminalId;
    }

    public String getAuthTime() {
        return authTime;
    }

    public String getAuthTraceNo() {
        return authTraceNo;
    }

    public void setSrvCondCode(String srvCondCode) {
        this.srvCondCode = srvCondCode;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardOrg() {
        return cardOrg;
    }

    public String getCardType() {
        return cardType;
    }

    public String getDiscAmt() {
        return discAmt;
    }

    public String getDiscRate() {
        return discRate;
    }

    public String getEntryCvv2Flag() {
        return entryCvv2Flag;
    }

    public String getExtTraceNo() {
        return extTraceNo;
    }

    public String getFeeAmt() {
        return feeAmt;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public String getForCardOrg() {
        return forCardOrg;
    }

    public String getIdAuthTermBatch() {
        return idAuthTermBatch;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getPan() {
        return pan;
    }

    public String getPdtId() {
        return pdtId;
    }

    public String getRewardPts() {
        return rewardPts;
    }

    public String getRiskLv() {
        return riskLv;
    }

    public String getRiskResult() {
        return riskResult;
    }

    public String getRiskStatus() {
        return riskStatus;
    }

    public String getsPan() {
        return sPan;
    }

    public String getStlBizFlg() {
        return stlBizFlg;
    }

    public String getStlMerchantId() {
        return stlMerchantId;
    }

    public String getTermBatchNo() {
        return termBatchNo;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public String getTermInMonths() {
        return termInMonths;
    }

    public String getTermInvoiceNo() {
        return termInvoiceNo;
    }

    public String getTermReconFlg() {
        return termReconFlg;
    }

    public String getTermTraceNo() {
        return termTraceNo;
    }

    public void setAuthBatchNo(String authBatchNo) {
        this.authBatchNo = authBatchNo;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setAuthExpTime(String authExpTime) {
        this.authExpTime = authExpTime;
    }

    public void setAuthInvoiceNo(String authInvoiceNo) {
        this.authInvoiceNo = authInvoiceNo;
    }

    public void setAuthMerchantId(String authMerchantId) {
        this.authMerchantId = authMerchantId;
    }

    public void setAuthReconFlg(String authReconFlg) {
        this.authReconFlg = authReconFlg;
    }

    public void setAuthRef(String authRef) {
        this.authRef = authRef;
    }

    public void setAuthRespCode(String authRespCode) {
        this.authRespCode = authRespCode;
    }

    public void setAuthTerminalId(String authTerminalId) {
        this.authTerminalId = authTerminalId;
    }

    public void setAuthTime(String authTime) {
        this.authTime = authTime;
    }

    public void setAuthTraceNo(String authTraceNo) {
        this.authTraceNo = authTraceNo;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setDiscAmt(String discAmt) {
        this.discAmt = discAmt;
    }

    public void setDiscRate(String discRate) {
        this.discRate = discRate;
    }

    public void setEntryCvv2Flag(String entryCvv2Flag) {
        this.entryCvv2Flag = entryCvv2Flag;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public void setExtTraceNo(String extTraceNo) {
        this.extTraceNo = extTraceNo;
    }

    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public void setForCardOrg(String forCardOrg) {
        this.forCardOrg = forCardOrg;
    }

    public void setIdAuthTermBatch(String idAuthTermBatch) {
        this.idAuthTermBatch = idAuthTermBatch;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setOnUs(String onUs) {
        this.onUs = onUs;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setPdtId(String pdtId) {
        this.pdtId = pdtId;
    }

    public void setRewardPts(String rewardPts) {
        this.rewardPts = rewardPts;
    }

    public void setRiskLv(String riskLv) {
        this.riskLv = riskLv;
    }

    public void setRiskResult(String riskResult) {
        this.riskResult = riskResult;
    }

    public void setRiskStatus(String riskStatus) {
        this.riskStatus = riskStatus;
    }

    public void setsPan(String sPan) {
        this.sPan = sPan;
    }

    public void setStlBizFlg(String stlBizFlg) {
        this.stlBizFlg = stlBizFlg;
    }

    public void setStlMerchantId(String stlMerchantId) {
        this.stlMerchantId = stlMerchantId;
    }

    public void setTermBatchNo(String termBatchNo) {
        this.termBatchNo = termBatchNo;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public void setTermInMonths(String termInMonths) {
        this.termInMonths = termInMonths;
    }

    public void setTermInvoiceNo(String termInvoiceNo) {
        this.termInvoiceNo = termInvoiceNo;
    }

    public void setTermReconFlg(String termReconFlg) {
        this.termReconFlg = termReconFlg;
    }

    public void setTermTraceNo(String termTraceNo) {
        this.termTraceNo = termTraceNo;
    }

    public void setTermTxnTime(Date termTxnTime) {
        this.termTxnTime = termTxnTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
