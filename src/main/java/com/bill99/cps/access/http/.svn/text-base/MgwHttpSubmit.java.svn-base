package com.bill99.cps.access.http;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import shelper.iffixture.HttpFixture;

public class MgwHttpSubmit {
	
	public HttpFixture Hf;
	private String autip;
	private String sslport;
	private String autuser;
	private String autuserpass;
	private String keystorepass;
	private String trustkeystorepass;
	
	
	 public void setAutip(String autip) {
		this.autip = autip;
	}

	public void setSslport(String sslport) {
		this.sslport = sslport;
	}

	public void setAutuser(String autuser) {
		this.autuser = autuser;
	}

	public void setAutuserpass(String autuserpass) {
		this.autuserpass = autuserpass;
	}

	public void setKeystorepass(String keystorepass) {
		this.keystorepass = keystorepass;
	}

	public void setTrustkeystorepass(String trustkeystorepass) {
		this.trustkeystorepass = trustkeystorepass;
	}


	public String post(String content,String url) throws NumberFormatException, MalformedURLException{

		 Hf = httpcommonmethod();
		 Hf.setUrl(url);
		 Hf.addRequestBody(content);
		 Hf.Post();
		 return Hf.getResponseBody();
	 }
	
	public String get(String url){
		 Hf = httpcommonmethod();
		 Hf.setUrl(url);
		 Hf.Get();
		 return Hf.getResponseBody();
	}
	

	private HttpFixture httpcommonmethod(){
//	 autip,Integer.parseInt(sslport),autuser,autuserpass   keystorepass   trustkeystorepass
//	"jetty-7.99bill.com",Integer.parseInt("8455"),"201000000000801","123456"   "123456"  "mas123"
		HttpFixture httpFix = null;
		 try {
			 httpFix = new HttpFixture(autip,Integer.parseInt(sslport),autuser,autuserpass
						 , new URL("file:"+new File("./").getCanonicalPath() + "\\src\\main\\resources\\qacontext\\httpconfig\\VPOS_cnp"),
						 keystorepass ,
						 new URL("file:"+new File("./").getCanonicalPath() + "\\src\\main\\resources\\qacontext\\httpconfig\\channel_srv_eval_ks")
			            ,trustkeystorepass
						 );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return httpFix;
	}
	
	/*public static void main(String[] args) {
	MgwHttpSubmit m1=new MgwHttpSubmit();
//	 String
//	 content="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version><TxnMsgContent><txnType>PRE</txnType><interactiveStatus>TR1</interactiveStatus><cardNo>9558821001002575556</cardNo><expiredDate>0913</expiredDate><cvv2>109</cvv2><amount>100</amount><termInMonths></termInMonths><merchantId>200900000072400</merchantId><terminalId>20090724</terminalId><entryTime>20111011165449</entryTime><externalRefNumber>20111011165449</externalRefNumber><customerId>123456</customerId><storableCardNo></storableCardNo><cardHolderName>test</cardHolderName><cardHolderId>640102198102080616</cardHolderId><idType>0</idType><extMap><extDate><key>phone</key><value></value></extDate></extMap><rifleMap/><orgPartyId></orgPartyId></TxnMsgContent></MasMessage>";
	
	
	try {
//		String a = m1.post(content,"https://192.168.52.218:8443/cnp/purchase");
		String a = m1.get("https://jetty-7.99bill.com:8455/cnp/accept_capacity");
		System.out.println("a=" + a);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
	
}
