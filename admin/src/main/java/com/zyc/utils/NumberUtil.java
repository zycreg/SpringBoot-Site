package com.zyc.utils;

import java.text.DecimalFormat;

public class NumberUtil {

	public static String parseDouble(double scale, int fix) {
		String point = "";
		for(int i=0;i<fix;i++){
			point+="0";
		}
		DecimalFormat fnum = new DecimalFormat("##0."+point);
		return fnum.format(scale);
	}
}
