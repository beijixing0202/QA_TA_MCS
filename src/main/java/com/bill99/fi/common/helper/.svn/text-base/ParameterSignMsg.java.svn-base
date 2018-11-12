package com.bill99.fi.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.bill99.fi.common.utils.MD5Util;
import com.bill99.fi.common.utils.PKIUtil;

/**
 * 加签
 * 
 * @author kaiquan.jiang
 * 
 */
public class ParameterSignMsg {
	public String SignMsg(String[] strmac, Map<String, String> data) {
		StringBuffer restrtemp = new StringBuffer();

		for (String key : strmac) {
			
			if (key.equals("signMsg")) {
				key = "key";
			}
			if (key.equals("ref_signMsg")) {
				key = "ref_key";
			}
			if (null==data.get(key)||data.get(key).equals("")) {

			} else {
				restrtemp.append(key.replace("ref_", "") + "=" + data.get(key) + "&");
			}
		}
		 System.out.println("加签前字符串：" + restrtemp.substring(0,restrtemp.length() - 1));
		if ("4".equals(data.get("signType"))) {
			String signMsg=null;
//			try {
//				signMsg=URLEncoder.encode(PKIUtil.pki(data.get("memberCode"), data.get("featureCode"), restrtemp.substring(0, restrtemp.length() - 1), data.get("inputCharset")), "utf-8");
				signMsg=PKIUtil.pki(data.get("memberCode"), data.get("featureCode"), restrtemp.substring(0, restrtemp.length() - 1), data.get("inputCharset"));
//			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

				System.out.println("signMsg:"+signMsg);
			return signMsg;

		} else {

			return MD5Util.MD5(restrtemp.substring(0, restrtemp.length() - 1), data.get("inputCharset"));
		}

	}
}
