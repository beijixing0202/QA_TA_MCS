package com.bill99.fi.service.payments.impl;

import java.util.Date;
import java.util.Map;

import org.testng.Reporter;

import shelper.iffixture.HttpFixture;
import steel.util.StringUtil;

import com.bill99.fi.common.helper.ParametersInitialization;
import com.bill99.fi.common.utils.CheckResponse;
import com.bill99.fi.common.utils.ChinaUnicomUtil;
import com.bill99.fi.common.utils.MD5Util;
import com.bill99.fi.orm.mng.GatewayDbCheck;
import com.bill99.fi.service.payments.PaymentController;

/**
 * @author kaiquan.jiang
 *
 */
/**
 * @author kaiquan.jiang
 *
 */
public class PaymentControllerImpl implements PaymentController {

	private GatewayDbCheck gatewayDbCheck;
	private ParametersInitialization parametersInitialization;
	
	/**
	 *模拟银行支付
	 */	
	  		
	public boolean payDemoBankOnce(HttpFixture query, Map<String, String> data) {
		String bankOrderNo = gatewayDbCheck.getSequenceidByOrderid(data).getOrgorderid();
		String bankMerchantId = "105110089991048"; // 105110089991048模拟银行固定的merchantid
		String privateKey = "123456789abcdefg";
		String sign = MD5Util.MD5("merchantId=" + bankMerchantId + "&orderNo=" + bankOrderNo + "&payMoney=" + Float.valueOf(data.get("orderAmount")) / 100
				+ "&result=1&privateKey=" + privateKey + "", "1");
		query.nextRequest();
		/* System.out.println("http://192.168.8.14/bankgateway/bankgateway/card/demobank.htm?merchantId="
		 + bankMerchantId + "&orderNo=" + bankOrderNo
		+ "&payMoney=" + Float.valueOf(data.get("orderAmount")) / 100 +
		 "&result=1&sign=" + sign + "");*/
		query.setUrl("https://www.99bill.com/bankgateway/bankgateway/card/demobank.htm?merchantId=" + bankMerchantId + "&orderNo=" + bankOrderNo + "&payMoney="
				+ Float.valueOf(data.get("orderAmount")) / 100 + "&result=1&sign=" + sign + "");

		query.Get();
		System.err.println("GETRES"+query.getResponseBody());
		if (query.getResponseBody().contains("<div class=\"unsuccess\">")) {
			Reporter.log(query.saveParamLeftstrRightstr("unsuccess\">", "</div>").replace("\r\n", "").replace("	", ""), false);
		}
		String notifyUrl = query.getResponseBody().split("url='")[1].split("'\">")[0];
		query.nextRequest();
		query.setUrl(notifyUrl);
		// 直连时，需要重定向  
		if (null!=data.get("payType")&&data.get("payType").equals("10") && null != data.get("bankId")) {
			query.Get();
			String redirect = query.getResponseBody().split("url='")[1].split("'\">")[0];
			query.nextRequest();
			query.setUrl(redirect);
		}
		query.Get(); 
		//联通网关支付
				if(null!=data.get("PartnerID")&&"99BILLUNICOM".equals(data.get("PartnerID"))){
					String unNotifyUrl = query.getResponseBody().split("url='")[1].split("'\">")[0];
					query.nextRequest();
					query.setUrl(unNotifyUrl);
					query.Get();
					
					
//					System.out.println(query.getResponseBody());
					return ChinaUnicomUtil.decryptSign(query.saveParamLeftstrRightstr("PayRepObj\" value=\"", "\"/>"), data.get("key")).contains("PayResult=0");
					
					
				}else{
					 
					String s = query.getResponseBody().replace("\r\n", "").replace("	", "");
					return s.contains("<td width=30%>10</td>") || s.contains("bgcolor=\"#F9F9F9\">Y  </td>") || s.contains(" class=\"success\">您的交易支付成功</div>")||s.contains("<td width=30%>Y</td>")||s.contains("<result>1</result>");//----FI自动化脚本屏蔽
					//return s.contains("<td width=30%>10</td>") || s.contains("bgcolor=\"#F9F9F9\">Y  </td>") || s.contains("您的交易支付成功")||s.contains("<td width=30%>Y</td>");
					
				}

	}
	
	/**
	 *账户支付
	 */	
	  
	/* (non-Javadoc)
	 * @see com.bill99.fi.service.payments.PaymentController#payAccount(shelper.iffixture.HttpFixture, java.util.Map)
	 */
	public boolean payAccount(HttpFixture query, Map<String, String> data) {
		//Date date = new Date();
		//获取验证码
		query.nextRequest();
		query.setUrl("https://www.99bill.com/gateway/validatecode/validatecode.htm?data="+System.currentTimeMillis());
		query.Get();
		//query.getStatus();
		
//		输入账户名和验证码
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("https://www.99bill.com/gateway/accountPay.htm");
		query.addRequestBody("memberId=" + data.get("selectMemberId") + "&checkCode=" + data.get("selectCheckCode") + "&method=authMember");
		query.Post();
		
//		输入密码
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("https://www.99bill.com/gateway/accountPay.htm");
		query.addRequestBody("greeting=" + data.get("selectGreeting") + "&memberId=" + data.get("selectMemberId") +
				"&method=authPassword&password="+data.get("selectPassword"));
		query.Post();
//		获取验证码
		query.nextRequest();
		query.setUrl("https://www.99bill.com/gateway/validatecode/validatecode.htm?data="+System.currentTimeMillis());
		query.Get();
		
		//获取账户余额
		query.nextRequest();
		query.setUrl("https://www.99bill.com/gateway/accountPay.htm?method=getAccountBalance&accountCode="+ data.get("selectAccountCode"));
		query.Get();
		
		
       //支付
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("https://www.99bill.com/gateway/accountPay.htm");
		query.addRequestBody("accountCode=" + data.get("selectAccountCode") + "&checkCode=" + data.get("selectCheckCode") + "&memberId="
				+ data.get("selectMemberId") + "&method=authAcctPassword");
		query.Post();
//		TO-DO: 检查返回值
		System.out.println("+++=++++++"+query.getResponseBody());
		
		query.setUrl("https://www.99bill.com"+query.getResponseBody().split("href='")[1].split("'")[0]);
		query.Get();
		//System.out.println("+++=++++++"+query.getResponseBody());
		//String notifyUrl= query.saveParamLeftstrRightstr("<td>", "</td>");
		boolean accresult=query.getResponseBody().contains("支付成功");
		if(!accresult){
		Reporter.log("=====支付不成功======");
		}
		//联通网关支付
		if(null!=data.get("PartnerID")&&"99BILLUNICOM".equals(data.get("PartnerID"))){
			//System.out.println("]]]]]]]]]"+query.getResponseBody());
			String unNotifyUrl =query.saveParamLeftstrRightstr("onclick=\"window.open('", "')");
			//String unNotifyUrl = query.getResponseBody().split("onclick=\"window.open('")[1].split("')")[0];
			query.nextRequest();
			query.setUrl(unNotifyUrl);
			query.Get();
			//System.out.println(query.getResponseBody());
			String unnNotifyUrl = query.getResponseBody().split("url='")[1].split("'\">")[0];
			query.nextRequest();
			query.setUrl(unnNotifyUrl);
			query.Get();
        	//System.out.println(query.getResponseBody());
			return ChinaUnicomUtil.decryptSign(query.saveParamLeftstrRightstr("PayRepObj\" value=\"", "\"/>"), data.get("key")).contains("PayResult=0");		
		}else{		
		//通知商户
	    String result=NoticeTrader(query,data);
	    //return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");
	    return accresult;	
	}
	}
	
