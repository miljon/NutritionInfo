package com.mwmurawski.nutritioninfo.component;

import com.mwmurawski.nutritioninfo.activity.MainActivity;
import com.mwmurawski.nutritioninfo.config.NetworkModule;

import dagger.Component;

@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivity mainActivity); //says where it will be injected
}
