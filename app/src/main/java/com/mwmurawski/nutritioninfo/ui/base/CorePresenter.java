package com.mwmurawski.nutritioninfo.ui.base;

import com.mwmurawski.nutritioninfo.di.component.ApplicationComponent;
import com.mwmurawski.nutritioninfo.di.component.DaggerApplicationComponent;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public abstract class CorePresenter {

    private ApplicationComponent applicationComponent;

    private CompositeDisposable compositeDisposable;
    private SchedulerProvider schedulerProvider;

    public CorePresenter() {
        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.inject(this);

        setSchedulerProvider(applicationComponent.getSchedulerProvider());
        setCompositeDisposable(applicationComponent.getCompositeDisposable());
    }

    protected CompositeDisposable getCompositeDisposable(){
        return compositeDisposable;
    }

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public void setSchedulerProvider(SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
