package com.mwmurawski.nutritioninfo.view.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mwmurawski.nutritioninfo.App;
import com.mwmurawski.nutritioninfo.cons.PresenterCache;
import com.mwmurawski.nutritioninfo.presenter.component.ApplicationComponent;
import com.mwmurawski.nutritioninfo.presenter.component.custom.PresenterComponent;
import com.mwmurawski.nutritioninfo.presenter.presenter.BasePresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject protected PresenterCache presenterCache;

    protected PresenterComponent<T> presenterComponent;

    protected T presenter;

    protected ApplicationComponent getApplicationComponent(){
        return ((App)getApplication()).getApplicationComponent();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutFile());
        inject();
        ButterKnife.bind(this);
        restoreOrCreatePresenter();
    }

    @SuppressWarnings("unchecked")
    private void restoreOrCreatePresenter() {
        presenter = presenterCache.getPresenter(getClass().getName());
        if (presenter == null) {
            //no cached presenter, create new
            presenter = presenterComponent.getPresenter();
            presenterCache.putPresenter(getClass().getName(), presenter);
        }
        presenter.bindView(this);
    }


    @LayoutRes
    protected abstract int getLayoutFile();

    /**
     * Inject all components for this class.
     */
    protected abstract void inject();

    @Override
    protected void onStop() {
        super.onStop();

        if (!isChangingConfigurations()){
            // activity is stopped normally, remove the cached presenter so it's not cached
            // even if activity gets killed
            presenterCache.removePresenter(presenter);
        }else {
            presenterCache.putPresenter(getClass().getName(),presenter);
        }
        presenter.unbindView();
    }
}
