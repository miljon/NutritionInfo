package com.mwmurawski.nutritioninfo.presenter.module;

import com.mwmurawski.nutritioninfo.cons.ActivityScope;
import com.mwmurawski.nutritioninfo.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.presenter.presenter.FoodDetailsPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.ItemDetailsAdapterView;
import com.mwmurawski.nutritioninfo.view.recyclerview.ItemDetailsAdapter;

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
