package com.bill99.ate.common.httpclient;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 替换HTML转义符
	 * @param s   
	 * @return   
	 */
	public static String replaceHtml(String s) {   
		String tmp = s.replaceAll("\\&lt;", "<");
		tmp = tmp.replaceAll("\\&gt;", ">");
		tmp = tmp.replaceAll("\\&amp;", "&");
		tmp = tmp.replaceAll("\\&apos;", "\'");
		tmp = tmp.replaceAll("\\&quot;", "\"");
		tmp = tmp.replaceAll("\\&nbsp;", " ");
		tmp = tmp.replaceAll("<br>", "\n");            
		return tmp;  
	}    
	
	/**
	 * 替换HTML转义符
	 * @param s   
	 * @return   
	 */
	public static String newreplaceHtml(String s) {   
		String tmp = s.replaceAll("\\&lt;", "<");
		tmp = tmp.replaceAll("\\&gt;", ">");
		tmp = tmp.replaceAll("\\&amp;", "&");
		tmp = tmp.replaceAll("\\&apos;", "\'");
		tmp = tmp.replaceAll("\\&quot;", "\"");
		tmp = tmp.replaceAll("\\&nbsp;", " ");
		tmp = tmp.replaceAll("<br>", "\n");
	 	tmp = tmp.replaceAll("__", "_");              
		return tmp;  
	} 
	 
	/**
	 * 判断是否非空 null or "" 返回 false 否则返回 true
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return str != null && str.trim().length() > 0;
	}
	
	/**
	 * 判断某一个字符串数组strList中有没有与字符串aStr相同的
	 *
	 * @param aStr
	 * @param strList
	 * @return
	 */
	public static boolean isIn(String aStr, String[] strList) {
		if (aStr == null || strList == null)
			return false;
		for (int i = 0; i < strList.length; i++) {
			if (strList[i] == null)
				continue;
			else {
				if (strList[i].equals(aStr))
					return true;
			}
		}
		return false;
	}
	/**
	 * 将以厘为单位的金额转换为以元为单位的金额，保留两位小数
	 * @param money 以厘为单位的金额
	 * @return 元为单位的金额
	 */
	public static String moneyFormat(String money){
		float f=Float.parseFloat(money)/1000;
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		return decimalFormat.format(f);
	}
	
	/**
	 * "\\s*|\t|\r|\n"
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
    	String dest = "";
    	if (str!=null) {
	    	Pattern p = Pattern.compile("\\r");
	    	Matcher m = p.matcher(str);
	    	dest = m.replaceAll("");
    	}
    	return dest;
    }
	
	/**
     * 
     * lpad 方法
     * <p>方法说明:指定一个长度，当指定字符串没有答达长度时，
     * 往字符串左边插入repalce。然后返回一个该长度的字符串</p>  
     * @param s
     * @param n
     * @param repalce
     * @return
     * @author wangsh
     * @since 2013-6-24-下午02:28:07
     */
    public static String lpad(String s,int n,String repalce){
    	StringBuffer sb =new StringBuffer(s);
    	while(sb.length()<n){
    		sb.insert(0, repalce);
    	}
    	return sb.substring(sb.length() -n);
    }
    /**
     * 
     * lpad 方法
     * <p>方法说明:指定一个长度，当指定字符串没有答达长度时，
     * 往字符串右边插入repalce。然后返回一个该长度的字符串</p>  
     * @param s
     * @param n
     * @param repalce
     * @return
     * @author wangsh
     * @since 2013-6-24-下午02:28:07
     */
    public static String rpad(String s,int n,String repalce){
    	StringBuffer sb =new StringBuffer(s);
    	while(sb.length()<n){
    		sb.append(repalce);
    	}
    	return sb.substring(0, n-1);
    }
    
    /**
     * 
     * ifNullGetDefualt 方法
     * <p>方法说明:如果传进来的值为空则返回默认值,如果不为空则返回src.toString()</p>  
     * @param src  
     * @param defualt
     * @return
     * @author wangsh
     * @since 2013-6-24-下午02:28:07
     */
    public static String ifNullGetDefualt(Object src,String defualt) {
    	String value ="";
    	if (defualt ==null) {
    		defualt ="";
    	}
    	if (src ==null||src.equals("")) {
    		value =defualt;
    	} else {
    		value =src.toString();
    	}
    	return value;
    }
    
    public static String null2Str(Object origin) {
	    return ((origin == null) ? "" : origin.toString().trim());            
	  }	
}
