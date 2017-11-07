package com.zyc.utils;

import java.util.List;

public class ListUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(123123123);
	}
	public static String join(List<String> list,String separator){
		String result = "";
		if ( list != null && !list.isEmpty()){
			StringBuffer sb = new StringBuffer();
			int i=0;
			for(;i<list.size()-1;i++){
				if( ! list.get(i).equals("") ){
					sb.append(list.get(i)+separator);
				}
			}
			sb.append(list.get(i));
			result =sb.toString();
			
		}else{
			result = "";
		}
		return result;
	}
}
