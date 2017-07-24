package com.mwmurawski.nutritioninfo;

import com.google.gson.GsonBuilder;

import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkTestHelper {

    private MockWebServer mockWebServer;
    private Retrofit retrofit;

    public NetworkTestHelper() {
        mockWebServer = new MockWebServer();
        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public Retrofit provideRetrofit(){
        return retrofit;
    }

    public MockWebServer provideMwMockWebServer() {
        return mockWebServer;
    }
}
