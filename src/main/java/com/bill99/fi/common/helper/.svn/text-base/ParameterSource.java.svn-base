package com.bill99.fi.common.helper;

/**
 * 请求参数拼接和加签顺序
 * 
 * @author kaiquan.jiang
 * 
 */
public class ParameterSource {

	
	/**
	 * gateway 加签及提交参数顺序 
	 */
	public static final String[] gatewayParameter = { "inputCharset",
			"pageUrl", "bgUrl", "version", "language", "signType",
			"merchantAcctId", "payerName", "payerContactType", "payerContact",
			"payerIdType", "payerId", "payerIP", "orderId", "orderAmount",
			"orderTime", "orderTimestamp", "productName", "productNum",
			"productId", "productDesc", "ext1", "ext2", "payType","pkgCode", "submitType","bankId",
			"cardIssuer", "cardNum", "redoFlag", "connId","pid", "extDataType",
			"extDataContent", "signMsg" };

	/**
	 * msgateway  加签及提交参数顺序(分账支付,修改,确认)
	 */
	public static final String[] msgatewayParameter = { "inputCharset",
			"pageUrl", "bgUrl", "version", "language", "signType",
			"payeeContactType", "payeeContact", "payerName","payerContactType",
			"payerContact", "displayContactType","displayContact",
			"payTolerance","payerIP", "orderId", "orderAmount","payeeAmount",
			"clearAmount", "orderTime","orderTimestamp","orderUpdateTime",
			"sharingTime","productName","productNum","productDesc", "ext1","ext2",
			"payType", "bankId", "pid","sharingData","sharingInfo", "sharingPayFlag", 
			"submitType", "autoIssueFlag","autoIssueData", "autoIssueReturnURL",
			"orderTimeOut", "signMsg" };

	/**
	 * msgatewayRefundModifyMsg  分账退款修改加签参数顺序
	 */
	public static final String[] msgatewayRefundModifyMsg = { "inputCharset",
			"version", "signType","orderId", "pid",	"seqId", "requestId",
			"requestTime","returnSharingDetail", "assignAcct", "signMsg" };
	
	/**
	 * msgatewayRefundModifyMsg  分账退款修改提交参数顺序
	 */
	public static final String[] msgatewayRefundModifySubmit = { "inputCharset",
		"version", "signType","orderId", "pid",	"seqId", "requestId",
		"requestTime","returnSharingDetail", "assignAcct", "key", "signMsg" };
		
	/**
	 * msgatewayRefundActionMsg  分账退款确认加签参数顺序
	 */
	public static final String[] msgatewayRefundActionMsg = { "inputCharset",
			"version", "signType", "orderId", "pid", "seqId",
			"sharingFlag", "sharingData", "returnTime","signMsg" };
	
	/**
	 * msgatewayRefundAction  分账退款确认提交参数顺序
	 */
	public static final String[] msgatewayRefundAction = { "inputCharset",
			"version", "signType", "orderId", "pid", "seqId",
			"sharingFlag", "sharingData", "returnTime","key","signMsg" };
	
	/**
	 * 航信网关  加签及提交参数顺序
	 * 
	 */
	public static final String[] hxgatewayParameter = { "inputCharset",
		"pageUrl", "bgUrl", "version", "language", "signType","payeeContactType",
		"payeeContact", "payerName","payerContactType", "payerContact",
		"displayContactType","displayContact", "payTolerance","payerIP", 
		"orderId", "orderAmount","payeeAmount", "orderTime","orderTimestamp", 
		"productName", "productNum","productDesc", "ext1", "ext2", "payType",
		"bankId", "pid","sharingFeeCalcType","sharingFeeType","hasPayeeFee",
		"sharingData", "sharingPayFlag", "submitType", "autoIssueFlag","autoIssueData",
		"autoIssueReturnURL","signMsg" };	
	
	/**
	 * 航信网关退款 加签及提交参数顺序
	 * 
	 */
	public static final String[] hxgatewayRefundParameter = { "inputCharset",
		"ref_version", "ref_language", "ref_signType","orderId","ref_pid", "ref_seqId","ref_returnAllAmount",
		"ref_returnTime","ext1","ext2","ref_returnDetail", "ref_signMsg"};
	
