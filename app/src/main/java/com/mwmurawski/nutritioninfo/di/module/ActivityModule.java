package com.mwmurawski.nutritioninfo.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.di.ActivityScope;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;
import com.mwmurawski.nutritioninfo.ui.fooddetails.adapter.ItemDetailsAdapter;
import com.mwmurawski.nutritioninfo.ui.fooddetails.adapter.ItemDetailsAdapterView;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapter;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapterView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    ItemAdapterView provideItemAdapterInterface(MainPresenter presenter) {
        return new ItemAdapter(presenter);
    }

    @Provides
    @ActivityScope
    ItemDetailsAdapterView provideItemDetailsAdapterInterface(FoodDetailsPresenter presenter) {
        return new ItemDetailsAdapter(new ArrayList<Nutrient>(), presenter);
    }
}
