package com.mwmurawski.nutritioninfo;

import android.app.Application;

import com.mwmurawski.nutritioninfo.di.component.ApplicationComponent;
import com.mwmurawski.nutritioninfo.di.component.DaggerApplicationComponent;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
//                .
                .build();
//        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
