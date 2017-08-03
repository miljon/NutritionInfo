package com.mwmurawski.nutritioninfo.di.module;


import com.mwmurawski.nutritioninfo.di.ApplicationScope;
import com.mwmurawski.nutritioninfo.ui.base.PresenterCache;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.AppSchedulerProvider;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    @ApplicationScope
    @Provides
    PresenterCache providePresenterCache() {
        return new PresenterCache();
    }

    @ApplicationScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @ApplicationScope
    @Provides
    SchedulerProvider provideScheduler() {
        return new AppSchedulerProvider();
    }
}
