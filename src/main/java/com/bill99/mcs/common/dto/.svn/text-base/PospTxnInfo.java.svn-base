package com.bill99.mcs.common.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Posp交易信息类
 */
public class PospTxnInfo {

	/**
	 * 汇率方向：报价扣款
	 */
	public static final String ROE_DIR_PRICING_BILLING = "0";

	/**
	 * 汇率方向：扣款报价
	 */
	public static final String ROE_DIR_BILLING_PRICING = "1";

	/**
	 * TPDU
	 */
	private byte[] tpdu;

	/**
	 * 报文版本号
	 */
	private byte[] version;

	/**
	 * 银行编号
	 */
	private String bankId;

	/**
	 * 商户编号
	 */
	private String merchantId;

	/**
	 * 商户对应的客户号
	 */
	private String merchantCid;

	/**
	 * 结算商户编号
	 */
	private String settleMerchantId;

	/**
	 * 终端编号
	 */
	private String terminalId;

	/**
	 * 交易类型
	 */
	private String txnType;

	/**
	 * 主账号
	 */
	private String PAN;

	/**
	 * 失效日期
	 */
	private String expiredDate;

	/**
	 * CVV2代码
	 */
	private String cvv2;

	/**
	 * 卡类型（之前getCardInfo返回的）
	 */
	private String cardType;

	/**
	 * 卡名称
	 */
	private String cardName;

	/**
	 * 卡组织
	 */
	private String cardOrg;

	/**
	 * 境外卡组织
	 */
	private String foreignCardOrg;

	/**
	 * 受理机构编号
	 */
	private String instId;

	/**
	 * 发送机构编号
	 */
	private String fwdInstId;

	/**
	 * 发卡机构编号
	 */
	private String issuerId;

	/**
	 * 应用类型（用于储值卡）
	 */
	private String appType;

	/**
	 * 交易币别
	 */
	private String cur;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 交易金额
	 */
	private BigDecimal amt;

	/**
	 * 国际网络标识域
	 */
	private String NII;

	/**
	 * 授权方参考编号
	 */
	private String authRef;

	/**
	 * 消费积分
	 */
	private Long rewardPoints;
	/**
	 * 余额
	 */
	private BigDecimal balance;

	/**
	 * 交易后积分余额
	 */
	private BigDecimal postRewardPoints;

	/**
	 * 分期消费月期限
	 */
	private Integer termInMonths;

	/**
	 * 产品编号
	 */
	private String productId;

	/**
	 * 转换币别（用于DCC）
	 */
	private String convCur;

	/**
	 * 二磁道
	 */
	private String track2;

	/**
	 * 三磁道
	 */
	private String track3;

	/**
	 * PIN
	 */
	private String PIN;

	/**
	 * 输入模式
	 */
	private String serviceEntryMode;

	/**
	 * 服务点条件代码
	 */
	private String serviceConditionCode;

	/**
	 * 授权号（用于预授权确认）
	 */
	private String authCode;

	/**
	 * 系统检索参考号（用于退货和撤销）
	 */
	private String RRN;

	/**
	 * 原系统检索参考号
	 */
	private String originalRRN;

	/**
	 * 终端跟踪流水号
	 */
	private String termTraceNo;

	/**
	 * 终端批次号
	 */
	private String termBatchNo;

	/**
	 * 终端票据号
	 */
	private String termInvoiceNo;

	/**
	 * 终端操作员编号
	 */
	private String terminalOperId;

	/**
	 * 外部交易跟踪编号
	 */
	private String externalTraceNo;

	/**
	 * 服务渠道跟踪编号
	 */
	private String serviceChannelTraceNo;

	/**
	 * 外部客户号
	 */
	private String externalCustomerId;

	/**
	 * 终端交易时间
	 */
	private java.util.Date termTxnTime;

	/**
	 * 客户端IP
	 */
	private String clientIp;

	/**
	 * 发生地点类型
	 */
	private String siteType;

	/**
	 * 发生地点编号
	 */
	private String siteId;

	/**
	 * 授权方交易应答码
	 */
	private String authResponseCode;

	/**
	 * 授权商户编号
	 */
	private String authMerchantId;

	/**
	 * 授权终端编号
	 */
	private String authTerminalId;

	/**
	 * 授权过期时间
	 */
	private Date authExpiredTime;

	/**
	 * 订单报价币别
	 */
	private String pricingCurrency;

	/**
	 * 订单报价金额
	 */
	private BigDecimal pricingAmount;

	/**
	 * 订单扣款币别
	 */
	private String billingCurrency;

	/**
	 * 订单扣款金额
	 */
	private BigDecimal billingAmount;

	/**
	 * 汇率提供标志
	 */
	private String exchangeRateFlag;

	/**
	 * 汇率方向 0 报价/扣款 1 扣款/报价
	 */
	private String exchangeRateDirection;

	/**
	 * 汇率
	 */
	private BigDecimal exchangeRate;

	/**
	 * 原交易信息
	 */
	private String attachInfos;
	/**
	 * 附加信息
	 */
	private String reserved;

