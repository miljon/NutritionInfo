package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.cons.PresenterCache;
import com.mwmurawski.nutritioninfo.presenter.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
//    void inject(App app);
    PresenterCache getPresenterCache();
}
