package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.module.ApplicationModule;
import com.mwmurawski.nutritioninfo.ui.base.PresenterCache;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    PresenterCache getPresenterCache();
}
