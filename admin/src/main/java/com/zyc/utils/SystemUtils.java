package com.zyc.utils;

import java.util.UUID;


public class SystemUtils {
	public final static String adminUserName = "administrator";
	public final static String adminRoleName = "超级管理员";
	private static Boolean isTest = false;
	
	public static Boolean getIsTest() {
		return isTest;
	}

	public static void setIsTest(Boolean isTest) {
		SystemUtils.isTest = isTest;
	}

//	public static void assertTrue(boolean value, int errorCode, String message) {
//		if (value == false) {
//			if (errorCode > 1000) {
//				throw new ErrorException(errorCode, message);
//			} else {
//				throw new InfoException(errorCode, message);
//			}
//		}
//	}

	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    }  
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
	
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	public static boolean isInteger(String str) {
		return str.matches("^(-?[1-9]\\d{0,9}|0)$");
	}
}
