package com.mwmurawski.nutritioninfo.di.module;


import com.mwmurawski.nutritioninfo.di.ActivityScope;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapterView;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {

    @Provides
    @ActivityScope
    MainPresenter provideMainActivityPresenter(){
        return new MainPresenter();
    }

    @Provides
    @ActivityScope
    ItemAdapterView provideItemAdapterInterface(MainPresenter presenter){
        return new ItemAdapter(new ArrayList<SearchItem>(), presenter);
    }
}