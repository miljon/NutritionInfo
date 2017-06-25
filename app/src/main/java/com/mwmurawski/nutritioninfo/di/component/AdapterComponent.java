package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.di.module.CompositeDisposableModule;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CompositeDisposableModule.class})
public interface AdapterComponent {
    void inject(ItemAdapter itemAdapter);
}
