package com.mwmurawski.nutritioninfo;

import android.app.Application;

import com.mwmurawski.nutritioninfo.presenter.component.ApplicationComponent;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerApplicationComponent;
import com.mwmurawski.nutritioninfo.presenter.module.ApplicationModule;


public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
