package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.ActivityScope;
import com.mwmurawski.nutritioninfo.di.component.custom.PresenterProviderInterface;
import com.mwmurawski.nutritioninfo.di.module.FoodDetailsPresenterModule;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsActivity;

import dagger.Component;

@Component(
        dependencies = {
                ApplicationComponent.class},
        modules = {
                FoodDetailsPresenterModule.class})
@ActivityScope
public interface FoodDetailsComponent extends PresenterProviderInterface<FoodDetailsPresenter> {
    void inject(FoodDetailsActivity foodDetailsActivity);
}