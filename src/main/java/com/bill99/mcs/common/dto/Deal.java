package com.bill99.mcs.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: seashell.DEAL
 * Author: zhenfeng.liu
 * Date: 2017/10/18 17:36
 */
public class Deal {
    private String dealid;
    private Date dealbegindate;
    private String orderSeqId;
    private double dealamount;
    private BigDecimal payeracctcode;
    private BigDecimal payeeacctcode;
    private int dealstatus;
    private Date dealenddate;
    private String orgorderid;
    private Short dealcode;
    private String paymentservicepkgcode;
    private Short payerorgcode;
    private Short payeeorgcode;
    private Date ordertime;
    private BigDecimal submitacctcode;
    private Short payeraccttype;
    private Short payerorgtype;
    private Short payeeaccttype;
    private Short payeeorgtype;
    private Short dealtype;
    private Long payeefee;
    private Long payerfee;
    private BigDecimal synctoken;
    private Short payeeservicelevel;
    private Short payerservicelevel;
    private String payercurrencycode;
    private Long exchangerate;
    private String payeecurrencycode;
    private Short hascaculatedprice;
    private Short asynaccounting;
    private Date transactiondate;
    private String payeechannel;
    private String payerchannel;
    private Short feetype;
    private BigDecimal payerfeeacctcode;
    private BigDecimal payeefeeacctcode;

    public String getDealid() {
        return dealid;
    }

    public void setDealid(String dealid) {
        this.dealid = dealid;
    }

    public Date getDealbegindate() {
        return dealbegindate;
    }

    public void setDealbegindate(Date dealbegindate) {
        this.dealbegindate = dealbegindate;
    }

    public String getOrderSeqId() {
        return orderSeqId;
    }

    public void setOrderSeqId(String orderSeqId) {
        this.orderSeqId = orderSeqId;
    }

    public double getDealamount() {
        return dealamount;
    }

    public void setDealamount(double dealamount) {
        this.dealamount = dealamount;
    }

    public BigDecimal getPayeracctcode() {
        return payeracctcode;
    }

    public void setPayeracctcode(BigDecimal payeracctcode) {
        this.payeracctcode = payeracctcode;
    }

    public BigDecimal getPayeeacctcode() {
        return payeeacctcode;
    }

    public void setPayeeacctcode(BigDecimal payeeacctcode) {
        this.payeeacctcode = payeeacctcode;
    }

    public int getDealstatus() {
        return dealstatus;
    }

    public void setDealstatus(int dealstatus) {
        this.dealstatus = dealstatus;
    }

    public Date getDealenddate() {
        return dealenddate;
    }

    public void setDealenddate(Date dealenddate) {
        this.dealenddate = dealenddate;
    }

    public String getOrgorderid() {
        return orgorderid;
    }

    public void setOrgorderid(String orgorderid) {
        this.orgorderid = orgorderid;
    }

    public Short getDealcode() {
        return dealcode;
    }

    public void setDealcode(Short dealcode) {
        this.dealcode = dealcode;
    }

    public String getPaymentservicepkgcode() {
        return paymentservicepkgcode;
    }

    public void setPaymentservicepkgcode(String paymentservicepkgcode) {
        this.paymentservicepkgcode = paymentservicepkgcode;
    }

    public Short getPayerorgcode() {
        return payerorgcode;
    }

    public void setPayerorgcode(Short payerorgcode) {
        this.payerorgcode = payerorgcode;
    }

    public Short getPayeeorgcode() {
        return payeeorgcode;
    }

