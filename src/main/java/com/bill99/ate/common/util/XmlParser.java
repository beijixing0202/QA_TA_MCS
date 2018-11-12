/*
   File: 
   Description:
   Copyright 2004-2012 99Bill Corporation. All rights reserved.
    Date            Author          Changes
   2015-12-2		    xiangnan.qi	   		xiangnan.qi
*/

package com.bill99.ate.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author  xiangnan.qi
 * @date 2015-12-2 下午2:25:23 
 *  
 */
public class XmlParser {

	@SuppressWarnings("rawtypes")
	public static String XmlToObj(String xml) throws UnsupportedEncodingException, DocumentException {
		System.out.println("=======================xml start===================");
		System.out.println(xml);
		System.out.println("=======================xml end===================");
		SAXReader reader = new SAXReader();
		InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document document = reader.read(in);
		Element e = document.getRootElement();
		List<String> list = new ArrayList<String>();
		for (Iterator it = e.elementIterator(); it.hasNext();) {
			Element child = (Element) it.next();
			list.add(child.getStringValue());
		}
		System.out.println(list.toString());
		if (list.size() > 0) {
			return list.get(1);
		} else {
			return "";
		}

	}
}