	/**
	 * ('signMsg','inputCharset=[inputCharset]&version=[version]&language=[language]&signType=[signType]&
	 * orderId=[orderId]&pid=[pid]&seqId=[seqId]&returnAllAmount=[returnAllAmount]&returnTime=[returnTime]&
	 * ext1=[ext1]&ext2=[ext2]&returnDetail=[returnDetail]&key=[merchant_key]')" value='刷新MD5'>
	 */
	/**
	 * 老网关加签顺序
	 */
	public static final String[] oldGatewayParameterMac = { 
		"merchant_id","orderid","amount", "merchant_url","merchant_key" };
	/**
	 * 老网关  提交参数顺序
	 */
	public static final String[] oldGatewayParameter = { "merchant_id",
			"commodity_info", "merchant_param", "pid", "pname", "pemail",
			"amount", "merchant_url", "merchant_key", "mac", "orderid",
			"currency", "isSupportDES" };

	/**
	 * 平台网关账户支付加签顺序
	 */
	public static final String[] platfromAccountAutopayParameterMsg = { 
		"inputCharset","pageUrl","bgUrl", "version","language",
		"signType","merchantAcctId","payerName","payerContactType","payerContact",
		"orderId","orderAmount","orderTime","productName","productNum",
		"productId","productDesc","ext1","ext2","payType","pkgCode","key" };
	
	/**
	 * 平台网关账户支付提交参数(notnull)
	 */
	public static final String[] platfromAccountAutopayParameter = { 
		"orderMsg","platformInputCharset","platformVersion","platformPayType","platformPayMsg",
		"platformId","platformSignType","platformSignMsg", "platformUrl"  };	

	/**
	 * 平台网关账户支付提交参数(全部)
	 */
	public static final String[] platfromAccountAutopayParameterMsgAll = { 
		"merchant_id","orderid","amount", "merchant_url","merchant_key" ,
		"bankId","bgUrl", "cardIssuer","cardNum" ,"connId","ext1",
		"ext2","extDataContent" ,"extDataType","inputCharset", "isOpenNewWindow",
		"language" ,"merchantAcctId","merchantKey", "orderAmount","orderId" ,
		"orderMsg","orderTime", "orderTimestamp","pageUrl" ,"payerContact","payerContactType",
		"payerId","payerIdType" ,"payerIP","payerName", "payType","pid","pkgCode","productDesc",
		"productId","productName" ,"productNum","redoFlag", "referData","referDataType","remitCode",
		"remitType","serverAddress","serverSubmitAddress","signMsg","signType","submit","submitType",
		"testServer","version" };
	
	/** 分账退款  加签及提交参数顺序
	 * 
	 */
	public static final String[] msgatewayRefundParameter = {
			"ref_inputCharset", "ref_version", "ref_signType",
			"ref_advanceFlag", "orderId", "ref_pid", "ref_seqId",
			"ref_returnAllAmount", "ref_getTolerance", "ref_returnTime",
			"ref_ext1", "ref_ext2", "ref_returnDetail",
			"ref_returnSharingDetail", "ref_assignAcct", "ref_processId",
			"ref_refundFlag", "ref_shareRefundFeeFlag", "ref_key" };

	
	/**
	 * 静态网关  加签及提交参数顺序
	 */
	public static final String[] staticGatewaySubmitParameter = {
		"convertOrderAmount", "isPayerFee", "merchantIdcontent", "merchantName", 
		"orderAmount", "orderCurrency", "pageUrl", "payeeMbrcode", "payerAddr",
		"payerEmail", "payerName", "payerTel", "paytype", "productName", "productNum",
		"remark","submitOk","websiteName"};
	
