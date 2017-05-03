package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.MainActivityPresenterModule;
import com.mwmurawski.nutritioninfo.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {MainActivityPresenterModule.class})
public interface MainActivityPresenterComponent {

    void inject(MainActivity mainActivity);
}
