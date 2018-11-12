package com.bill99.fo.common.httpclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.bill99.fo.common.dto.InfsBalResData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




import shelper.iffixture.HttpFixture;

/**
 * 余额充值页面
 * @author lulu.yang
 *
 */

public class HttpInfsBalance {
	/**
	 * 修改人民币账户余额
	 * @param memberCode
	 */
	public void chargeMoneyRmb(String memberCode,String newBalance){
		HttpFixture hfFixture = new HttpFixture();
		//查询余额
		hfFixture.addHeaderValue("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");	
		hfFixture.setUrl("http://192.168.55.44:8100/infs/balance.htm?method=query");	
		String queryData ="{\"queryKey\":\"memberCode\",\"environment\":\"stage2\",\"queryValue\":\""+memberCode+"\"}";
		hfFixture.addParamValue("jsonData", queryData);

		hfFixture.Post();

		String resBody =  hfFixture.getResponseBody();
		
		int index = resBody.lastIndexOf("resultData\":") + "resultData\":".length();

		String modifyData = resBody.substring(index, resBody.length()-1);
			    
		//将字符串转化成json对象
		JSONArray jsonobj=JSONArray.fromObject(modifyData);		
		InfsBalResData[] inf=(InfsBalResData[])JSONArray.toArray(jsonobj,InfsBalResData.class);
		inf[0].setBalance(newBalance);
				
		JSONArray out = JSONArray.fromObject(inf);
		System.out.println(out.toString());
		String postDataAfrModifyBal = out.toString();
		
		//修改余额
		hfFixture.nextRequest();
		hfFixture.setUrl("http://192.168.55.44:8100/infs/balance.htm?method=change");
		hfFixture.addParamValue("jsonData", postDataAfrModifyBal);
		hfFixture.Post();
	}
}
