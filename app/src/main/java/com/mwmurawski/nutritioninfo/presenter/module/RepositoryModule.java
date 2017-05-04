package com.mwmurawski.nutritioninfo.presenter.module;

import com.mwmurawski.nutritioninfo.model.repository.SearchRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RepositoryModule {

    @Singleton
    @Provides SearchRepository provideSearchRepository(Retrofit retrofit){
        return new SearchRepository(retrofit);
    }
}
