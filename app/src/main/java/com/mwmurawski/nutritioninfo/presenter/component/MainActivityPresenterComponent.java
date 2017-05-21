package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.CompositeDisposableModule;
import com.mwmurawski.nutritioninfo.presenter.module.NetworkModule;
import com.mwmurawski.nutritioninfo.presenter.module.RepositoryModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {NetworkModule.class, RepositoryModule.class,CompositeDisposableModule.class})
@Singleton
public interface MainActivityPresenterComponent {
    void inject(MainActivityPresenter mainActivityPresenter);
}
