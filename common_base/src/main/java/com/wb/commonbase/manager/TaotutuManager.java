package com.wb.commonbase.manager;

import android.content.Context;

import com.wb.commonbase.http.ExceptionHandler;
import com.wb.commonbase.http.RetrofitClient;
import com.wb.commonbase.util.SharedPreferenceUtil;

public class TaotutuManager {

    static Context context;
    static String token ;
    static String uid;

    public static void  init(Context c){
        context  = c;
        RetrofitClient.getInstance(context);
        SharedPreferenceUtil.init(context);
        ExceptionHandler.init(context);
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TaotutuManager.token = token;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        TaotutuManager.uid = uid;
    }
}
