package com.zyc.utils;

import java.math.BigDecimal;

public class Arith {
    private static final int DEF_DIV_SCALE = 10;
    
    /**
     * 两个Double数相�?
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double add(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }
    
    /**
     * 两个Double数相�?
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double sub(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }
    
    /**
     * 两个Double数相�?
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double mul(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }
    
    /**
     * 两个Double数相�?
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double div(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 两个Double数相除，并保留scale位小�?
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    public static Double div(String v1,String v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

} 