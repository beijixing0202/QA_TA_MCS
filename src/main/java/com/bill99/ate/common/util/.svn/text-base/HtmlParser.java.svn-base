/*
   File: 
   Description:
   Copyright 2004-2012 99Bill Corporation. All rights reserved.
    Date            Author          Changes
   2015-11-26		    xiangnan.qi	   		xiangnan.qi
*/

package com.bill99.ate.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author  xiangnan.qi
 * @date 2015-11-26 下午4:26:21 
 *  
 */
public class HtmlParser {

	@SuppressWarnings("unused")
	public static void parserString() {
		String html = "<html><head><title>First parse</title></head>" + "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getAllElements();
		System.out.println();
	}

	/**
	 * 
	 * @Description: 余额变更页面解析
	 * @param html
	 * @return
	 */
	public static Map<String, String> getMemberAcctCodeExpBalance(String html) {
		Document doc = Jsoup.parse(html);
		Elements resultLinks = doc.select("[name=expBalance]");
		Elements resuLinks = doc.select("[name=memberAcctcode]");
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, String> mapData = new HashMap<String, String>();
		List<String> balanceList = new ArrayList<String>();
		List<String> memberAcctcodeList = new ArrayList<String>();
		for (int i = 0; i < resuLinks.size(); i++) {
			String memberAcctcode = resuLinks.get(i).attributes().get("value");
			String expBalance = resultLinks.get(i).attributes().get("value");
			balanceList.add(resuLinks.get(i).attributes().get("value"));
			memberAcctcodeList.add(resultLinks.get(i).attributes().get("value"));
			mapData.put(memberAcctcode, expBalance);
			System.out.println("==============" + memberAcctcode + ":" + expBalance);
		}

		System.out.println(map.toString());
		map.put("expBalance", balanceList);
		map.put("memberAcctcode", memberAcctcodeList);
		return mapData;
	}

	/**
	 * 
	 * @Description: 解析绑定实体卡响应信息
	 * @param response
	 * @return
	 */
	public static Map<String, String> parserBandEntityCardResponse(String response) {

		return null;

	}
}
