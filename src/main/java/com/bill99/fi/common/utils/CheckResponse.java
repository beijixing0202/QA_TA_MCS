package com.bill99.fi.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckResponse {
	
	
	boolean checkresult=false;
	public boolean paytypeResponse(String response,String data){
		
		String result[]=response.split("<li|</li>");
		for (String temp : result) {
			if(temp.contains("display:block")){
//				System.out.println(temp);
				checkresult= temp.contains(data);
//				System.out.println(checkresult);
			}	 
		}
		return checkresult;
	}
	
	public static String parseString(String expression,String requeString){
		String parsestringreturn = "";
		Pattern pattern = Pattern.compile(expression);
		Matcher mat = pattern.matcher(requeString);
		boolean result = mat.find();
		if (result) {
			parsestringreturn = mat.group();
//			Reporter.log("解析msgcotent如下=" + parsestringreturn);
		}
		
		return parsestringreturn;
		
	}
 

}
