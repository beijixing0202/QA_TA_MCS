package com.bill99.ate.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class BasicStringTools {

	private static String encode = "utf-8";

	// 找出中间string
	public static String saveParamLeftstrRightstr(String leftstr, String rightstr, byte[] responseBody) {
		byte[] left;
		byte[] right;
		byte[] content = null;
		int start;
		int end;
		try {
			left = leftstr.getBytes(encode);
			right = rightstr.getBytes(encode);
			for (int a = 0; a < (responseBody.length - left.length - right.length + 1); a++) {
				boolean result = true;
				for (int b = 0; b < left.length; b++) {
					if (responseBody[a + b] != left[b]) {
						result = false;
						break;
					}
				}

				if (result) {
					// 注意
					start = a + left.length;
					for (int a1 = start; a1 < (responseBody.length - right.length + 1); a1++) {
						boolean result2 = true;
						for (int b1 = 0; b1 < right.length; b1++) {
							if (responseBody[a1 + b1] != right[b1]) {
								result2 = false;
								break;
							}
						}
						if (result2) {
							end = a1 - 1;
							if (start > end) {
								return "";
							} else {
								content = new byte[end - start + 1];
								int j = 0;
								for (int a2 = start; a2 <= end; a2++) {
									content[j] = responseBody[a2];
									j++;
								}
								String collstr = new String(content, encode);

								return collstr;
							}
						}
					}
				}
			}
		} catch (UnsupportedEncodingException ex) {
			/* Reporter.log(ex.getMessage(), true); */
		}
		return "";
	}
	
	/**
	 * 
	 * @param group
	 * @return
	 * @Description:判断数组是否为空
	 */
	public static boolean isEmpty(String[] group){
		return (group == null || group.length==0);
	}
	
	/**
	 * 
	 * @param group
	 * @param element
	 * @return
	 * @Description:String数组中是否包含某字符串
	 */
	public static boolean containString(String[] group , String element){
		Arrays.sort(group);  
        int index = Arrays.binarySearch(group, element);  
        return ((index!=-1)?true:false);
	}

}
