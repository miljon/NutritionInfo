package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.module.CompositeDisposableModule;
import com.mwmurawski.nutritioninfo.di.module.NetworkModule;
import com.mwmurawski.nutritioninfo.di.module.RepositoryModule;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {NetworkModule.class, RepositoryModule.class, CompositeDisposableModule.class})
@Singleton
public interface MainPresenterComponent {
    void inject(MainPresenter mainPresenter);
}
