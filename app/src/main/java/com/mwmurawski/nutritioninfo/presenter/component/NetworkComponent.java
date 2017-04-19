package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.NetworkModule;
import com.mwmurawski.nutritioninfo.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivity mainActivity); //says where it will be injected
}