	/**
	 *分账网关账户支付
	 */	
	  
	/* (non-Javadoc)
	 * @see com.bill99.fi.service.payments.PaymentController#payAccount(shelper.iffixture.HttpFixture, java.util.Map)
	 */
	public boolean msgpayAccount(HttpFixture query, Map<String, String> data) {
		//获取验证码
		query.nextRequest();
		query.setUrl("http://www.99bill.com/msgateway/validatecode/validatecode.htm?data="+System.currentTimeMillis());
		query.Get();
		//query.getStatus();
		
//		输入账户名和验证码
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/msgateway/accountPay.htm");
		query.addRequestBody("memberId=" + data.get("selectMemberId") + "&checkCode=" + data.get("selectCheckCode") + "&method=authMember");
		query.Post();
		
//		输入密码
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/msgateway/accountPay.htm");
		query.addRequestBody("greeting=" + data.get("selectGreeting") + "&memberId=" + data.get("selectMemberId") +
				"&method=authPassword&password="+data.get("selectPassword"));
		query.Post();
		
//		获取验证码
		query.nextRequest();
		query.setUrl("http://www.99bill.com/msgateway/validatecode/validatecode.htm?data="+System.currentTimeMillis());
		query.Get();
		//System.out.println(query.getResponseBody());
		
		//获取账户余额
		query.nextRequest();
		query.setUrl("http://www.99bill.com/msgateway/accountPay.htm?method=getAccountBalance&accountCode="+ data.get("selectAccountCode"));
		query.Get();
		
		
       //支付
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/msgateway/accountPay.htm");
		query.addRequestBody("accountCode=" + data.get("selectAccountCode") + "&checkCode=" + data.get("selectCheckCode") + "&memberId="
				+ data.get("selectMemberId") + "&method=authAcctPassword");
		query.Post();
		
		//System.err.println(query.getResponseBody());
//		TO-DO: 检查返回值
		
		query.setUrl("http://www.99bill.com"+query.getResponseBody().split("href='")[1].split("'")[0]);
		query.Get();
		//System.out.println("+++=++++++"+query.getResponseBody());
		//String notifyUrl= query.saveParamLeftstrRightstr("<td>", "</td>");
		boolean accresult=query.getResponseBody().contains("支付成功");
		if(!accresult){
		  Reporter.log("=====支付不成功======");
		}
		
		//通知商户
	    String result=NoticeTrader(query,data);
	    //return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");
	    return accresult;	
	}
	
	
	/**
	 *ATA网关账户支付
	 */	
	  
	/* (non-Javadoc)
	 * @see com.bill99.fi.service.payments.PaymentController#payAccount(shelper.iffixture.HttpFixture, java.util.Map)
	 */
	public boolean ATApayAccount(HttpFixture query, Map<String, String> data) {
		//获取验证码
		query.nextRequest();
		query.setUrl("http://www.99bill.com/gateway/validatecode/validatecode.htm?");
		query.Get();
		//query.getStatus();
	  // System.err.println(query.getResponseBody());
		
//		输入账户名和验证码
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/accountPay.htm");
		query.addRequestBody("checkCode=" + data.get("selectCheckCode")+"&memberId=" + data.get("selectMemberId")+ "&method=authMember");
		query.Post();
		//System.err.println(query.getResponseBody());
		
/*	 // 账户登录
		query.nextRequest();
		query.setUrl("http://www.99bill.com/gateway/accountPay.htm?method=memberCertList&memberId=gr001@qa.99bill.com&userId=admin");
		query.Get();
		System.out.println(query.getResponseBody());*/
		
//		输入密码
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/accountPay.htm");
		query.addRequestBody("greeting=" + data.get("selectGreeting") + "&memberId=" + data.get("selectMemberId") +
				"&method=authPassword&password="+data.get("selectPassword"));
		query.Post();
		
		//获取账户余额
		query.nextRequest();
		query.setUrl("https://www.99bill.com/gateway/accountPay.htm?method=getAccountBalance&accountCode="+ data.get("selectAccountCode"));
		query.Get();
		//System.out.println(query.getResponseBody());
		
		//获取验证码
		query.nextRequest();
		query.setUrl("http://www.99bill.com/gateway/validatecode/validatecode.htm?data="+System.currentTimeMillis());
		query.Get();
		//query.getStatus();
	   //System.out.println(query.getResponseBody());
	   
		//获取账户余额
		query.nextRequest();
		query.setUrl("https://www.99bill.com/gateway/accountPay.htm?method=getAccountBalance&accountCode="+ data.get("selectAccountCode"));
		query.Get();
		//System.out.println(query.getResponseBody());
		
//		支付
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/accountPay.htm");
		query.addRequestBody("accountCode=" + data.get("selectAccountCode") + "&checkCode=" + data.get("selectCheckCode") + "&memberId="
				+ data.get("selectMemberId") + "&method=authAcctPassword");
		query.Post();
		
	
//		TO-DO: 检查返回值
		query.setUrl("http://www.99bill.com"+query.getResponseBody().split("href='")[1].split("'")[0]);
		query.Get();
		//System.out.println("+++=++++++"+query.getResponseBody());
		//String notifyUrl= query.saveParamLeftstrRightstr("<td>", "</td>");
		boolean accresult=query.getResponseBody().contains("支付成功");
		if(!accresult){
		Reporter.log("=====支付不成功======");
		}
		return accresult;	

	}
	/**
	 *线下支付 银行柜台：paytype=1；邮局：paytype=2
	 */	
	  
