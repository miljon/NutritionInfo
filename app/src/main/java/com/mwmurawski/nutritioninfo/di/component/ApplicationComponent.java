package com.mwmurawski.nutritioninfo.di.component;

import com.mwmurawski.nutritioninfo.App;
import com.mwmurawski.nutritioninfo.data.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.di.ApplicationScope;
import com.mwmurawski.nutritioninfo.di.module.ApplicationModule;
import com.mwmurawski.nutritioninfo.di.module.NetworkModule;
import com.mwmurawski.nutritioninfo.ui.base.CorePresenter;
import com.mwmurawski.nutritioninfo.ui.base.PresenterCache;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.SchedulerProvider;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@ApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(App application);

    void inject(CorePresenter corePresenter);

    PresenterCache getPresenterCache();

    CompositeDisposable getCompositeDisposable();

    SchedulerProvider getSchedulerProvider();

    //repositories
    SearchRepository getSearchRepository();

//    void inject(MainPresenter presenter);
//
//    void inject(FoodDetailsPresenter presenter);
}
