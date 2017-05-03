package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.presenter.module.MainActivityPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {MainActivityPresenterModule.class})
public interface MainActivityPresenterComponent {
//    void inject(MainActivityView mainActivityView); //says where it will be injected

    MainActivityPresenter getPresenter();
}
