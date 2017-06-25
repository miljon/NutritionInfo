package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.module.CompositeDisposableModule;
import com.mwmurawski.nutritioninfo.di.module.NetworkModule;
import com.mwmurawski.nutritioninfo.di.module.RepositoryModule;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class,CompositeDisposableModule.class})
public interface FoodDetailsPresenterComponent {
    void inject(FoodDetailsPresenter foodDetailsPresenter);
}
