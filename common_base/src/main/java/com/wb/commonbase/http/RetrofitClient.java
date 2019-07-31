package com.wb.commonbase.http;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String API_HOST = "https://taotutu.jiayuonline.com/";
    private static RetrofitClient instance;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;
    static Context context;


    private RetrofitClient() {


//        ClearableCookieJar cookieJar =
//                new PersistentCookieJar(new SetCookieCache(),
//                        new SharedPrefsCookiePersistor(context));

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
//                .cookieJar(cookieJar)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .addInterceptor(InterceptorUtil.logInterceptor())
                .addInterceptor(InterceptorUtil.headerInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(FastJsonConverterFactory.create())   // 使用 fastjson 解析器解析 json
                .addConverterFactory(GsonConverterFactory.create())   // 使用 fastjson 解析器解析 json
                .client(okHttpClient)
                .build();

    }


    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }


    public static RetrofitClient getInstance(Context c ) {
         context = c;
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}