    public void setPayeeorgcode(Short payeeorgcode) {
        this.payeeorgcode = payeeorgcode;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public BigDecimal getSubmitacctcode() {
        return submitacctcode;
    }

    public void setSubmitacctcode(BigDecimal submitacctcode) {
        this.submitacctcode = submitacctcode;
    }

    public Short getPayeraccttype() {
        return payeraccttype;
    }

    public void setPayeraccttype(Short payeraccttype) {
        this.payeraccttype = payeraccttype;
    }

    public Short getPayerorgtype() {
        return payerorgtype;
    }

    public void setPayerorgtype(Short payerorgtype) {
        this.payerorgtype = payerorgtype;
    }

    public Short getPayeeaccttype() {
        return payeeaccttype;
    }

    public void setPayeeaccttype(Short payeeaccttype) {
        this.payeeaccttype = payeeaccttype;
    }

    public Short getPayeeorgtype() {
        return payeeorgtype;
    }

    public void setPayeeorgtype(Short payeeorgtype) {
        this.payeeorgtype = payeeorgtype;
    }

    public Short getDealtype() {
        return dealtype;
    }

    public void setDealtype(Short dealtype) {
        this.dealtype = dealtype;
    }

    public Long getPayeefee() {
        return payeefee;
    }

    public void setPayeefee(Long payeefee) {
        this.payeefee = payeefee;
    }

    public Long getPayerfee() {
        return payerfee;
    }

    public void setPayerfee(Long payerfee) {
        this.payerfee = payerfee;
    }

    public BigDecimal getSynctoken() {
        return synctoken;
    }

    public void setSynctoken(BigDecimal synctoken) {
        this.synctoken = synctoken;
    }

    public Short getPayeeservicelevel() {
        return payeeservicelevel;
    }

    public void setPayeeservicelevel(Short payeeservicelevel) {
        this.payeeservicelevel = payeeservicelevel;
    }

    public Short getPayerservicelevel() {
        return payerservicelevel;
    }

    public void setPayerservicelevel(Short payerservicelevel) {
        this.payerservicelevel = payerservicelevel;
    }

    public String getPayercurrencycode() {
        return payercurrencycode;
    }

    public void setPayercurrencycode(String payercurrencycode) {
        this.payercurrencycode = payercurrencycode;
    }

    public Long getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(Long exchangerate) {
        this.exchangerate = exchangerate;
    }

    public String getPayeecurrencycode() {
        return payeecurrencycode;
    }

    public void setPayeecurrencycode(String payeecurrencycode) {
        this.payeecurrencycode = payeecurrencycode;
    }

    public Short getHascaculatedprice() {
        return hascaculatedprice;
    }

    public void setHascaculatedprice(Short hascaculatedprice) {
        this.hascaculatedprice = hascaculatedprice;
    }

    public Short getAsynaccounting() {
        return asynaccounting;
    }

    public void setAsynaccounting(Short asynaccounting) {
        this.asynaccounting = asynaccounting;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getPayeechannel() {
        return payeechannel;
    }

    public void setPayeechannel(String payeechannel) {
        this.payeechannel = payeechannel;
    }

    public String getPayerchannel() {
        return payerchannel;
    }

    public void setPayerchannel(String payerchannel) {
        this.payerchannel = payerchannel;
    }

    public Short getFeetype() {
        return feetype;
    }

    public void setFeetype(Short feetype) {
        this.feetype = feetype;
    }

    public BigDecimal getPayerfeeacctcode() {
        return payerfeeacctcode;
    }

    public void setPayerfeeacctcode(BigDecimal payerfeeacctcode) {
        this.payerfeeacctcode = payerfeeacctcode;
    }

    public BigDecimal getPayeefeeacctcode() {
        return payeefeeacctcode;
    }

    public void setPayeefeeacctcode(BigDecimal payeefeeacctcode) {
        this.payeefeeacctcode = payeefeeacctcode;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "dealid='" + dealid + '\'' +
                ", dealbegindate=" + dealbegindate +
                ", orderSeqId=" + orderSeqId +
                ", dealamount=" + dealamount +
                ", payeracctcode=" + payeracctcode +
                ", payeeacctcode=" + payeeacctcode +
                ", dealstatus=" + dealstatus +
                ", dealenddate=" + dealenddate +
                ", orgorderid='" + orgorderid + '\'' +
                ", dealcode=" + dealcode +
                ", paymentservicepkgcode='" + paymentservicepkgcode + '\'' +
                ", payerorgcode=" + payerorgcode +
                ", payeeorgcode=" + payeeorgcode +
                ", ordertime=" + ordertime +
                ", submitacctcode=" + submitacctcode +
                ", payeraccttype=" + payeraccttype +
                ", payerorgtype=" + payerorgtype +
                ", payeeaccttype=" + payeeaccttype +
                ", payeeorgtype=" + payeeorgtype +
                ", dealtype=" + dealtype +
                ", payeefee=" + payeefee +
                ", payerfee=" + payerfee +
                ", synctoken=" + synctoken +
                ", payeeservicelevel=" + payeeservicelevel +
                ", payerservicelevel=" + payerservicelevel +
                ", payercurrencycode='" + payercurrencycode + '\'' +
                ", exchangerate=" + exchangerate +
                ", payeecurrencycode='" + payeecurrencycode + '\'' +
                ", hascaculatedprice=" + hascaculatedprice +
                ", asynaccounting=" + asynaccounting +
                ", transactiondate=" + transactiondate +
                ", payeechannel='" + payeechannel + '\'' +
                ", payerchannel='" + payerchannel + '\'' +
                ", feetype=" + feetype +
                ", payerfeeacctcode=" + payerfeeacctcode +
                ", payeefeeacctcode=" + payeefeeacctcode +
                '}';
    }
}
