package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.CompositeDisposableModule;
import com.mwmurawski.nutritioninfo.presenter.module.NetworkModule;
import com.mwmurawski.nutritioninfo.presenter.module.RepositoryModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.FoodDetailsPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class,CompositeDisposableModule.class})
public interface FoodDetailsPresenterComponent {
    void inject(FoodDetailsPresenter foodDetailsPresenter);
}
