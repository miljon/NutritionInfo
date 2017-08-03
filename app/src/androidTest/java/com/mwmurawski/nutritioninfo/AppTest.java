package com.mwmurawski.nutritioninfo;

import com.mwmurawski.nutritioninfo.di.component.ApplicationComponentTest;
import com.mwmurawski.nutritioninfo.di.component.DaggerApplicationComponentTest;
import com.mwmurawski.nutritioninfo.di.module.ApplicationModuleTest;

public class AppTest extends App {

    @Override
    public ApplicationComponentTest createComponent() {
        return DaggerApplicationComponentTest.builder()
                .applicationModule(new ApplicationModuleTest())
                .build();
    }
}