	/**
	 * 应用错误码
	 */
	private String appExcCode;

	/**
	 * 应答码
	 */
	private String responseCode;

	/**
	 * 应答消息
	 */
	private String responseMessage;

	/**
	 * 交易编号
	 */
	private Long idTxn;

	/**
	 * 银行中文名称
	 */
	private String issuerCNName;

	/**
	 * MAS交易时间
	 */
	private Date txnTime;
	/**
	 * 终端密钥
	 * 
	 * @return
	 */
	private String tmk;
	/**
	 * tais整合渠道类型
	 */
	private String integrationChannelType;
	/**
	 * 余额或附加金额
	 */
	private String additionalAmounts;
	/**
	 * 47域
	 */
	private String field47;
	/**
	 * 48域
	 */
	private String field48;
	/**
	 * 58域值
	 */
	private String field58;

	/**
	 * 56域值
	 */
	private String field56;

	/**
	 * 商户机构编号
	 */
	private String orgPartyId;

	/**
	 * OQS查询地址
	 */
	private String oqsQueryUrl;

	/**
	 * OQS查询类型
	 */
	private String oqsQueryType;
	/**
	 * 原交易日期
	 */
	private String origTxnDay;
	/**
	 * 原交易的信息类型代码
	 */
	private String orig_msg_type;

	public String getOrig_msg_type() {
		return orig_msg_type;
	}

	public void setOrig_msg_type(String orig_msg_type) {
		this.orig_msg_type = orig_msg_type;
	}

	public String getOrigTxnDay() {
		return origTxnDay;
	}

	public void setOrigTxnDay(String origTxnDay) {
		this.origTxnDay = origTxnDay;
	}

	public String getField56() {
		return field56;
	}

	public void setField56(String field56) {
		this.field56 = field56;
	}

	public String getField47() {
		return field47;
	}

	public void setField47(String field47) {
		this.field47 = field47;
	}

	public String getField58() {
		return field58;
	}

	public void setField58(String field58) {
		this.field58 = field58;
	}

	public String getField48() {
		return field48;
	}

	public void setField48(String field48) {
		this.field48 = field48;
	}

	public String getAdditionalAmounts() {
		return additionalAmounts;
	}

	public void setAdditionalAmounts(String additionalAmounts) {
		this.additionalAmounts = additionalAmounts;
	}

	public String getIntegrationChannelType() {
		return integrationChannelType;
	}

	public void setIntegrationChannelType(String integrationChannelType) {
		this.integrationChannelType = integrationChannelType;
	}

	public String getTmk() {
		return tmk;
	}

	public void setTmk(String tmk) {
		this.tmk = tmk;
	}

	public byte[] getTpdu() {
		return tpdu;
	}

	public void setTpdu(byte[] tpdu) {
		this.tpdu = tpdu;
	}

	public byte[] getVersion() {
		return version;
	}

