package com.mwmurawski.nutritioninfo.di.module;

import com.mwmurawski.nutritioninfo.utils.rx.schedulers.SchedulerProvider;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.TestSchedulerProvider;

import io.reactivex.schedulers.TestScheduler;

public class ApplicationModuleTest extends ApplicationModule {

    @Override
    SchedulerProvider provideScheduler() {
        return new TestSchedulerProvider(new TestScheduler());
    }
}
