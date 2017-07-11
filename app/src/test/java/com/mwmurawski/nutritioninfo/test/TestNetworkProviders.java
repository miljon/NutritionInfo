package com.mwmurawski.nutritioninfo.test;

import com.google.gson.Gson;

import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestNetworkProviders {

    private static Gson getGson(){
        return new Gson();
    }

    public static Retrofit getRetrofit(MockWebServer server){
        return new Retrofit.Builder()
                .baseUrl(server.url("").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }
}
