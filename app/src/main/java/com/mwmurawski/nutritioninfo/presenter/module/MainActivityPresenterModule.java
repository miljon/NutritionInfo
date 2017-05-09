package com.mwmurawski.nutritioninfo.presenter.module;


import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.ItemAdapterInterface;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;
import com.mwmurawski.nutritioninfo.view.recyclerview.ItemAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityPresenterModule {

    private MainActivityView mainActivityView;

    public MainActivityPresenterModule(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    @Provides
    @Singleton
    MainActivityPresenter provideMainActivityPresenter(){
        return new MainActivityPresenter(mainActivityView);
    }

    @Provides
    @Singleton ItemAdapterInterface provideItemAdapterInterface(MainActivityPresenter presenter){
        return new ItemAdapter(new ArrayList<SearchItem>(), presenter);
    }
}