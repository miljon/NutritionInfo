package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.RepositoryModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.presenter.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface MainActivityPresenterComponent {
    void inject(MainActivityPresenter mainActivityPresenter);
}
