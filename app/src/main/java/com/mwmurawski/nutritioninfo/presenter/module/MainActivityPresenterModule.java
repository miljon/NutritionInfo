package com.mwmurawski.nutritioninfo.presenter.module;


import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

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
}