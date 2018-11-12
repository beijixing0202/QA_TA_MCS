package com.bill99.fi.common.helper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**添加默认值
 * @author kaiquan.jiang
 *
 */
public class ParameterDispose {
	public static Map<String, String> addDefaultValue(Map<String, String> data) {
		final String orderPrefix = "Auto";
		if (("").equals(data.get("orderId"))) {

			data.put("orderId", orderPrefix+String.valueOf(new Random().nextInt()));
		}
		if (("").equals(data.get("orderid"))) {

			data.put("orderid", orderPrefix+String.valueOf(new Random().nextInt()));
			data.put("orderId",data.get("orderid"));
		}
		if (("").equals(data.get("requestId"))) {
			
			data.put("requestId", orderPrefix+String.valueOf(new Random().nextInt()));
		}
		if (("").equals(data.get("ref_seqId")) ){

			data.put("ref_seqId", orderPrefix+String.valueOf(new Random().nextInt()));
		}
		if (("").equals(data.get("seqId")) ){

			data.put("seqId", orderPrefix+String.valueOf(new Random().nextInt()));
		}
	
		if (("").equals(data.get("orderTime"))) {
			
			data.put("orderTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}
         if (("").equals(data.get("Date"))) {
			
			data.put("Date", new SimpleDateFormat("yyyyMMdd").format(new Date()));
		}
		if (("").equals(data.get("payTime"))) {
			
			data.put("payTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//			data.put("payTime", "20070304152214");
			
		}
        if (("").equals(data.get("PayTime"))) {
			
			data.put("PayTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//        	data.put("payTime", "20070304152214");
		}
        if (("").equals(data.get("sharingTime"))) {
			
			data.put("sharingTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}
		if (("").equals(data.get("ref_returnTime"))) {
			
			data.put("ref_returnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}		
		if (("").equals(data.get("returnTime"))) {
			
			data.put("returnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}
		if (("").equals(data.get("requestTime"))) {
			
			data.put("requestTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}		
		if(("").equals(data.get("orderTimestamp"))){
			data.put("orderTimestamp", data.get("orderTime"));
		}
		if (("").equals(data.get("orderUpdateTime"))) {
			
			data.put("orderUpdateTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		}
		if(("").equals(data.get("mergeOrderId"))){
			data.put("mergeOrderId", String.valueOf(new Random().nextLong()));
		}
		if (("").equals(data.get("PayOrderID"))) {
			
			data.put("PayOrderID", "UDP"+String.valueOf(new Random().nextInt()));
			data.put("orderId",data.get("PayOrderID"));
		}
		
//		if(("ip").equals(data.get("Reserved2"))){
//			//获取本机IP
//			try {
//				InetAddress addr = InetAddress.getLocalHost();
//				data.put("Reserved2", addr.getHostAddress());
//			} catch (UnknownHostException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}		
//		}
//		
		System.out.println("orderID===="+data.get("orderId"));
		System.out.println("orderid===="+data.get("orderid"));
		System.out.println("requestId===="+data.get("requestId"));
		System.out.println("seqId===="+data.get("seqId"));		
		System.out.println("orderTime===="+data.get("orderTime"));
		System.out.println("payTime===="+data.get("payTime"));
		System.out.println("PayTime===="+data.get("PayTime"));
		System.out.println("sharingTime===="+data.get("sharingTime"));
		System.out.println("ref_returnTime===="+data.get("ref_returnTime"));
		System.out.println("orderTimestamp===="+data.get("orderTimestamp"));
		System.out.println("orderUpdateTime===="+data.get("orderUpdateTime"));
		System.out.println("returnTime===="+data.get("returnTime"));
		System.out.println("requestTime===="+data.get("requestTime"));
		System.out.println("Reserved2==="+data.get("Reserved2"));
		
		return data;
	} 
}
