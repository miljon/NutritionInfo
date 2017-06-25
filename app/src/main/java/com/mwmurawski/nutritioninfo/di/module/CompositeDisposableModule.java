package com.mwmurawski.nutritioninfo.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class CompositeDisposableModule {

    @Singleton
    @Provides CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }
}
