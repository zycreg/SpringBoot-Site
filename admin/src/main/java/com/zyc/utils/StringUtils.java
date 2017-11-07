package com.zyc.utils;


import java.util.Map;

/**
 * 
 * @author Jerry Zhang Create date 2010-12-3
 * @version 1.0.0
 * @email jerry.zhang@jamboree.com.cn 字符串处理工具类
 */
public class StringUtils {
	public static final String STRING_EMPTIY = "";

	public static boolean isEmpty(String param) {
		if (param == null || param.equals(STRING_EMPTIY) || param.equals("null")) {
			return true;
		}
		return false;
	}

	public static String trim(String param) {
		if (!isEmpty(param)) {
			return param.trim();
		}
		return STRING_EMPTIY;
	}

	public static String getStringByMap(String key, Map map) {
		if (key != null && map != null) {
			return map.get(key) == null ? "" : map.get(key).toString();
		}
		return "";
	}

	public static String getStringByMap(String key, Map map, String defaultValue) {
		String returnValue = "";
		if (key != null && map != null) {
			returnValue = map.get(key) == null ? defaultValue : map.get(key).toString();
			if (returnValue.equals("")) {
				returnValue = defaultValue;
			}
		}
		return returnValue;
	}

	public static int getIntByMap(String key, Map map) {
		if (key != null && map != null) {
			return map.get(key) == null ? -1 : Integer.parseInt(map.get(key).toString());
		}
		return -1;
	}

	public static final String htmlToCode(String s) {
		if (s == null) {
			return "";
		} else {
			s = s.replace("\n\r", "<br>&nbsp;&nbsp;");
			s = s.replace("\r\n", "<br>&nbsp;&nbsp;");// 这才是正确的！
			s = s.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
			s = s.replace(" ", "&nbsp;");

			s = s.replace("\"", "\\" + "\"");// 如果原文含有双引号，这一句最关键！！！！！！
			return s;
		}
	}

	/**
	 * 将字符串数组转整形数组
	 */
	public static final int[] parseStringArrToIntArr(String[] array){
		int[] intArr = new int[array.length];
		for(int i = 0; i < array.length; i++){
			intArr[i] = Integer.parseInt(array[i]);
		}
		return intArr;
	}


}