	public boolean payOffline(HttpFixture query, Map<String, String> data){
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.addHeaderValue("Referer", "https://www.99bill.com/gateway/recvMerchantInfoAction.htm");
		System.out.println("requestHeader"+query.getHeaderValue("Referer"));
		query.setUrl("http://www.99bill.com/gateway/offlinePay.htm");
		query.addRequestBody("method=" + data.get("selectMethod") + "&offlineEmail=" + data.get("selectOfflineEmail") 
				+ "&payType="+ data.get("selectpayType") + "&selectedCoupon=" + data.get("selectedCoupon")
				+ "&selectedLoyalCardNumber="+ data.get("selectedLoyalCardNumber")+"&");
		query.Post();
		System.err.println("param"+"method=" + data.get("selectMethod") + "&offlineEmail=" + data.get("selectOfflineEmail") 
				+ "&payType="+ data.get("selectpayType") + "&selectedCoupon=" + data.get("selectedCoupon")
				+ "&selectedLoyalCardNumber="+ data.get("selectedLoyalCardNumber"));
		System.out.println("res"+query.getResponseBody());
		String s=query.getResponseBody().replace("\r\n", "").replace("	", "");
		String getmessage = gatewayDbCheck.getSmsNotification(data);
		String message = "线下支付申请成功";
		System.out.println("dealId"+s.contains("class=\"waitsuccess\">线下支付申请提交成功！"));
		if( getmessage.contains(message) && s.contains("class=\"waitsuccess\">线下支付申请提交成功！")){
			return true;
		}
		else
		{
			return false;
		}
			
	}
	/**
	 *预付费卡支付
	 */	
	   
