package com.mwmurawski.nutritioninfo.di.module;

import com.mwmurawski.nutritioninfo.data.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.di.ActivityScope;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @ActivityScope
    MainPresenter provideMainActivityPresenter(SearchRepository searchRepository){
        return new MainPresenter(searchRepository);
    }

    @Provides
    @ActivityScope
    FoodDetailsPresenter provideFoodDetailsPresenter(SearchRepository searchRepository){
        return new FoodDetailsPresenter(searchRepository);
    }

}
