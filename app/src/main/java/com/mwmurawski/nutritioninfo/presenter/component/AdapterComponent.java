package com.mwmurawski.nutritioninfo.presenter.component;

import com.mwmurawski.nutritioninfo.presenter.module.CompositeDisposableModule;
import com.mwmurawski.nutritioninfo.view.recyclerview.ItemAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CompositeDisposableModule.class})
public interface AdapterComponent {
    void inject(ItemAdapter itemAdapter);
}
