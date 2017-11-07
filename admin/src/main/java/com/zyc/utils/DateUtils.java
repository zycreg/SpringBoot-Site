package com.zyc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Object unix2Datestr(Object object) {
		if(object != null){
			try {
				return sdf.format(object);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return object;
			}
		}
		return "";
	}
	
	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	public static Date getDate() {
		return new Date();
	}



	/**
	 * 日期转换为字符串
	 *
	 * @param date
	 *            日期
	 * @param date_sdf
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, SimpleDateFormat date_sdf) {
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}




    /**
     * 日期转换为字符串
     *
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return 字符串
     */
    public static String date2Str(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        return date2Str(date,new SimpleDateFormat(pattern));
    }


	/**
	 * 日期转换为字符串
	 *
	 * @param format
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String getDate(String format) {
		Date date=new Date();
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转换成日期
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static Date str2Date(String str, SimpleDateFormat sdf) {
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 字符串转换成日期
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date str2Date(String str, String format) {
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取时间间隔
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getInterval(String beginDate,String endDate){
		long interval = 0l;
		long lBegin = str2Date(beginDate, sdf).getTime();
		long lEnd= str2Date(endDate, sdf).getTime();
		interval = lEnd - lBegin;
		return interval;
	}
	
}