	public boolean payPrepaidCard(HttpFixture query, Map<String, String> data){
		//获取验证码
		query.nextRequest();
		query.setUrl("https://www.99bill.com/gateway/validatecode/validatecode.htm?data="+System.currentTimeMillis());
		query.Get();
	
		//输入卡号密码等
		query.nextRequest();
		query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		query.setUrl("https://www.99bill.com/gateway/prepayCardPay.htm?method=selectPrepay");
		
		query.addRequestBody("cardIssuer=" + data.get("cardIssuer") + "&checkCode=" + data.get("checkCode") 
				+ "&email="+ data.get("email") + "&firstIssuerName=" + data.get("firstIssuerName")
				+ "&method="+ data.get("method")+ "&pan="+ data.get("pan")+ "&password="
				+ data.get("password")+ "&prepayContactStatus="+ data.get("prepayContactStatus"));
		query.Post();
		System.out.println("==============+++++++++++++"+query.getResponseBody());
		//通知商户
	    String result=NoticeTrader(query,data);
	    System.err.println(result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>"));
	    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");
	
		
	}    
	/**
	 *CNP支付 
	 */	
	   
	public boolean payCNP(HttpFixture query, Map<String, String> data) {
		//确认支付
	    query.nextRequest();
	    query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
	    query.setUrl("http://www.99bill.com/gateway/creditCardPay.htm");
	    query.addRequestBody("method=selectBank&cvv2="+data.get("cvv2")+"&date=2014-9-24 8:50:01&idType="+data.get("idType")
	    		+"&pan="+data.get("pan")+"&pan=PUR&selectedbankId="+data.get("selectBankId"));
	    query.Post();
	    //System.out.println("1+++=++++++"+query.getResponseBody());
	    
	    //通知商户
	    String result=NoticeTrader(query,data);
	    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");

	}
	/**
	 *分账网关CNP支付 
	 */	
	    public boolean payMsGatewayCNP(HttpFixture query, Map<String, String> data) {
	    	//确认支付
		    query.nextRequest();
		    query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		    query.setUrl("http://www.99bill.com/msgateway/creditCardPay.htm");
//		    query.addRequestBody("method=selectBank&cardHolderId="+data.get("cardHolderId")
//		    		+"&cardHolderName="+data.get("cardHolderName")+"&cvv2="+data.get("cvv2")
//		    		+"&date=2017-01-18 13:30:15&expiredMonth="+data.get("expiredMonth")+
//		    		"&expiredYear="+data.get("expiredYear")+"&idType"+data.get("idType")+
//		    		"&pan="+data.get("pan")+"&pan=PUR");
//		    query.addRequestBody("method=selectBank&cardHolderId="+data.get("cardHolderId")
//		    		+"&cardHolderName="+data.get("cardHolderName")+"&cvv2="+data.get("cvv2")
//		    		+"&date&expiredMonth="+data.get("expiredMonth")+
//		    		"&expiredYear="+data.get("expiredYear")+"&idType"+data.get("idType")+
//		    		"&pan="+data.get("pan")+"&pan=PUR");
		    query.addRequestBody("method=selectBank&action2=&cardHolderId="+data.get("cardHolderId")
		    		+"&cardHolderName="+data.get("cardHolderName")+"&cvv2="+data.get("cvv2")
		    		+"&date="+System.currentTimeMillis()+"&expiredMonth="+data.get("expiredMonth")+
		    		"&expiredYear="+data.get("expiredYear")+"&idType"+data.get("idType")+
		    		"&pan="+data.get("pan")+"&pan=PUR&password=&rad=");
		    query.Post();
		    //通知商户
		    String result=NoticeTrader(query,data);
		    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");

	}
	
	    /**
		 *分账网关快捷支付 
		 */	
		    public boolean payMsGatewayClickPay(HttpFixture query, Map<String, String> data) {
		    	//确认支付
			    query.nextRequest();
			    query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
			    query.setUrl("https://www.99bill.com/msgateway/creditCardPay.htm?method=selectBank");
			    query.addRequestBody("cardHolderName="+data.get("cardHolderName")+"&idType="+data.get("idType")
			    		+"&cardHolderId="+data.get("cardHolderId")+"&pan="+data.get("pan")
			    		+"&pan=PUR&expiredMonth="+data.get("expiredMonth")+"&expiredYear="+data.get("expiredYear")
			    		+"&cvv2="+data.get("cvv2")+"&password=&action2=&rad=&");
			    System.out.println("request_____________________++++++++++++++++"+"cardHolderName="+data.get("cardHolderName")+"&idType="+data.get("idType")
			    		+"&cardHolderId="+data.get("cardHolderId")+"&pan="+data.get("pan")
			    		+"&pan=PUR&expiredMonth="+data.get("expiredMonth")+"&expiredYear="+data.get("expiredYear")
			    		+"&cvv2="+data.get("cvv2")+"&password=&action2=&rad=&");
			    query.Post();
			    System.out.println("redirect++++++++++++++++"+query.getResponseBody());
			    //通知商户
			    String result=NoticeTrader(query,data);
			    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");

		}
	    /**
		 *网关3.0快捷支付 
		 */	
		public  boolean gateway3ClickPay(HttpFixture query, Map<String, String> data) {
			// 选择银行
			query.addHeaderValue("Content-Type",
					"application/x-www-form-urlencoded");
			query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectBank");
			query.addRequestBody("&bankId=" + data.get("selectBankId"));
			query.Post();
			
			//点击获取手机验证码
			query.nextRequest();
			String url="https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=postDynaCode&quickPay=N&bankId=BOC&quickPan="+
			data.get("storablePan")+"&quickcvv2="+data.get("cvv2")+"&quickcardHoldName=test&quickcardHolderId="+data.get("quickcardHolderId")
			+"&quickphoneNo="+data.get("tel")+"&quickexpiredDate=0520&quickHoldIdType=0&savePci=1&rad=&instalmentTimes=";
			query.setUrl(url);
			query.Get();
			
			//查询验证码
			String code=gatewayDbCheck.getMobileValidateCode(data);
			System.out.println("CODE______________"+code);
			
			//输入有效期 验证码信息
			query.nextRequest();
			query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
			if(data.get("PayTime").equals("1")){
				query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectCreditCardPay&bankId=BOC&quickOrCnp=1");
			}else{
				query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectCreditCardQuickPay&bankId=BOC&quickOrCnp=1");
			}   
		    query.addRequestBody("&bankId="+data.get("selectBankId")+"&creditCardQuickPayValidateCode="+code+"&instalmentTimes=&quickcardHolderId="+data.get("quickcardHolderId")+"&quickcardHoldName="
		    +data.get("quickcardHoldName")+"&quickcvv2="+data.get("cvv2")+"&quickexpiredMonth="+data.get("quickexpiredMonth")+"&quickexpiredYear="
		    		+data.get("quickexpiredYear")+"&quickHoldIdType=0&quickPan="+data.get("quickPan")+"&quickphoneNo="+data.get("tel")
		    		+"&savePci=1");
		    query.Post();
		    
		    query.nextRequest();
		    String queryurl="https://www.99bill.com/gateway/creditCardQuery.htm?method=getOrderStatus";
		    query.setUrl(queryurl);
		    query.Get();
		    
		   
		    //通知商户
		    String result=NoticeTrader(query,data);
		    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");
		}
		/**
		 *网关3.0快捷分期支付 
		 */	
		public  boolean gateway3InstallmentPay(HttpFixture query, Map<String, String> data) {
			// 选择银行
//			query.addHeaderValue("Content-Type",
//					"application/x-www-form-urlencoded");
//			query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectBank");
//			query.addRequestBody("&bankId=" + data.get("selectBankId"));
//			query.Post();
			
			//点击获取手机验证码
			query.nextRequest();
			String url="https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=postDynaCode&quickPay=N&bankId=BOC&quickPan="+
			data.get("storablePan")+"&quickcvv2="+data.get("cvv2")+"&quickcardHoldName=test&quickcardHolderId="+data.get("quickcardHolderId")
			+"&quickphoneNo="+data.get("tel")+"&quickexpiredDate=0520&quickHoldIdType=0&savePci=1&rad=&period="+data.get("period")+"&instalmentTimes=";
			query.setUrl(url);
			query.Get();
			
			//查询验证码
			String code=gatewayDbCheck.getMobileValidateCode(data);
			System.out.println("CODE______________"+code);
			
			//输入有效期 验证码信息
			query.nextRequest();
			query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
			
				query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectCreditCardPay&bankId=BOC&quickOrCnp=1");
			
				
		    query.addRequestBody("bankId="+data.get("selectBankId")+"&creditCardQuickPayValidateCode="+code+"&period="+data.get("period")+"&quickcardHolderId="+data.get("quickcardHolderId")+"&quickcardHoldName="
		    +data.get("quickcardHoldName")+"&quickcvv2="+data.get("cvv2")+"&quickexpiredMonth="+data.get("quickexpiredMonth")+"&quickexpiredYear="
		    		+data.get("quickexpiredYear")+"&quickHoldIdType=0&quickPan="+data.get("quickPan")+"&quickphoneNo="+data.get("tel")
		    		+"&savePci=1");
		    //System.out.println("requestBody___________"+"&bankId="+data.get("selectBankId")+"&creditCardQuickPayValidateCode="+code+"&period="+data.get("period")+"&quickcardHolderId="+data.get("quickcardHolderId")+"&quickcardHoldName="
		    //+data.get("quickcardHoldName")+"&quickcvv2="+data.get("cvv2")+"&quickexpiredMonth="+data.get("quickexpiredMonth")+"&quickexpiredYear="
		    		//+data.get("quickexpiredYear")+"&quickHoldIdType=0&quickPan="+data.get("quickPan")+"&quickphoneNo="+data.get("tel")
		    		//+"&savePci=1&");
		    query.Post();
		    //System.out.println("1111111111111111111111111111++++++++++++++++++++++++++++++++++++"+query.getResponseBody());
		    
		    query.nextRequest();
		    String queryurl="https://www.99bill.com/gateway/creditCardQuery.htm?method=getOrderStatus";
		    query.setUrl(queryurl);
		    query.Get();
		    //System.out.println("tongzhishanghu______________"+query.getResponseBody());
		    System.out.println("data!!!!!!!!!!!!!!!!!!!!"+data.get("orderId"));
		   
		    //通知商户
		    String result=NoticeTrader(query,data);
		    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");
		}
		
	    /**
		 *网关3.0非快捷支付 
		 */	
		public  boolean gateway3ClickPayoff(HttpFixture query, Map<String, String> data) {
			// 选择银行
			query.addHeaderValue("Content-Type",
					"application/x-www-form-urlencoded");
			query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectBank");
			query.addRequestBody("&bankId=" + data.get("selectBankId"));
			query.Post();
			
			//点击获取手机验证码
			query.nextRequest();
			String url="https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=postDynaCode&quickPay=N&bankId=BOC&quickPan="+
			data.get("storablePan")+"&quickcvv2="+data.get("cvv2")+"&quickcardHoldName=test&quickcardHolderId="+data.get("quickcardHolderId")
			+"&quickphoneNo="+data.get("tel")+"&quickexpiredDate=0520&quickHoldIdType=0&savePci=0&rad=&instalmentTimes=";
			query.setUrl(url);
			query.Get();
			
			//查询验证码
			String code=gatewayDbCheck.getMobileValidateCode(data);
			
			//输入有效期 验证码信息
			query.nextRequest();
			query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
			if(data.get("PayTime").equals("1")){
				query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectCreditCardPay&bankId=BOC&quickOrCnp=1");
			}else{
				query.setUrl("https://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectCreditCardQuickPay&bankId=BOC&quickOrCnp=1");
			}   
		    query.addRequestBody("&bankId="+data.get("selectBankId")+"&creditCardQuickPayValidateCode="+code+"&instalmentTimes=&quickcardHolderId="+data.get("quickcardHolderId")+"&quickcardHoldName="
		    +data.get("quickcardHoldName")+"&quickcvv2="+data.get("cvv2")+"&quickexpiredMonth="+data.get("quickexpiredMonth")+"&quickexpiredYear="
		    		+data.get("quickexpiredYear")+"&quickHoldIdType=0&quickPan="+data.get("quickPan")+"&quickphoneNo="+data.get("tel")
		    		+"&savePci=0");
		    query.Post();
		    
		    query.nextRequest();
		    String queryurl="https://www.99bill.com/gateway/creditCardQuery.htm?method=getOrderStatus";
		    query.setUrl(queryurl);
		    query.Get();
		   
		    //通知商户
		    String result=NoticeTrader(query,data);
		    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");
		}
		
			
		/**
		 *网关3.0储蓄卡支付 
		 */	
		public  boolean gateway3DepositPay(HttpFixture query, Map<String, String> data) {
			//点击获取手机验证码
			query.nextRequest();
			String url="https://www.99bill.com/gateway/debitCardPay.htm?method=postDynaCode&quickPay=N&bankId=BOC&quickPan="+data.get("quickPan")+"&quickCardHoldName=test&quickCardHolderId=330327197801030037&quickPhoneNo=18502539841&quickHoldIdType=0&savePci=1";
			query.setUrl(url);
			query.Get();
			
			//查询验证码
			String code=gatewayDbCheck.getMobileValidateCode(data);
			
			//输入有效期 验证码信息
			query.nextRequest();
			query.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
			if(data.get("PayTime").equals("1")){
				 query.setUrl("https://www.99bill.com/gateway/debitCardPay.htm?method=selectDebitCardPay&bankId=BOC");	
			}else{
				 query.setUrl("https://www.99bill.com/gateway/debitCardPay.htm?method=selectDebitCardQuickPay&bankId=BOC");
			}
		    query.addRequestBody("&bankId="+data.get("selectBankId")+"&creditCardQuickPayValidateCode="+code+"&quickCardHolderId="+data.get("quickCardHolderId")
		    		+"&quickCardHoldName="+data.get("quickCardHoldName")+"&quickHoldIdType="+data.get("quickHoldIdType")
		    		+"&quickPan="+data.get("quickPan")+"&quickPhoneNo="+data.get("tel")+"&savePci="+data.get("savePci"));
		    query.Post();
            // System.out.println(query.getResponseBody());
		    //通知商户
		    String result=NoticeTrader(query,data);
		    return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")||result.contains("<td width=30%>10</td>");
		}
/**
 *网关3.0无卡支付 
 */
	
	public boolean gateway3Pay(HttpFixture query, Map<String, String> data) {
		// 选择银行
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=selectBank");
		query.addRequestBody("&bankId=" + data.get("selectBankId"));
		query.Post();
		// 输入卡号、有效期
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=cnpPaySure");
		query.addRequestBody("&bankId=" + data.get("selectBankId")
				+ "&instalmentTimes=&quickcvv2=" + data.get("quickcvv2")
				+ "&quickPan=" + data.get("quickPan") + "&savePci="
				+ data.get("savePci"));
		query.Post();
		// 确认支付
		query.nextRequest();
		query.addHeaderValue("Content-Type",
				"application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/creditCardQuickPayV3.htm?method=creditCnpSelectBank");
		query.addRequestBody("&cvv2=" + data.get("cvv2") + "&pan="
				+ data.get("quickPan") + "&selectedbankId="
				+ data.get("selectBankId"));
		query.Post();
		// 通知商户
		String result = NoticeTrader(query, data);
		return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")
				|| result.contains("<td width=30%>10</td>");

	}
	
	/**
	 * 移动网关信用卡快捷首次支付
	 */
	 
	public boolean mobileGatewayClickFirstPay(HttpFixture query, Map<String, String> data) {
		//输入卡号
		query.nextRequest();
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
		query.addRequestBody("method=checkCardInfo&card_number=" +data.get("card_number"));
		query.Post();
//		System.out.println("======1======="+query.getResponseBody());
//		query.nextRequest();
//		query.setUrl(parametersInitialization.getMobilePayActionUrl());
//		query.addRequestBody("method=index&card_number=" + "6259+6540+9019+1148");
//		query.Post();
//		System.out.println("======2======="+query.getResponseBody());
		
		//点击获取手机验证码
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
//		query.addRequestBody("method=postDynaCode&lan=1&date="+System.currentTimeMillis()+"&cardType=0001&type=noBind&bankId=CCB&cardNum=6259654090191148&validTime=1218&cvv2=990&id=320123198310180029&tel=18751851950&vcode=&supportQuickPay=on");
		query.addRequestBody("method=postDynaCode&lan=1&date="+System.currentTimeMillis()+"&cardType=0001&type=noBind&bankId="+data.get("bankId")+"&cardNum="+data.get("card_number")+"&validTime="+data.get("expiredDate")+"&id="+data.get("idCode")+"&expiredDate="
				+data.get("expiredDate")+"&tel="+data.get("tel")+"&vcode=&cvv2="+data.get("cvv2")+"&supportQuickPay=on");
		
		query.Post();
		
		if(query.getResponseBody().contains("验证码已发送")){
			Reporter.log("手机验证码发送成功",true);
		}else{
			String errorMsg=query.saveParamLeftstrRightstr("\"errorMessage\":\"", "\"}");
			Reporter.log("【失败原因】："+errorMsg);
			Reporter.log("手机验证码发送失败",false);

		}
		//System.out.println("======3======="+query.getResponseBody());
		
		//查询验证码
		String code=gatewayDbCheck.getMobileValidateCode(data);
		
		if(StringUtil.isEmpty(code)){
			Reporter.log("数据库查询验证码失败",false);
		}
		
		//确认支付
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobileCreditPayAction());
		query.addRequestBody("method=detailPay&cardType=0001&type=detailPay&expiredDate="+data.get("expiredDate")+"&tel="
		                       +data.get("tel")+"&vcode="+code+"&supportQuickPay=on");
		query.Post();
		//System.out.println("======4======="+query.getResponseBody());
		//商户通知
		String result = NoticeTrader(query, data);
		return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")|| result.contains("<td width=30%>10</td>");

	}
	/**
	 * 移动网关信用卡快捷首次不支付绑定银行卡
	 */
	 
