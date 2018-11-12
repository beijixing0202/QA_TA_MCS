package com.bill99.ate.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RefundApplyDto {
    private BigDecimal refundApplyId;

    private BigDecimal masterId;

    private String outTradeNo;

    private String merchantCode;

    private String appId;

    private Short channelType;

    private Short functionCode;

    private String outRefundNo;

    private String refundChannel;

    private String operator;

    private Short refundStatus;

    private Date refundTime;

    private Date applyTime;

    private BigDecimal refundAmount;

    private String refundSerialId;

    private String subTerminalId;

    private String deviceInfo;

    private String memberCode;

    private String errorCode;

    private String errorInfo;

    private String memo;

    private Date createTime;

    private Date updateTime;

    private String billOrderId;

    public BigDecimal getRefundApplyId() {
        return refundApplyId;
    }

    public void setRefundApplyId(BigDecimal refundApplyId) {
        this.refundApplyId = refundApplyId;
    }

    public BigDecimal getMasterId() {
        return masterId;
    }

    public void setMasterId(BigDecimal masterId) {
        this.masterId = masterId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Short getChannelType() {
        return channelType;
    }

    public void setChannelType(Short channelType) {
        this.channelType = channelType;
    }

    public Short getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(Short functionCode) {
        this.functionCode = functionCode;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo == null ? null : outRefundNo.trim();
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel == null ? null : refundChannel.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Short getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Short refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundSerialId() {
        return refundSerialId;
    }

    public void setRefundSerialId(String refundSerialId) {
        this.refundSerialId = refundSerialId == null ? null : refundSerialId.trim();
    }

    public String getSubTerminalId() {
        return subTerminalId;
    }

    public void setSubTerminalId(String subTerminalId) {
        this.subTerminalId = subTerminalId == null ? null : subTerminalId.trim();
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo == null ? null : errorInfo.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBillOrderId() {
        return billOrderId;
    }

    public void setBillOrderId(String billOrderId) {
        this.billOrderId = billOrderId == null ? null : billOrderId.trim();
    }
}