	/**
	 * 静态网关 应用提交顺序
	 */
	public static final String[] staticGatewayParameter = {
		"inputCharset", "pageUrl", "version", "language", "signType", 
		"merchantAcctId", "payerContactType", "orderId", "orderAmount",
		"orderTime", "productName",	"productNum", "productDesc", "payType","pkgCode","key" };	
	
	
	/**
	 * 网关自动支付 参数提交顺序
	 */
	public static final String[] autoPaySubmitParameter={
		"inputCharset","url","version","signType","memberCode",
		"payerName","payerContactType","payerContact","payChannel",
		"payType","payerNo","merchantAcctId","orderId","requestId","payAmount","payTime","signMsg"
	};

	
	/**
	 * RMB车险网关 platFormSignMessage 参数加签顺序
	 */
	public static final String[] rmbCarInsurancePlatSignParameter={
		"inputCharset","pageUrl","bgUrl","version","language","signType","merchantAcctId",
		"payerName","payerContactType","payerContact","payerIdType","payerId","orderId","orderAmount",
		"orderTime","productName","productNum","productId","productDesc","ext1","ext2",
		"payType","pkgCode","bankId","cardIssuer","cardNum","remitType","remitCode",
		"redoFlag","pid","submitType","platformId","platformUrl","platformSignType","key"
	}; 
 
	
	/**
	 * RMB车险网关 signMessage 参数加签顺序
	 */

	public static final String[] rmbCarInsuranceSignParameter={
		"inputCharset","pageUrl","bgUrl","version","language","signType","merchantAcctId",
		"payerName","payerContactType","payerContact","orderId","orderAmount",
		"orderTime","productName","productNum","productDesc","transCode","platformCode",
		"ext1","ext2","payType","key"	
	};
	/**
	 * RMB车险网关 signMessage 参数 提交 顺序
	 */
	public static final String[] rmbCarInsuranceSumbitParameter={
		 "bgUrl","ext1","ext2","fee","inputCharset","isOpenNewWindow","key","language",
		 "merchantAcctId","orderAmount","orderId","orderTime","pageUrl","payerContact","payerContactType","payerName",
		 "payType","platformCode","platformId","platformSignMsg","platformSignType","platformUrl","productDesc",
		 "productName","productNum","serverAddress","signMsg","signType","testServer","transCode","version"
	};
	
	/**
	 * PKI 车险网关 signMessage 参数 提交 顺序
	 */
	public static final String[] pkiCarInsuranceSumbitParameter={
		"inputCharset","pageUrl","bgUrl","version","language","signType","merchantAcctId",
		"payerContactType","orderId","orderAmount",
		"orderTime","productNum","transCode","platformCode",
		"ext1","ext2","payType"	
		};
	

	/**
	 * 移动网关  参数提交顺序
	 */
	public static final String[] mobileGatewaySubmitParameter={"inputCharset", "pageUrl", "bgUrl", "version",
		"language", "signType", "merchantAcctId", "payerName",
		"payerContactType", "payerContact", "payerIdType", "payerId",
		"payerIP", "orderId", "orderAmount", "orderTime",
		"orderTimestamp", "productName", "productNum", "productId",
		"productDesc", "ext1", "ext2", "payType","pkgCode", "bankId",
		"redoFlag", "pid" ,"orderTimeOut","mobileGateway", "signMsg"
	};



	/**
	 * 移动网关  参数提交顺序
	 */
	public static final String[] ataGatewayParameter={"mer_code","Billno","Amount","Date","Merchanturl","SignMD5"
		,"merchantKey","Currency_Type","Gateway_Type","Lang","FailUrl","ErrorUrl","Attach","DispAmount","OrderEncodeTyp"
		,"RetEncodeType","Rettype","ServerUrl","DoCredit","bankco","payType"
	};

	/**
	 * 海外险网关 signMessage 参数加签顺序
	 */

	public static final String[] hwGateWaySignParameter={
		"inputCharset","pageUrl","bgUrl","version","language","signType","merchantAcctId",
		"payerName","payerContactType","payerContact","payerIdentityCard","orderId",
		"orderCurrency","settlementCurrency","orderAmount",
		"orderTime","productName","productDesc","payType","key"	
	};
	
	/**
	 * 海外险网关提交顺序
	 */

	public static final String[] hwGateWaySubmitParameter={
		"bgUrl","inputCharset","language","merchantAcctId","merchantKey","orderAmount",
		"orderCurrency","orderId","orderTime","pageUrl","payerContact","payerContactType",
		"payerIdentityCard","payerName","payType","productDesc","productName","serverAddress",
		"settlementCurrency","signMsg","signType","testServer","version"
	};

