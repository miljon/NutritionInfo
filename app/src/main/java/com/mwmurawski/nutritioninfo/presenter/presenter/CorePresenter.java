package com.mwmurawski.nutritioninfo.presenter.presenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class CorePresenter {

    @Inject
    protected CompositeDisposable compositeDisposable;

    CompositeDisposable getCompositeDisposable(){
        return compositeDisposable;
    }

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }
}
