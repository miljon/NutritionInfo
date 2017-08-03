package com.mwmurawski.nutritioninfo;

import android.app.Application;

import com.mwmurawski.nutritioninfo.di.component.ApplicationComponent;
import com.mwmurawski.nutritioninfo.di.component.DaggerApplicationComponent;
import com.mwmurawski.nutritioninfo.di.module.ApplicationModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createComponent();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    public ApplicationComponent createComponent(){
        return DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();
    }
}
