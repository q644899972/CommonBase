package com.wb.commonbase.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class InterceptorUtil {
    public static Interceptor headerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
//                builder
//                        .addHeader("access-token", "asdasdasdasdas");
//                        .addHeader("access-token", UserLoginManager.getInstance().getAccess_token())
//                        .addHeader("uid",UserLoginManager.getInstance().getUid());

                Request request = builder.build();
                return chain.proceed(request);
            }
        };
    }

    public static Interceptor logInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
