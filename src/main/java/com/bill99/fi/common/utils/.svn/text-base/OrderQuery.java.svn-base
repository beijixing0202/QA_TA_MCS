package com.bill99.fi.common.utils;
import com.bill99.fi.common.utils.MD5Util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;



/**加签
 * @author leo.zhou
 *
 */
public class OrderQuery {
	
	private String strtemp="";
	private StringBuffer restrtemp= new StringBuffer();
	

	
	/**api 查询接口加签
	 * @param inputCharset
	 * @param version
	 * @param signType
	 * @param merchantAcctId
	 * @param queryType
	 * @param queryMode
	 * @param startTime
	 * @param endTime
	 * @param requestPage
	 * @param orderId
	 * @param key
	 * @return
	 */
	public String  SignMsg(String inputCharset,String version,String signType,String merchantAcctId,String queryType,String queryMode,String startTime,String endTime,String requestPage,String orderId,String key){
		String[] strmac={"inputCharset","version","signType","merchantAcctId","queryType","queryMode","startTime","endTime","requestPage","orderId","key"};
		String[] str={inputCharset,version,signType,merchantAcctId,queryType,queryMode,startTime,endTime,requestPage,orderId,key};
		for(int i=0;i<str.length;i++){
			if(str[i].equals("")){
				
			}else{
				restrtemp.append(strmac[i]+"="+str[i]+"&");
//				strtemp=strtemp+strmac[i]+"="+str[i]+"&";
			}
		}
			
		return MD5Util.MD5(restrtemp.substring(0, restrtemp.length()-1),"1");
	} 
	
	/**ATA线下支付查询
	 * @param inputCharset
	 * @param version
	 * @param signType
	 * @param merchantAcctId
	 * @param queryType
	 * @param startTime
	 * @param endTime
	 * @param remitStartTime
	 * @param remitEndTime
	 * @param requestPage
	 * @param remitNumber
	 * @param remitCode
	 * @param payerName
	 * @return
	 */
	public String  SignMsg(String inputCharset, String version, String signType, String merchantAcctId, String queryType, String startTime, String endTime,  String requestPage, String remitNumber, String remitCode,String payerName,String key){
//		String strtemp="";
		String[] strmac={"inputCharset","version","signType","merchantAcctId","queryType","startTime","endTime","requestPage","remitNumber","remitCode","payerName","key"};
		String[] str={inputCharset,version,signType,merchantAcctId,queryType,startTime,endTime,requestPage,remitNumber,remitCode,payerName,key};
		for(int i=0;i<str.length;i++){
			if(str[i].equals("")){
				
			}else{
				restrtemp.append(strmac[i]+"="+str[i]+"&");
//				strtemp=strtemp+strmac[i]+"="+str[i]+"&";
			}
		}
			
		return MD5Util.MD5(restrtemp.substring(0, restrtemp.length()-1),"1");
	} 
	
	
	/**退款查询接口加签
	 * @param version
	 * @param signType
	 * @param acctId
	 * @param startDate
	 * @param endDate
	 * @param lastupdateStartDate
	 * @param lastupdateEndDate
	 * @param customerBatchId
	 * @param orderId
	 * @param requestPage
	 * @param OrderId
	 * @param seqId
	 * @param status
	 * @param key
	 * @return
	 */
	public String  ReSignMsg(Map<String , String> datadrven){
		String[] strmac={"version","signType","merchantAcctId","startDate","endDate","lastupdateStartDate","lastupdateEndDate","customerBatchId","orderId","requestPage","rOrderId","seqId","status","extra_output_column","key"};
		for (int i = 0; i < strmac.length; i++) {
			if(datadrven.get(strmac[i])==null||"".equals(datadrven.get(strmac[i]))){
				
			}else{
				restrtemp.append(strmac[i]+"="+datadrven.get(strmac[i])+"&");

			}
		}
		return MD5Util.MD5(restrtemp.substring(0, restrtemp.length()-1),"1");
	} 
	
	
	public static void main(String[] args) {
		new OrderQuery().SignMsg("inputCharset","version","signType","merchantAcctId","queryType","startTime","endTime","requestPage","remitNumber","remitCode","payerName","key");

	}	


}
