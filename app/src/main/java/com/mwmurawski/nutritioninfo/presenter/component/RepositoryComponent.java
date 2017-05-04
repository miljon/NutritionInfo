package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.NetworkModule;
import com.mwmurawski.nutritioninfo.presenter.module.RepositoryModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, NetworkModule.class})
public interface RepositoryComponent {
    void inject(MainActivityPresenter mainActivityPresenter);
}
