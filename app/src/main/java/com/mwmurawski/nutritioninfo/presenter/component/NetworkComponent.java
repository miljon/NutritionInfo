package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.presenter.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivityPresenter mainActivityPresenter); //says where it will be injected
}
