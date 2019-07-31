package com.wb.commonbase.util;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * 说明：数字工具类
 * <p/>
 * 作者：fanly
 * <p/>
 * 时间：2015/11/1 18:14
 * <p/>
 * 版本：verson 1.0
 */

public final class NumberUtils {

    /**
     * 说明：禁止实例化
     */
    private NumberUtils(){}

    private final static DecimalFormat decimalFormat = new DecimalFormat();

    private static DecimalFormat getDecimalFormat(){
        return decimalFormat;
    }

    /**
     * 说明：String转Int
     * @param str 目标String
     * @return int 转换失败返回-1
     */
    public final static int toInt(String str){
        return toInt(str,-1);
    }

    /**
     * 说明：String转Int
     * @param str 目标String
     * @param def 默认值
     * @return int
     */
    public final static int toInt(String str,int def){
        int num = def;
        try {
            if (!StringUtils.isEmpty(str)){
                num = Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
            Log.e("NumberUtils",str + " : 转 Interger 失败！");
        }
        return num;
    }

    /**
     * 说明：String转Double
     * @param str 目标String
     * @return
     */
    public final static double toDouble(String str){
        return toDouble(str,0.0d);
    }

    /**
     * 说明：String转Double
     * @param str 目标String
     * @param def 默认值
     * @return
     */
    public final static double toDouble(String str,double def){
        double num = def;
        try {
            if (!StringUtils.isEmpty(str)){
                num = Double.parseDouble(str);
            }else {
                num = 0.0d;
            }
        } catch (NumberFormatException e) {
            Log.e("NumberUtils",str + " : 转 Double 失败！");
        }
        return num;
    }

    /**
     * 说明：String转long
     * @param str
     * @return 转换异常返回 -1
     */
    public static long toLong(String str) {
        return toLong(str,-1L);
    }

    /**
     * 说明：String转long
     * @param str 目标String
     * @param def 默认值
     * @return
     */
    public static long toLong(String str,long def) {
        long num = def;
        try {
            if (!StringUtils.isEmpty(str)){
                num =  Long.parseLong(str);
            }
        } catch (NumberFormatException e) {
            Log.e("NumberUtils",str + " : 转 Double 失败！");
        }
        return num;
    }

    /**
     * 说明：字符串转布尔
     * @param str 目标String
     * @return
     */
    public static boolean toBool(String str) {
        return Boolean.parseBoolean(str);
    }

    /**
     * 说明：保留N位小数
     * @param d
     * @param n
     * @return
     */
    public final static String saveDecimal(double d,int n){
        StringBuilder sb = new StringBuilder("#0");
        if (n >= 1){
            sb.append(".");
            for (int i = 0 ;i < n;i++){
                sb.append("0");
            }
        }
        DecimalFormat formatter = new DecimalFormat();
        formatter.applyPattern(sb.toString());
        return formatter.format(d);
    }

    /**
     * 说明：减法
     * @param str1-str2
     * @return 返回double
     */
    public final static double subDouble(String str1,String str2){
        BigDecimal result = new BigDecimal("0");
        if (StringUtils.isNotEmpty(str1)){
            result = result.add(new BigDecimal(String.valueOf(toDouble(str1))));
        }
        if (StringUtils.isNotEmpty(str2)){
            result = result.subtract(new BigDecimal(String.valueOf(toDouble(str2))));
        }
        return result.doubleValue();
    }

    /**
     * 说明：累加方法
     * @param str
     * @return 返回double
     */
    public final static double addDouble(String...str){
        BigDecimal result = new BigDecimal("0");
        if (str != null && str.length > 0){
            for (int i = 0;i < str.length;i++){
                result = result.add(new BigDecimal(String.valueOf(toDouble(str[i]))));
            }
        }
        return result.doubleValue();
    }

    /**
     * 说明：累加方法
     * @param doubles
     * @return 返回double
     */
    public final static double addDouble(double...doubles){
        BigDecimal result = new BigDecimal("0");
        if (doubles != null && doubles.length > 0){
            for (int i = 0;i < doubles.length;i++){
                result = result.add(new BigDecimal(String.valueOf(doubles[i])));
            }
        }
        return result.doubleValue();
    }

    public final static double multipy(double...doubles){
        BigDecimal result = new BigDecimal("1");
        if (doubles != null && doubles.length > 0){
            for (int i = 0;i < doubles.length;i++){
                result = result.multiply(new BigDecimal(String.valueOf(doubles[i])));
            }
        }
        return result.doubleValue();
    }

    /**
     * 说明：相除
     * @param d1
     * @param d2
     * @return
     */
    public final static double divide(double d1,double d2,int roundingMode){
        if (d2 == 0){
            return 0;
        }else {
            BigDecimal b1 = new BigDecimal(d1);
            BigDecimal b2 = new BigDecimal(d2);
            return b1.divide(b2,roundingMode).doubleValue();
        }
    }

    /**
     * 说明：相除
     * @param d1
     * @param d2
     * @return
     */
    public final static double divide(double d1,double d2,RoundingMode roundingMode){
        if (d2 == 0){
            return 0;
        }else {
            BigDecimal b1 = new BigDecimal(d1);
            BigDecimal b2 = new BigDecimal(d2);
            return b1.divide(b2,roundingMode).doubleValue();
        }
    }

    /**
     * 说明：累加方法
     * @param str
     * @return 返回int
     */
    public final static int addInt(String...str){
        int total = 0;
        for (String s : str) {
            total += toInt(s);
        }
        return total;
    }

    /**
     * 说明：二进制转十进制
     * @param str 为只包含0，1的32位字符串，并且以0开头
     * @return 转换失败返回-1
     */
    public static String binToDec(String str){
        if (StringUtils.isEmpty(str)) {
            return "";
        }else if (str.length() < 32 || (str.length() == 32 && str.startsWith("0"))) {
            if (str.matches("[0-1;]+")) {
                return Integer.valueOf(str,2).toString();
            }else {
                Log.e("NumberUtils",str + "二进制转十进制出错：字符串不是二进制！！！");
                return "-1";
            }
        }else {
            Log.e("NumberUtils",str + "二进制转十进制出错：长度超出32位！！！");
            return "-1";
        }
    }

    /**
     * 说明：十进制转二进制
     * @param str
     * @return
     */
    public static String decToBin(String str){
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return Integer.toBinaryString(toInt(str));
    }

    /**
     * 说明：二进制转十六进制
     * @param str
     * @return 转换失败返回-1
     */
    public static String binToHex(String str){
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (str.matches("[0-1;]+")) {
            String dec = binToDec(str);
            return Integer.toHexString(Integer.parseInt(dec));
        }else {
            Log.e("NumberUtils",str + "二进制转十六进制：字符串不是二进制！！！");
            return "-1";
        }
    }

    /**
     * 说明：十六进制转二进制
     * @param str
     * @return 转换失败返回""
     */
    public static String hexToBin(String str){
        String result = "";
        if (!StringUtils.isEmpty(str)) {
            try {
                result = decToBin(Integer.valueOf(str,16).toString());
            } catch (NumberFormatException e) {
                Log.e("NumberUtils",str + "十六进制转二进制异常！！！");
            }
        }
        return result;
    }

    /**
     * 说明：十进制转十六进制
     * @param str
     * @return
     */
    public static String decToHex(String str){
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return Integer.toHexString(toInt(str));
    }

    /**
     * 说明：十六进制转十进制
     * @param str
     * @return 转换失败返回""
     */
    public static String hexToDec(String str){
        String result = "";
        if (!StringUtils.isEmpty(str)) {
            try {
                result = Integer.valueOf(str,16).toString();
            } catch (NumberFormatException e) {
                Log.e("NumberUtils",str + "十六进制转十进制异常！！！");
            }
        }
        return result;
    }

    /**
     * double->string
     * @param d
     * @return
     */
    public static String toString(double d){
        Double dou_obj = new Double(d);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(dou_obj);
    }
}