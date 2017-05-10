package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.ApplicationModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.BaseBasePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BaseBasePresenter baseBasePresenter);
}
