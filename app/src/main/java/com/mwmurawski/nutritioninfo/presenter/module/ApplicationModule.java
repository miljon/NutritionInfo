package com.mwmurawski.nutritioninfo.presenter.module;

import com.mwmurawski.nutritioninfo.cons.PresenterCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    PresenterCache providePresenterCache() {
        return new PresenterCache();
    }
}
