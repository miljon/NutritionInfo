package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.ActivityScope;
import com.mwmurawski.nutritioninfo.di.module.ActivityModule;
import com.mwmurawski.nutritioninfo.di.module.PresenterModule;
import com.mwmurawski.nutritioninfo.ui.base.PresenterCache;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsActivity;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;
import com.mwmurawski.nutritioninfo.ui.fooddetails.adapter.ItemDetailsAdapter;
import com.mwmurawski.nutritioninfo.ui.fooddetails.adapter.ItemDetailsAdapterView;
import com.mwmurawski.nutritioninfo.ui.main.MainActivity;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapter;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapterView;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class, PresenterModule.class})
public interface ActivityComponent {

    //activities
    void inject(MainActivity activity);
    void inject(FoodDetailsActivity activity);

    //adapters
    void inject(ItemAdapter itemAdapter);
    void inject(ItemDetailsAdapter itemDetailsAdapter);

    MainPresenter getMainPresenter();
    FoodDetailsPresenter getFoodDetailsPresenter();

    ItemAdapterView getItemAdapter();
    ItemDetailsAdapterView getIDetailsAdapter();

    PresenterCache getPresenterCache();
}
