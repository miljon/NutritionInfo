package com.mwmurawski.nutritioninfo.presenter.module;


import com.mwmurawski.nutritioninfo.cons.ActivityScope;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.ItemAdapterInterface;
import com.mwmurawski.nutritioninfo.view.recyclerview.ItemAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityPresenterModule {

    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(){
        return new MainActivityPresenter();
    }

    @Provides
    @ActivityScope
    ItemAdapterInterface provideItemAdapterInterface(MainActivityPresenter presenter){
        return new ItemAdapter(new ArrayList<SearchItem>(), presenter);
    }
}