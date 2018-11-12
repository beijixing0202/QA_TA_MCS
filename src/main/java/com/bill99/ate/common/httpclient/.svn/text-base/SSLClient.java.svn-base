package com.bill99.ate.common.httpclient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class SSLClient {
	private static SSLConnectionSocketFactory sslsf; 
	private static HttpClientBuilder builder;
	static {
		try {
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,new TrustStrategy() {

				public boolean isTrusted(X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					return true;
				}}).build();
			sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,  
	                   SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
			builder=HttpClients.custom().setSSLSocketFactory(sslsf).setMaxConnPerRoute(200).setMaxConnTotal(400);
		} catch(KeyStoreException e){
			
		}catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	public static CloseableHttpClient  getHttpClient(){
		if (builder !=null){
			return builder.build();
		}
		return HttpClients.createDefault();
	}
}
