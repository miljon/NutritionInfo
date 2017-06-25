package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.ActivityScope;
import com.mwmurawski.nutritioninfo.di.component.custom.PresenterProviderInterface;
import com.mwmurawski.nutritioninfo.di.module.MainPresenterModule;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;
import com.mwmurawski.nutritioninfo.ui.main.MainActivity;

import dagger.Component;


@Component(
        dependencies = {
                ApplicationComponent.class},
        modules = {
                MainPresenterModule.class})
@ActivityScope
public interface MainComponent extends PresenterProviderInterface<MainPresenter> {

    void inject(MainActivity mainActivity);
}
