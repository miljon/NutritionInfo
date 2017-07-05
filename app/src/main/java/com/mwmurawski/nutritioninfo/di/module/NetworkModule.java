package com.mwmurawski.nutritioninfo.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mwmurawski.nutritioninfo.data.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.di.ApplicationScope;
import com.mwmurawski.nutritioninfo.utils.AppConstants;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @ApplicationScope
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @ApplicationScope
    @Provides
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @ApplicationScope
    @Provides
    SearchRepository provideSearchRepository(Retrofit retrofit){
        return new SearchRepository(retrofit);
    }
}
