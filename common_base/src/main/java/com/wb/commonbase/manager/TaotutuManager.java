package com.wb.commonbase.manager;

import android.content.Context;

import com.wb.commonbase.http.ExceptionHandler;
import com.wb.commonbase.http.RetrofitClient;
import com.wb.commonbase.util.SharedPreferenceUtil;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;

public class TaotutuManager {

    static Context context;
    static String accessSecret;
    static String accessKey;

    public static void  init(Context c){

        context  = c;
        RetrofitClient.getInstance(context);
        SharedPreferenceUtil.init(context);
        ExceptionHandler.init(context);
    }




    public static String getAccessSecret() {
        return accessSecret;
    }

    public static void setAccessSecret(String accessSecret) {
        TaotutuManager.accessSecret = accessSecret;
    }

    public static String getAccessKey() {
        return accessKey;
    }

    public static void setAccessKey(String accessKey) {
        TaotutuManager.accessKey = accessKey;
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static HashMap<String,String> getParam(HashMap<String,String> params){

        String timestamp = String.valueOf(System.currentTimeMillis());

//          accessSecret = "56cbaf232dbd48d9b6b7b88760b5784e";
//          accessKey = "7b51de35ba4f465a81c48af594df38e1";

        params.put("timestamp",timestamp);
        params.put("accessSecret",accessSecret);

        Object[] keys = params.keySet().toArray();
        Arrays.sort(keys);

        StringBuilder sb = new StringBuilder();
        for(Object key:keys) {
            sb.append(key + "=" + params.get(key) + "&");
        }
        sb.append("accessKey=" + accessKey);

        String sign = MD5Encode(sb.toString(),"");
        sign =  MD5Encode(sign,"");
        sign =  MD5Encode(sign,"");

        params.put("sign",sign);

        return params;
    }
}
