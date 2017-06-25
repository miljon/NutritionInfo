package com.mwmurawski.nutritioninfo.ui.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class CorePresenter {

    @Inject
    protected CompositeDisposable compositeDisposable;

    protected CompositeDisposable getCompositeDisposable(){
        return compositeDisposable;
    }

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }
}
