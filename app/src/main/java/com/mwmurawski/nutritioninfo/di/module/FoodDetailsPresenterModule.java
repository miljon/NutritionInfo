package com.mwmurawski.nutritioninfo.di.module;

import com.mwmurawski.nutritioninfo.di.ActivityScope;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;
import com.mwmurawski.nutritioninfo.ui.fooddetails.adapter.ItemDetailsAdapterView;
import com.mwmurawski.nutritioninfo.ui.fooddetails.adapter.ItemDetailsAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class FoodDetailsPresenterModule {
    @Provides
    @ActivityScope
    FoodDetailsPresenter provideFoodDetailsPresenter(){
        return new FoodDetailsPresenter();
    }

    @Provides
    @ActivityScope
    ItemDetailsAdapterView provideItemDetailsAdapterInterface(FoodDetailsPresenter presenter){
        return new ItemDetailsAdapter(new ArrayList<Nutrient>(), presenter);
    }
}
