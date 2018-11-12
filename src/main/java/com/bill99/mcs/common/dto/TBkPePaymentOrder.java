package com.bill99.mcs.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: T_BK_PE_PAYMENT_ORDER
 * Author: zhenfeng.liu
 * Date: 2017/10/17 14:43
 */
public class TBkPePaymentOrder {
    private String idBkPePaymentOrder;
    private String orderId;
    private BigDecimal orderType;
    private double orderAmt;
    private BigDecimal orderStatus;
    private Date orderTime;
    private BigDecimal prodNum;
    private String prodName;
    private BigDecimal payMethod;
    private BigDecimal payerOrgType;
    private BigDecimal payerOrgCode;
    private BigDecimal payerAcctType;
    private String payerAcctCode;
    private BigDecimal payeeOrgType;
    private BigDecimal payeeOrgCode;
    private BigDecimal payeeAcctType;
    private String payeeAcctCode;
    private String version;
    private String memo;
    private Date crtTime;
    private Date updTime;
    private BigDecimal seqId;
    private String payerChan;
    private String payeeChan;
    private String relationSeqId;

    public String getIdBkPePaymentOrder() {
        return idBkPePaymentOrder;
    }

    public void setIdBkPePaymentOrder(String idBkPePaymentOrder) {
        this.idBkPePaymentOrder = idBkPePaymentOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderType() {
        return orderType;
    }

    public void setOrderType(BigDecimal orderType) {
        this.orderType = orderType;
    }

    public double getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(double orderAmt) {
        this.orderAmt = orderAmt;
    }

    public BigDecimal getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(BigDecimal orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getProdNum() {
        return prodNum;
    }

    public void setProdNum(BigDecimal prodNum) {
        this.prodNum = prodNum;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public BigDecimal getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(BigDecimal payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getPayerOrgType() {
        return payerOrgType;
    }

    public void setPayerOrgType(BigDecimal payerOrgType) {
        this.payerOrgType = payerOrgType;
    }

    public BigDecimal getPayerOrgCode() {
        return payerOrgCode;
    }

    public void setPayerOrgCode(BigDecimal payerOrgCode) {
        this.payerOrgCode = payerOrgCode;
    }

    public BigDecimal getPayerAcctType() {
        return payerAcctType;
    }

    public void setPayerAcctType(BigDecimal payerAcctType) {
        this.payerAcctType = payerAcctType;
    }

    public String getPayerAcctCode() {
        return payerAcctCode;
    }

    public void setPayerAcctCode(String payerAcctCode) {
        this.payerAcctCode = payerAcctCode;
    }

    public BigDecimal getPayeeOrgType() {
        return payeeOrgType;
    }

    public void setPayeeOrgType(BigDecimal payeeOrgType) {
        this.payeeOrgType = payeeOrgType;
    }

    public BigDecimal getPayeeOrgCode() {
        return payeeOrgCode;
    }

    public void setPayeeOrgCode(BigDecimal payeeOrgCode) {
        this.payeeOrgCode = payeeOrgCode;
    }

    public BigDecimal getPayeeAcctType() {
        return payeeAcctType;
    }

    public void setPayeeAcctType(BigDecimal payeeAcctType) {
        this.payeeAcctType = payeeAcctType;
    }

    public String getPayeeAcctCode() {
        return payeeAcctCode;
    }

    public void setPayeeAcctCode(String payeeAcctCode) {
        this.payeeAcctCode = payeeAcctCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public BigDecimal getSeqId() {
        return seqId;
    }

    public void setSeqId(BigDecimal seqId) {
        this.seqId = seqId;
    }

    public String getPayerChan() {
        return payerChan;
    }

    public void setPayerChan(String payerChan) {
        this.payerChan = payerChan;
    }

    public String getPayeeChan() {
        return payeeChan;
    }

    public void setPayeeChan(String payeeChan) {
        this.payeeChan = payeeChan;
    }

    public String getRelationSeqId() {
        return relationSeqId;
    }

    public void setRelationSeqId(String relationSeqId) {
        this.relationSeqId = relationSeqId;
    }

    @Override
    public String toString() {
        return "TBkPePaymentOrder{" +
                "idBkPePaymentOrder=" + idBkPePaymentOrder +
                ", orderId='" + orderId + '\'' +
                ", orderType=" + orderType +
                ", orderAmt=" + orderAmt +
                ", orderStatus=" + orderStatus +
                ", orderTime=" + orderTime +
                ", prodNum=" + prodNum +
                ", prodName='" + prodName + '\'' +
                ", payMethod=" + payMethod +
                ", payerOrgType=" + payerOrgType +
                ", payerOrgCode=" + payerOrgCode +
                ", payerAcctType=" + payerAcctType +
                ", payerAcctCode='" + payerAcctCode + '\'' +
                ", payeeOrgType=" + payeeOrgType +
                ", payeeOrgCode=" + payeeOrgCode +
                ", payeeAcctType=" + payeeAcctType +
                ", payeeAcctCode='" + payeeAcctCode + '\'' +
                ", version='" + version + '\'' +
                ", memo='" + memo + '\'' +
                ", crtTime=" + crtTime +
                ", updTime=" + updTime +
                ", seqId=" + seqId +
                ", payerChan='" + payerChan + '\'' +
                ", payeeChan='" + payeeChan + '\'' +
                ", relationSeqId='" + relationSeqId + '\'' +
                '}';
    }
}
