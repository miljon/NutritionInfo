package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.cons.ActivityScope;
import com.mwmurawski.nutritioninfo.presenter.component.custom.PresenterProviderInterface;
import com.mwmurawski.nutritioninfo.presenter.module.FoodDetailsPresenterModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.FoodDetailsPresenter;
import com.mwmurawski.nutritioninfo.view.activity.FoodDetailsActivity;

import dagger.Component;

@Component(
        dependencies = {
                ApplicationComponent.class},
        modules = {
                FoodDetailsPresenterModule.class})
@ActivityScope
public interface FoodDetailsActivityComponent extends PresenterProviderInterface<FoodDetailsPresenter> {
    void inject(FoodDetailsActivity foodDetailsActivity);
}