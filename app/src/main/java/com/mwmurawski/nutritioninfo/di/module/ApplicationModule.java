package com.mwmurawski.nutritioninfo.di.module;


import com.mwmurawski.nutritioninfo.ui.base.PresenterCache;

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
