package com.signalpancakeswap.signalpancakeswap.WebService;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient  {
    
    public static final String BASE_URL = "https://coinbox-pro.com/server/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {

            Request newRequest  = chain.request().newBuilder()
                    .build();

            return chain.proceed(newRequest);
        })
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
