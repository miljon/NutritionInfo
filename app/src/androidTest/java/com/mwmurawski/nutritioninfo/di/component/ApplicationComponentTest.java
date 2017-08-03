package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.ApplicationScope;
import com.mwmurawski.nutritioninfo.di.module.ApplicationModule;
import com.mwmurawski.nutritioninfo.di.module.NetworkModule;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsActivityTest;
import com.mwmurawski.nutritioninfo.ui.main.MainActivityTest;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponentTest extends ApplicationComponent {

    void inject(MainActivityTest activity);
    void inject(FoodDetailsActivityTest activity);
}