	public void setVersion(byte[] version) {
		this.version = version;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amount) {
		this.amt = amount;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expireDate) {
		this.expiredDate = expireDate;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pan) {
		PAN = pan;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pin) {
		PIN = pin;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getTrack3() {
		return track3;
	}

	public void setTrack3(String track3) {
		this.track3 = track3;
	}

	public String getServiceEntryMode() {
		return serviceEntryMode;
	}

	public void setServiceEntryMode(String serviceEntryMode) {
		this.serviceEntryMode = serviceEntryMode;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getConvCur() {
		return convCur;
	}

	public void setConvCur(String convCur) {
		this.convCur = convCur;
	}

	public String getRRN() {
		return RRN;
	}

	public Date getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(Date txnTime) {
		this.txnTime = txnTime;
	}

	public void setRRN(String rrn) {
		this.RRN = rrn;
	}

	public static String getRoeDirPricingBilling() {
		return ROE_DIR_PRICING_BILLING;
	}

	public static String getRoeDirBillingPricing() {
		return ROE_DIR_BILLING_PRICING;
	}

	public String getExternalTraceNo() {
		return externalTraceNo;
	}

	public void setExternalTraceNo(String externalTraceNo) {
		this.externalTraceNo = externalTraceNo;
	}

	public String getExternalCustomerId() {
		return externalCustomerId;
	}

	public void setExternalCustomerId(String customerId) {
		this.externalCustomerId = customerId;
	}

	public String getTermTraceNo() {
		return termTraceNo;
	}

	public void setTermTraceNo(String temTraceNo) {
		this.termTraceNo = temTraceNo;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getSettleMerchantId() {
		return settleMerchantId;
	}

	public void setSettleMerchantId(String settleMerchantId) {
		this.settleMerchantId = settleMerchantId;
	}

	public java.util.Date getTermTxnTime() {
		return termTxnTime;
	}

	public void setTermTxnTime(java.util.Date termTxnTime) {
		this.termTxnTime = termTxnTime;
	}

	public String getOriginalRRN() {
		return originalRRN;
	}

	public void setOriginalRRN(String originalRRN) {
		this.originalRRN = originalRRN;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getTermBatchNo() {
		return termBatchNo;
	}

	public void setTermBatchNo(String termBatchNo) {
		this.termBatchNo = termBatchNo;
	}

	public String getTermInvoiceNo() {
		return termInvoiceNo;
	}

	public void setTermInvoiceNo(String termInvoiceNo) {
		this.termInvoiceNo = termInvoiceNo;
	}

	public String getTerminalOperId() {
		return terminalOperId;
	}

	public void setTerminalOperId(String terminalOperId) {
		this.terminalOperId = terminalOperId;
	}

	public Long getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public Integer getTermInMonths() {
		return termInMonths;
	}

	public void setTermInMonths(Integer termInMonths) {
		this.termInMonths = termInMonths;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMerchantCid() {
		return merchantCid;
	}

	public String getServiceChannelTraceNo() {
		return serviceChannelTraceNo;
	}

	public void setMerchantCid(String merchantCid) {
		this.merchantCid = merchantCid;
	}

	public void setServiceChannelTraceNo(String serviceChannelTraceNo) {
		this.serviceChannelTraceNo = serviceChannelTraceNo;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getNII() {
		return NII;
	}

	public void setNII(String nii) {
		NII = nii;
	}

	public String getServiceConditionCode() {
		return serviceConditionCode;
	}

	public void setServiceConditionCode(String serviceConditionCode) {
		this.serviceConditionCode = serviceConditionCode;
	}

	public String getAuthRef() {
		return authRef;
	}

	public void setAuthRef(String authRef) {
		this.authRef = authRef;
	}

	public String getAuthResponseCode() {
		return authResponseCode;
	}

	public void setAuthResponseCode(String authResponseCode) {
		this.authResponseCode = authResponseCode;
	}

	public String getAuthMerchantId() {
		return authMerchantId;
	}

	public void setAuthMerchantId(String authMerchantId) {
		this.authMerchantId = authMerchantId;
	}

	public String getAuthTerminalId() {
		return authTerminalId;
	}

	public void setAuthTerminalId(String authTerminalId) {
		this.authTerminalId = authTerminalId;
	}

	public String getAppExcCode() {
		return appExcCode;
	}

	public void setAppExcCode(String appExcCode) {
		this.appExcCode = appExcCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Date getAuthExpiredTime() {
		return authExpiredTime;
	}

	public void setAuthExpiredTime(Date authExpiredTime) {
		this.authExpiredTime = authExpiredTime;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(String cardOrg) {
		this.cardOrg = cardOrg;
	}

	public String getForeignCardOrg() {
		return foreignCardOrg;
	}

	public void setForeignCardOrg(String foreignCardOrg) {
		this.foreignCardOrg = foreignCardOrg;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public Long getIdTxn() {
		return idTxn;
	}

	public void setIdTxn(Long idTxn) {
		this.idTxn = idTxn;
	}

	public String getIssuerCNName() {
		return issuerCNName;
	}

	public void setIssuerCNName(String issuerCNName) {
		this.issuerCNName = issuerCNName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getPostRewardPoints() {
		return postRewardPoints;
	}

	public void setPostRewardPoints(BigDecimal postRewardPoints) {
		this.postRewardPoints = postRewardPoints;
	}

	public String getPricingCurrency() {
		return pricingCurrency;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}

	public BigDecimal getPricingAmount() {
		return pricingAmount;
	}

	public void setPricingAmount(BigDecimal pricingAmount) {
		this.pricingAmount = pricingAmount;
	}

	public String getBillingCurrency() {
		return billingCurrency;
	}

	public void setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
	}

	public BigDecimal getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(BigDecimal billingAmount) {
		this.billingAmount = billingAmount;
	}

	public String getExchangeRateFlag() {
		return exchangeRateFlag;
	}

	public void setExchangeRateFlag(String exchangeRateFlag) {
		this.exchangeRateFlag = exchangeRateFlag;
	}

	public String getExchangeRateDirection() {
		return exchangeRateDirection;
	}

	public void setExchangeRateDirection(String exchangeRateDirection) {
		this.exchangeRateDirection = exchangeRateDirection;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getAttachInfos() {
		return attachInfos;
	}

	public void setAttachInfos(String attachInfos) {
		this.attachInfos = attachInfos;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getFwdInstId() {
		return fwdInstId;
	}

	public void setFwdInstId(String fwdInstId) {
		this.fwdInstId = fwdInstId;
	}

	public String getOrgPartyId() {
		return orgPartyId;
	}

	public void setOrgPartyId(String orgPartyId) {
		this.orgPartyId = orgPartyId;
	}

	public String getOqsQueryUrl() {
		return oqsQueryUrl;
	}

	public void setOqsQueryUrl(String oqsQueryUrl) {
		this.oqsQueryUrl = oqsQueryUrl;
	}

	public String getOqsQueryType() {
		return oqsQueryType;
	}

	public void setOqsQueryType(String oqsQueryType) {
		this.oqsQueryType = oqsQueryType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
