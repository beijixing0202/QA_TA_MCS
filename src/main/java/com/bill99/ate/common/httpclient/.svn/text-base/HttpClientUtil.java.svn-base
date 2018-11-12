package com.bill99.ate.common.httpclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
	private static final Logger logger = Logger.getLogger(HttpClientUtil.class);
	public static final String Accept = "image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*";
	public static final String Accept_Encoding = "gzip, deflate";
	public static final String Accept_Language = "zh-cn";
	public static final String Cache_Control = "no-cache";
	public static final String Connection = "Keep-Alive";
	public static final String Content_Type = "application/x-www-form-urlencoded; charset=UTF-8";
	public static final String Content_Type_Get = "application/x-www-form-urlencoded; charset=UTF-8";

	public static final String User_Agent = "Mozilla/4.0 (compat ible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E";
	public static final int timeout = 60000;
	public static String charset = "UTF-8";
	private static CloseableHttpClient httpClient = null;

	public static String doPost(String url, Map<String, String> map, Map<String, String> headers, HttpContext context)
			throws Exception {
		logger.debug("请求URL:" + url);
		logger.debug("请求参数:" + JSONObject.toJSONString(map));
		logger.debug("请求头：" + headers);
		HttpPost httpPost = null;
		String result = null;
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		try {
			if (httpClient == null) {
				httpClient = SSLClient.getHttpClient();
			}
			httpPost = new HttpPost(url);
			if (!headers.containsKey("Accept"))
				httpPost.addHeader("Accept", Accept);
			// if(!headers.containsKey("Accept-Encoding"))
			// httpPost.addHeader("Accept-Encoding",Accept_Encoding);
			if (!headers.containsKey("Accept-Language"))
				httpPost.addHeader("Accept-Language", Accept_Language);
			if (!headers.containsKey("Cache-Control"))
				httpPost.addHeader("Cache-Control", Cache_Control);
			if (!headers.containsKey("Connection"))
				httpPost.addHeader("Connection", Connection);
			if (!headers.containsKey("Content-Type"))
				httpPost.addHeader("Content-Type", Content_Type);
			if (!headers.containsKey("User-Agent"))
				httpPost.addHeader("User-Agent", User_Agent);
			for (String header : headers.keySet()) {
				httpPost.addHeader(header, headers.get(header).toString());
			}
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse response = httpClient.execute(httpPost, context);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = getEntityStr(resEntity, charset);
				}
			}
			;

		} catch (Exception ex) {
			throw ex;
		} 
		return result;
	}

	public static String doGet(String url, Map<String, String> headers, HttpContext context) throws Exception {
		logger.debug("请求URL:" + url);
		logger.debug("请求头：" + headers);
		HttpGet httpGet = null;
		String result = null;
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		try {
			if (httpClient == null) {
				httpClient = SSLClient.getHttpClient();
			}
			httpGet = new HttpGet(url);
			if (!headers.containsKey("Accept"))
				httpGet.addHeader("Accept", Accept);
			if (!headers.containsKey("Accept-Language"))
				httpGet.addHeader("Accept-Language", Accept_Language);
			if (!headers.containsKey("Cache-Control"))
				httpGet.addHeader("Cache-Control", Cache_Control);
			if (!headers.containsKey("Connection"))
				httpGet.addHeader("Connection", Connection);
			if (!headers.containsKey("Content-Type"))
				httpGet.addHeader("Content-Type", Content_Type_Get);
			if (!headers.containsKey("User-Agent"))
				httpGet.addHeader("User-Agent", User_Agent);

			for (String header : headers.keySet()) {
				httpGet.addHeader(header, headers.get(header));
			}
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
			httpGet.setConfig(requestConfig);
			HttpResponse response = httpClient.execute(httpGet, context);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = getEntityStr(resEntity, "gb2312");

				}
			}
		} catch (Exception ex) {
			throw ex;
		} 
		return result;
	}

	public static byte[] doGetBackEntity(String url, Map<String, String> headers, HttpContext context)
			throws Exception {
		logger.debug("请求URL:" + url);
		logger.debug("请求头：" + headers);
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		try {
			httpClient = SSLClient.getHttpClient();
			httpGet = new HttpGet(url);
			if (!headers.containsKey("Accept"))
				httpGet.addHeader("Accept", Accept);
			if (!headers.containsKey("Accept-Language"))
				httpGet.addHeader("Accept-Language", Accept_Language);
			if (!headers.containsKey("Cache-Control"))
				httpGet.addHeader("Cache-Control", Cache_Control);
			if (!headers.containsKey("Connection"))
				httpGet.addHeader("Connection", Connection);
			if (!headers.containsKey("Content-Type"))
				httpGet.addHeader("Content-Type", Content_Type_Get);
			if (!headers.containsKey("User-Agent"))
				httpGet.addHeader("User-Agent", User_Agent);

			for (String header : headers.keySet()) {
				httpGet.addHeader(header, headers.get(header));
			}
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
			httpGet.setConfig(requestConfig);

			HttpResponse response = httpClient.execute(httpGet, context);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					byte[] b = new byte[resEntity.getContent().available()];
					resEntity.getContent().read(b);
					resEntity.getContent().close();
					return b;
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
		return null;
	}

	public static String getEntityStr(HttpEntity entity, String defaultCharset)
			throws IllegalStateException, IOException {
		String result = "";
		InputStream instream = entity.getContent();
		if (instream == null) {
			return null;
		}
		//保存流数据
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer1 = new byte[1024];
		int len;
		while ((len = instream.read(buffer1)) > -1) {
			baos.write(buffer1, 0, len);
		}
		baos.flush();
		try {
			Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
					"HTTP entity too large to be buffered in memory");
			int i = (int) entity.getContentLength();
			if (i < 0) {
				i = 4096;
			}
			Charset charset = null;
			try {
				ContentType contentType = ContentType.get(entity);
				if (contentType != null) {
					charset = contentType.getCharset();
				}
			} catch (UnsupportedCharsetException ex) {
				throw new UnsupportedEncodingException(ex.getMessage());
			}
			if (charset == null) {
				charset = Charset.forName(defaultCharset);
			}
			if (charset == null) {
				charset = HTTP.DEF_CONTENT_CHARSET;
			}
			Reader reader = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), charset);
			CharArrayBuffer buffer = new CharArrayBuffer(i);
			char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			result = buffer.toString();
			Document document = Jsoup.parse(result);
			if (document != null) {
				Elements content = document.select("meta[http-equiv=Content-Type]");
				if (content == null) {
					content = document.select("meta[http-equiv=content-type]");
				}
				if (content != null) {
					String contentStr = content.attr("content");
					String charsetTemp = "";
					if (StringUtil.isNotBlank(contentStr)) {
						Pattern pattern = Pattern.compile(".*charset=((\\w|-){2,10}).*");
						Matcher matcher = pattern.matcher(contentStr.toLowerCase());
						while (matcher.find()) {
							if (matcher.group(1) != null)
								charsetTemp = matcher.group(1);
						}
						String orgChareset = charset.displayName().toLowerCase();
						if (StringUtil.isNotBlank(charsetTemp) && !orgChareset.equals(charsetTemp)) {
							reader = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), charsetTemp);
							buffer = new CharArrayBuffer(i);
							tmp = new char[1024];
							while ((l = reader.read(tmp)) != -1) {
								buffer.append(tmp, 0, l);
							}
							result = buffer.toString();
						}
					}
				}
			}
			return result;
		} finally {
			instream.close();
			baos.close();
		}
	}

	public static CloseableHttpClient getHttpClient() {
		return httpClient;
	}
	
	public static void closeHttpClient(){
		try {
			httpClient.close();
			httpClient = null;
			System.out.println("关闭httpClient");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("关闭httpClient失败");
			e.printStackTrace();
		}
	}
	
	

}