	/**
	 * 分期网关提交顺序---静态加密
	 */

	public static final String[] InstallmentSubmitParameter={
		"inputCharset","pageUrl", "bgUrl", "version", "language", "signType",
		"merchantAcctId", "payerName", "payerContactType", "payerContact",
		"orderId", "orderAmount","orderTime","productName", "productNum",
		"productId", "productDesc", "ext1", "ext2","pkgCode", "payType","bankId",
		"period", "redoFlag", "pid", "signMsg"
	};
	/**
	 * 分期网关静态加密
	 */

	public static final String[] InstallmentSignParameter={
		"pageUrl","version", "signType","merchantAcctId","orderId","orderAmount",
		"orderTime","productName", "productNum", "payType","pkgCode","period","language", 
		 "payerContact","payerContactType","productDesc","payerName","inputCharset",
		 "signMsg"
	};
	/**
	 * 静态分期网关  加签及提交参数顺序
	 */
	public static final String[] staticInstallmentSubmitParameter = {
		"convertOrderAmount", "isPayerFee", "merchantIdcontent", "merchantName", 
		"orderAmount", "orderCurrency", "pageUrl", "payeeMbrcode", "payerAddr",
		"payerEmail", "payerName", "payerTel","payTimes", "paytype", "productName", "productNum",
		"remark","submitOk","websiteName"};



 /**
  * 合并网关网银支付MD5参数
*/

public static final String[] mergeNetBankMD5Parameter = {
	"inputCharset", "pageUrl", "bgUrl", "version", "language",
	"signType", "merchantAcctId", "payerName", "payerContactType",
	"payerContact", "payerIdType", "payerId", "payerIP", "orderId",
	"orderAmount", "bindCard","bindMobile", "orderTime", "orderTimestamp", "productName",
	"productNum", "productId","productDesc","ext1","ext2","payType","pkgCode",
	"bankId","cardIssuer","cardNum","remitType","remitCode","redoFlag",
	"pid","orderTimeOut","submitType","extDataType","extDataContent","subTradeInfo","key"};

/**
 * 合并网关网银支付MD5提交参数
*/

public static final String[] mergeNetBankMD5SubmitParameter = {
	"bankId","bgUrl","cardIssuer","cardNum","ext1","ext2","extDataContent","extDataType",
	"inputCharset","isOpenNewWindow","language",
	"merchantAcctId", "merchantKey", "orderAmount", "orderId", "orderTime",
	"orderTimeOut", "orderTimestamp", "pageUrl", "payerContact",
	"payerContactType", "payerId", "payerIdType", "payerIP", "payerName",
	"payType", "pid","productDesc", "productId", "productName", "productNum",
	"redoFlag", "referData","referData","referDataType","serverAddress","signType",
	"submit","subTradeInfo","testServer","version","signMsg"};
/**
 * 合并网关子订单MD5参数
*/

public static final String[] mergeNetBanksubMD5Parameter = {
	"inputCharset","contactType","contactInfo","mergeOrderId","applyAmount"
	,"notifyUrl","productNum","productName","ext1","ext2","signType","key"};



/**
 * 合并网关网银支付
*/
public static final String[] mergeNetBankReadySignMsgParameter = {
"inputCharset", "pageUrl", "bgUrl", "version", "language",
"signType", "merchantAcctId", "payerName", "payerContactType",
"payerContact", "orderId", "orderAmount", "orderTime", "productName",
"productNum", "productId","productDesc", "ext1", "ext2", "payType", "pkgCode", "bankId",
"pid","orderTimeOut","subTradeInfo","key"};

/**
* 合并网关网银支付PKI提交参数
*/
public static final String[] mergeNetBankPKISubmitParameter = {
"bankId", "bgUrl", "ext1", "ext2", "inputCharset",
"language", "merchantAcctId", "orderAmount", "orderId",
"orderTime", "orderTimeOut", "originalData", "pageUrl", "payerContact",
"payerContactType", "payerName","payType", "pid", "pkgCode", "productDesc",
"productId", "productName","productNum","signMsg","signType","subTradeInfo","version"};

}