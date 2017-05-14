package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.cons.ActivityScope;
import com.mwmurawski.nutritioninfo.presenter.component.custom.PresenterProviderInterface;
import com.mwmurawski.nutritioninfo.presenter.module.MainActivityPresenterModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.activity.MainActivity;

import dagger.Component;


@Component(
        dependencies = {
                ApplicationComponent.class},
        modules = {
                MainActivityPresenterModule.class})
@ActivityScope
public interface MainActivityComponent extends PresenterProviderInterface<MainActivityPresenter> {

    void inject(MainActivity mainActivity);
}
