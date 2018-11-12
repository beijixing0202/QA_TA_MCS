package com.bill99.fi.common.utils;
import com.bill99.fi.common.utils.MD5Util;


/**
 * @author leo.zhou
 *
 */
public class MSOrderQuery {
	private String strtemp="";
	private StringBuffer restrtemp=new StringBuffer();
	
	/**
	 * @param version
	 * @param queryType
	 * @param queryMode
	 * @param orderId
	 * @param pid
	 * @param signType
	 * @param key
	 * @return
	 */
	public String  SignMsg(String version,String queryType,String queryMode,String orderId,String pid,String signType,String key){
		String[] strmac={"version","queryType","queryMode","orderId","pid","signType","key"};
		String[] str={version,queryType,queryMode,orderId,pid,signType,key};
		for(int i=0;i<str.length;i++){
			if(str[i].equals("")){
				
			}else{
				restrtemp.append(strmac[i]+"="+str[i]+"&");
//				strtemp=strtemp+strmac[i]+"="+str[i]+"&";
			}
		}
		
		return  MD5Util.MD5(restrtemp.substring(0, restrtemp.length()-1),"1");

	} 
	
	/**
	 * @param inputCharset
	 * @param version
	 * @param signType
	 * @param queryType
	 * @param queryMode
	 * @param pid
	 * @param orderId
	 * @param refundSeqid
	 * @param key
	 * @return
	 */
	public String  ReSignMsg(String inputCharset,String version,String signType,String queryType,String queryMode,String pid,String orderId,String refundSeqid,String key){
		String[] strmac={"inputCharset","version","signType","queryType","queryMode","pid","orderId","refundSeqid","key"};
		String[] str={inputCharset,version,signType,queryType,queryMode,pid,orderId,refundSeqid,key};
		for(int i=0;i<str.length;i++){
			if(str[i].equals("")){
				
			}else{
				restrtemp.append(strmac[i]+"="+str[i]+"&");
//					restrtemp=restrtemp+strmac[i]+"="+str[i]+ "&";
			}
		}
		
		return MD5Util.MD5(restrtemp.substring(0,restrtemp.length()-1),"1");
	} 
	
	
//	public static void main(String[] args){
//		System.out.print(new MSOrderQuery().ReSignMsg("1", "v2.0", "1", "0", "1", "10011692830", "1948874800334839", "7033567701562486", "W4W53MY6SMDDLKQI"));
//	}	
}

