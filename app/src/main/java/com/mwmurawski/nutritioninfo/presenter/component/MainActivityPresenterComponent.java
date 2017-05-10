package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.component.custom.PresenterComponent;
import com.mwmurawski.nutritioninfo.presenter.module.MainActivityPresenterModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {MainActivityPresenterModule.class})
public interface MainActivityPresenterComponent extends PresenterComponent<MainActivityPresenter> {

    void inject(MainActivity mainActivity);
}
