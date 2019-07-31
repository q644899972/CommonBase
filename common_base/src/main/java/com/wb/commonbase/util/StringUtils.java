package com.wb.commonbase.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.regex.Pattern;


/**
 * 说明：字符串工具类
 * <p/>
 * 作者：fanly
 * <p/>
 * 时间：2015/10/28 21:26
 * <p/>
 * 版本：verson 1.0
 */

public final class StringUtils {

    private final static String                 emailer   = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    private final static String                 phoner    = "1\\d{10}$";
    private final static HashSet<String> sPhoneSet = new HashSet<>();

    static {
        sPhoneSet.add("134");
        sPhoneSet.add("135");
        sPhoneSet.add("136");
        sPhoneSet.add("137");
        sPhoneSet.add("138");
        sPhoneSet.add("139");
        sPhoneSet.add("144");
        sPhoneSet.add("147");
        sPhoneSet.add("148");
        sPhoneSet.add("150");
        sPhoneSet.add("151");
        sPhoneSet.add("152");
        sPhoneSet.add("157");
        sPhoneSet.add("158");
        sPhoneSet.add("159");
        sPhoneSet.add("170");
        sPhoneSet.add("178");
        sPhoneSet.add("182");
        sPhoneSet.add("183");
        sPhoneSet.add("184");
        sPhoneSet.add("187");
        sPhoneSet.add("188");
        sPhoneSet.add("198");

        sPhoneSet.add("130");
        sPhoneSet.add("131");
        sPhoneSet.add("132");
        sPhoneSet.add("155");
        sPhoneSet.add("156");
        sPhoneSet.add("185");
        sPhoneSet.add("186");
        sPhoneSet.add("145");
        sPhoneSet.add("146");
        sPhoneSet.add("166");
        sPhoneSet.add("167");
        sPhoneSet.add("175");
        sPhoneSet.add("176");
        sPhoneSet.add("171");

        sPhoneSet.add("133");
        sPhoneSet.add("153");
        sPhoneSet.add("177");
        sPhoneSet.add("180");
        sPhoneSet.add("181");
        sPhoneSet.add("189");
        sPhoneSet.add("191");
        sPhoneSet.add("199");
        sPhoneSet.add("141");
        sPhoneSet.add("174");
    }

    /**
     * 说明：判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotNull(Object object){ return object != null;}

    public static boolean isNotEmpty(CharSequence input){
        return !isEmpty(input);
    }

    /**
     * 说明：判断多个定字符串是否空
     *      全为null或"",返回true
     *      否则返回false
     */
    public static boolean isEmpty(CharSequence ...input) {
        boolean flag = true;
        if (input != null){
            for (CharSequence c:input){
                if (!isEmpty(c)){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 说明：判断两个字符串是否相等
     * @param a
     * @param b
     * @return
     */
    public static boolean isEquals(CharSequence a,CharSequence b){
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 说明：比较字符串是否相等（忽视大小写）
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqualsIgnoreCase(String a,String b){
        if (a == b) return true;
        if (a != null && b != null) {
            return a.equalsIgnoreCase(b);
        }
        return false;
    }

    /**
     * 说明：判定是否符合
     * @param pattern 正则表达式
     * @param str 验证的字符串
     * @return
     */
    public static boolean matches(String pattern,CharSequence str){
        if (isEmpty(str) || isEmpty(str)){
            return false;
        }
        Pattern p = Pattern.compile(pattern);
        return p.matcher(str).matches();
    }

    /**
     * 说明：判断是不是一个合法的电子邮件地址（自定义规则）
     * @param pattern
     * @param email
     * @return
     */
    public static boolean isEmail(String pattern,CharSequence email) {
        return matches(pattern,email);
    }

    /**
     * 说明：判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(CharSequence email) {
        return isEmail(emailer, email);
    }

    /**
     * 说明：判断是不是一个合法的手机号码
     */
    public static boolean isPhone(CharSequence phoneNum) {
        boolean result = isPhone(phoner,phoneNum);
        if (result){
            String pre = phoneNum.subSequence(0,3).toString();
            if (sPhoneSet.contains(pre)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 说明：判断是不是一个合法的手机号码(自定义规则)
     */
    public static boolean isPhone(String pattern,CharSequence phoneNum) {
        return matches(pattern,phoneNum);
    }

    /**
     * 说明：过滤字符串中Emoji表情
     * @param src 源字符串
     * @return 过滤后字符串
     */
    public static String emojiFilter(String src){
        if (isEmpty(src)) {
            return "";
        }else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < src.codePointCount(0, src.length()); i++) {
                int point = src.codePointAt(i);
                if (!isEmojiCharacter(point)) {
                    sb.append((char)point);
                }
            }
            return sb.toString();
        }
    }

    /**
     * 说明：判读字符是否为Emoji表情
     * @param codePoint
     * @return false:不是，true:是
     */
    public static boolean isEmojiCharacter(int codePoint) {
        return (codePoint >= 0x2600 && codePoint <= 0x27BF)
                || codePoint == 0x303D
                || codePoint == 0x2049
                || codePoint == 0x203C
                || (codePoint >= 0x3200 && codePoint <= 0x32FF)
                || (codePoint >= 0x2100 && codePoint <= 0x214F)
                || (codePoint >= 0x2B00 && codePoint <= 0x23FF)
                || (codePoint >= 0x2900 && codePoint <= 0x297F)
                || codePoint >= 0x10000;
    }

    /**
     * 说明：生成32为包含数字和字母的唯一UUID字符串
     * @return 32位长度
     */
    public static String UUID(){
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 说明：Encode编码
     * @param sign
     * @return
     */
    public static String utfEncode(String sign){
        String result = "";
        try {
            if (!isEmpty(sign)){
                result = URLEncoder.encode(sign,"UTF-8");
            }
        }catch (UnsupportedEncodingException e){
            Log.e("StringUtils",e.toString());
        }
        return result;
    }
}

