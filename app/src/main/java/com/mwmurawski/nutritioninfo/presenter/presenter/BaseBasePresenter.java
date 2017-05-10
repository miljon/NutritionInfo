package com.mwmurawski.nutritioninfo.presenter.presenter;

import com.mwmurawski.nutritioninfo.cons.PresenterCache;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerApplicationComponent;

import javax.inject.Inject;

/**
 * Need that class, becouse Dagger has a problem with injecting into class with generics.
 * PresenterCache is injected here.
 */
public abstract class BaseBasePresenter {

    @Inject protected PresenterCache presenterCache;
//    @Inject protected CompositeDisposable compositeDisposable;

    public BaseBasePresenter() {
        DaggerApplicationComponent.builder().build().inject(this);
//        DaggerApplicationComponent.builder().build().inject(this);
    }
}