	public boolean mobileGatewayClickFirstPayQuickPayoff(HttpFixture query, Map<String, String> data) {
		//输入卡号
		query.nextRequest();
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
		query.addRequestBody("method=checkCardInfo&card_number=" + data.get("card_number"));
		query.Post();
		//System.out.println("======1======="+query.getResponseBody());
		query.nextRequest();
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
		query.addRequestBody("method=index&card_number=" + data.get("card_number"));
		query.Post();
		//System.out.println("======2======="+query.getResponseBody());
		
		//点击获取手机验证码
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
		query.addRequestBody("method=postDynaCode&lan=1&date="+System.currentTimeMillis()+"&cardType=0001&type=detailPay&expiredDate="
		+data.get("expiredDate")+"&tel="+data.get("tel")+"&vcode=&cvv2="+data.get("cvv2")+"&supportQuickPay=off");
		query.Post();
		System.out.println("======3======="+query.getResponseBody());
		query.nextRequest();
		
		//查询验证码
		String code=gatewayDbCheck.getMobileValidateCode(data);
		
		//确认支付
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobileCreditPayAction());
		query.addRequestBody("method=detailPay&cardType=0001&type=detailPay&expiredDate="+data.get("expiredDate")+"&tel="
		                       +data.get("tel")+"&vcode="+code+"&supportQuickPay=off");
		query.Post();
		//System.out.println("======4======="+query.getResponseBody());
		//商户通知
		String result = NoticeTrader(query, data);
		return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")|| result.contains("<td width=30%>10</td>");

	}
	/**
	 *  移动网关信用卡快捷二次支付
	 */
	public boolean mobileGatewayClickPay(HttpFixture query, Map<String, String> data) {
		//点击获取手机验证码
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
		query.addRequestBody("method=postDynaCode&lan=1&date="+System.currentTimeMillis()+"cardType=0001&type=quickPay&vcode=&cvv2="+data.get("cvv2")+"&vcode=");
		query.Post();
		//System.out.println("======3======="+query.getResponseBody());
		if(query.getResponseBody().contains("验证码已发送")){
			Reporter.log("手机验证码发送成功",true);
		}else{
			String errorMsg=query.saveParamLeftstrRightstr("\"errorMessage\":\"", "\"}");
			Reporter.log("【失败原因】："+errorMsg);
			Reporter.log("手机验证码发送失败",false);

		}
		//System.out.println("======3======="+query.getResponseBody());
		
		//查询验证码
		String code=gatewayDbCheck.getMobileValidateCode(data);
		
		if(StringUtil.isEmpty(code)){
			Reporter.log("数据库查询验证码失败",false);
		}
		
		//确认支付
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobileCreditPayAction());
		query.addRequestBody("method=quickPay&cardType=0001&type=quickPay&cvv2="+data.get("cvv2")+"&vcode="+code);
		query.Post();
		//System.out.println("======4======="+query.getResponseBody());
		//商户通知
		String result = NoticeTrader(query, data);
		return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")|| result.contains("<td width=30%>10</td>");

	}
	/**
	 * 移动网关储蓄卡首次支付
	 */
	 
	public boolean mobileGatewayDepositFirstPay(HttpFixture query,
			Map<String, String> data) {
		        //输入卡号
				query.nextRequest();
				query.setUrl(parametersInitialization.getMobilePayActionUrl());
				query.addRequestBody("method=checkCardInfo&card_number=" + data.get("card_number"));
				query.Post();
				//System.out.println("======1======="+query.getResponseBody());
//				query.nextRequest();
//				query.setUrl(parametersInitialization.getMobilePayActionUrl());
//				query.addRequestBody("method=index&card_number=" + data.get("card_number"));
//				query.Post();
				//System.out.println("======2======="+query.getResponseBody());
				
				//点击获取手机验证码
				query.nextRequest();
				query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
				query.setUrl(parametersInitialization.getMobilePayActionUrl());
//				query.addRequestBody("method=postDynaCode&lan=1&date="+System.currentTimeMillis()+
//						"cardType=0002&type=detailPay&bankId=CCB&cardHolderName="+data.get("cardHolderName")
//						+"&idCard="+data.get("idCard")+"&tel="+data.get("tel")+"&vcode=&supportQuickPay=on");
				query.addRequestBody("method=postDynaCode&lan=1&date="+System.currentTimeMillis()+"&cardType=0002&type=noBind&bankId="+data.get("bankId")+"&cardHolderName="+data.get("cardHolderName")+"&cardNum="+data.get("card_number")+"&validTime="+data.get("expiredDate")+"&id="+data.get("idCode")+"&tel="+data.get("tel")+"&supportQuickPay=on");
				
				query.Post();
				
				if(query.getResponseBody().contains("验证码已发送")){
					Reporter.log("手机验证码发送成功",true);
				}else{
					String errorMsg=query.saveParamLeftstrRightstr("\"errorMessage\":\"", "\"}");
					Reporter.log("【失败原因】："+errorMsg);
					Reporter.log("手机验证码发送失败",false);

				}
				//System.out.println("======3======="+query.getResponseBody());
				
				//查询验证码
				String code=gatewayDbCheck.getMobileValidateCode(data);
				
				if(StringUtil.isEmpty(code)){
					Reporter.log("数据库查询验证码失败",false);
				}
				
//				query.Post();
				//System.out.println("======3======="+query.getResponseBody());
				
				//查询验证码
//				String code=gatewayDbCheck.getMobileValidateCode(data);
				
				//确认支付
				query.nextRequest();
				query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
				query.setUrl(parametersInitialization.getMobileDebitPayAction());
				query.addRequestBody("method=detailPay&cardType=0002&type=detailPay&bankId=CCB&cardHolderName="+data.get("cardHolderName")
						+"&idCard="+data.get("idCode")+"&tel="+data.get("tel")+"&vcode="+code+"&supportQuickPay=on");
				query.Post();
				//System.out.println("======4======="+query.getResponseBody());
				//商户通知
				String result = NoticeTrader(query, data);
				return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")|| result.contains("<td width=30%>10</td>");

	}
	/**
	 * 移动网关储蓄卡二次支付
	 */
	public boolean mobileGatewayDepositPay(HttpFixture query,
			Map<String, String> data) {
		//点击获取手机验证码
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
		query.addRequestBody("method=postDynaCode&lan=1&date="+System.currentTimeMillis()
				+"cardType=0002&type=quickPay&storablePan="+data.get("storablePan")+"&storablePhoneNo="+data.get("tel")+"&vcode=");
		query.Post();
		//System.out.println("======3======="+query.getResponseBody());
		
		if(query.getResponseBody().contains("验证码已发送")){
			Reporter.log("手机验证码发送成功",true);
		}else{
			String errorMsg=query.saveParamLeftstrRightstr("\"errorMessage\":\"", "\"}");
			Reporter.log("【失败原因】："+errorMsg);
			Reporter.log("手机验证码发送失败",false);

		}
		//System.out.println("======3======="+query.getResponseBody());
		
		//查询验证码
		String code=gatewayDbCheck.getMobileValidateCode(data);
		
		if(StringUtil.isEmpty(code)){
			Reporter.log("数据库查询验证码失败",false);
		}
		
		//确认支付
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobileDebitPayAction());
		query.addRequestBody("method=quickPay&cardType=0002&type=quickPay&storablePan="+data.get("storablePan")+"&storablePhoneNo="+data.get("tel")+"&vcode="+code);
		query.Post();
		//System.out.println("======4======="+query.getResponseBody());
		//商户通知
		String result = NoticeTrader(query, data);
		return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")|| result.contains("<td width=30%>10</td>");

	}
	/**
	 * 移动网关内卡无卡支付
	 */
	public boolean mobileGatewayInnercardCNP(HttpFixture query,
			Map<String, String> data) {
		//输入卡号
		query.nextRequest();
		query.setUrl(parametersInitialization.getMobilePayActionUrl());
		query.addRequestBody("method=checkCardInfo&card_number=" + data.get("card_number"));
		query.Post();
		//System.out.println("======1======="+query.getResponseBody());
		//query.nextRequest();
		//query.setUrl(parametersInitialization.getMobilePayActionUrl());
		//query.addRequestBody("method=index&card_number=" + data.get("card_number"));
		//query.Post();
		//System.out.println("======2======="+query.getResponseBody());
		
		//确认支付
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl(parametersInitialization.getMobileCreditPayAction());
		query.addRequestBody("method=cnpPay&cardType=0001&type=cnpPay&cvv2="+data.get("cvv2"));
		query.Post();
		//System.out.println("======4======="+query.getResponseBody());
		//商户通知
		String result = NoticeTrader(query, data);
		return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")|| result.contains("<td width=30%>10</td>");
	}
	
	/**
	 * 信用卡分期付款
	 * @param query
	 * @param data
	 * @return
	 */
	public boolean InstallmentPay(HttpFixture query, Map<String, String> data) {
		//信用卡支付
		query.nextRequest();
		query.addHeaderValue("Content-Type","application/x-www-form-urlencoded");
		query.setUrl("http://www.99bill.com/gateway/creditCardPay.htm?method=selectBank");
		query.addRequestBody("&action2=&bankId="+data.get("selectBankId")+"&cardHolderId="+data.get("cardHolderId")
				+"&cardHolderName="+data.get("cardHolderName")+"&cvv2="+data.get("cvv2")+"&expiredMonth="+data.get("expiredMonth")
				+"&expiredYear="+data.get("expiredYear")+"&idType="+data.get("idType")+"&pan="+data.get("pan")+"&pan=INP&password=");
		query.Post();
		//System.out.println(query.getResponseBody());
		query.nextRequest();
		query.setUrl("http://www.99bill.com/gateway/creditCardQuery.htm?method=getOrderStatus");
		query.Get();
		//商户通知
		String dealId =gatewayDbCheck.getInstallmentSequenceidByOrderid(data).getDealid();
		query.nextRequest();
		query.setUrl("http://www.99bill.com/gwfnotify/notifyMerchant.htm?dealId="+dealId+"&orderId="+data.get("orderId"));
		query.Get();
		//System.out.println(query.getResponseBody());
		if(query.getResponseBody().contains("通知失败！")){
			Reporter.log(query.saveParamLeftstrRightstr("<br/>", "</body>"), false);
			Reporter.log("=======判断可能为系统异常====手动测试确认=======");
		}
		String url2=query.getResponseBody().split("url='")[1].split("'\">")[0];
        query.setUrl(url2);
        query.Get();
        //System.out.println("2============"+query.getResponseBody());
		//联通支付
		if(null!=data.get("PartnerID")&&"99BILLUNICOM".equals(data.get("PartnerID"))){
			return ChinaUnicomUtil.decryptSign(query.saveParamLeftstrRightstr("PayRepObj\" value=\"", "\"/>"), data.get("key")).contains("PayResult=0");
			}
		String result=query.getResponseBody().replace("\r\n", "").replace("	", "");
		return result.contains("bgcolor=\"#F9F9F9\">Y  </td>")|| result.contains("<td width=30%>10</td>");
	}
	
	
	/**
	 * 通知商户
	 * @param query
	 * @param data
	 * @return
	 */
	    
		public String NoticeTrader(HttpFixture query,Map<String, String> data){	
			
		   String dealId =gatewayDbCheck.getSequenceidByOrderid(data).getDealid();
		   System.out.println("dealid"+dealId);
			query.nextRequest();
			String dealUrl = "https://www.99bill.com/gwfnotify/notifyMerchant.htm?dealId="+dealId+"&orderId="+data.get("orderId");
			Reporter.log("====dealUrl====="+dealUrl);
			query.setUrl(dealUrl);
			query.Get();
			System.out.println("tongzhijieguo"+query.getResponseBody());
			if(query.getResponseBody().contains("通知失败！")){
				Reporter.log(query.saveParamLeftstrRightstr("<br/>", "</body>"), false);
				Reporter.log("=======判断可能为系统异常====手动测试确认=======");
			}
			String url2=query.getResponseBody().split("url='")[1].split("'\">")[0];
	        query.setUrl(url2);
	        query.Get();
			String s=query.getResponseBody().replace("\r\n", "").replace("	", "");
			System.out.println("900000000000000000000000000000000");
			
			return s;
			
		}
		
		/**
		 *模拟银行充值
		 */	
		  		
		public boolean reChargeDemoBank(HttpFixture query, Map<String, String> data) {
			String bankOrderNo = data.get("orgorderID");
			System.out.println("orderno"+data.get("orgorderID"));
			String bankMerchantId = "105110089991048"; // 模拟银行固定的merchantid
			String privateKey = "123456789abcdefg";
			String sign = MD5Util.MD5("merchantId=" + bankMerchantId + "&orderNo=" + bankOrderNo + "&payMoney=" + Float.valueOf(data.get("orderAmount")) / 100
					+ "&result=1&privateKey=" + privateKey + "", "1");
			query.nextRequest();
			 System.out.println("http://192.168.8.14/bankgateway/bankgateway/card/demobank.htm?merchantId="
			 + bankMerchantId + "&orderNo=" + bankOrderNo
			+ "&payMoney=" + Float.valueOf(data.get("orderAmount")) / 100 +
			 "&result=1&sign=" + sign + "");
			query.setUrl("http://www.99bill.com/bankgateway/bankgateway/card/demobank.htm?merchantId=" + bankMerchantId + "&orderNo=" + bankOrderNo + "&payMoney="
					+ Float.valueOf(data.get("orderAmount")) / 100 + "&result=1&sign=" + sign + "");
			System.out.println("http://www.99bill.com/bankgateway/bankgateway/card/demobank.htm?merchantId=" + bankMerchantId + "&orderNo=" + bankOrderNo + "&payMoney="
					+ Float.valueOf(data.get("orderAmount")) / 100 + "&result=1&sign=" + sign + "");
			query.Get();
			System.out.println("resPonse____________________________________________________________"+query.getResponseBody());
			
			boolean flag= query.getResponseBody().contains("<div class=\"success\">恭喜您，支付成功！");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			query.nextRequest();
//			query.setUrl("https://www.99bill.com/fideposit/deposite/showresult.htm");
//			query.Get();  
//			System.out.println("hahahahahahahaah"+query.getResponseBody());
//			System.out.println("交易号："+CheckResponse
//					 .parseString(
//							 "(?<=<td><b>)(.*?)(?=</strong>（元）</td>)",
//							 query.getResponseBody()));
			
			return flag;
			
		}
		
		
		
		
	public GatewayDbCheck getGatewayDbCheck() {
		return gatewayDbCheck;
	}

	public void setGatewayDbCheck(GatewayDbCheck gatewayDbCheck) {
		this.gatewayDbCheck = gatewayDbCheck;
	}
	
	public ParametersInitialization getParametersInitialization() {
		return parametersInitialization;
	}
	public void setParametersInitialization(
			ParametersInitialization parametersInitialization) {
		this.parametersInitialization = parametersInitialization;
	}



}
