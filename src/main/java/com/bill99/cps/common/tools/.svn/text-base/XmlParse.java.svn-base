package com.bill99.cps.common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParse {

	
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
	
	
	public static String getValidateCode(String before, String after, String smsContent) {
		return XmlParse.parseString("(?<="+before+")(.*?)(?="+after+")", smsContent);
	}
	
	public static String getResponse(String reposeString, String node) {
		String parseString = "(?<=<" + node + ">)(.*?)(?=</" + node + ">)";
		return XmlParse.parseString(parseString, reposeString);
	}
}
