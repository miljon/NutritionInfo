package com.mwmurawski.nutritioninfo.view.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.mwmurawski.nutritioninfo.App;
import com.mwmurawski.nutritioninfo.presenter.component.ApplicationComponent;
import com.mwmurawski.nutritioninfo.presenter.component.custom.PresenterProviderInterface;
import com.mwmurawski.nutritioninfo.presenter.presenter.BasePresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.BaseView;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends CoreActivity implements BaseView {

    protected PresenterProviderInterface<T> presenterProviderInterface;

    protected T presenter;

    protected ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutFile());
        inject();
        ButterKnife.bind(this);
        if (restoreOrCreatePresenter())
            asignPresenterValuesToViewAfterRestore();
    }

    @SuppressWarnings("unchecked")
    private boolean restoreOrCreatePresenter() {
        presenter = presenterCache.getPresenter(getClass().getName());
        if (presenter == null) {
            //no cached presenter, create new
            presenter = presenterProviderInterface.getPresenter();
            presenterCache.putPresenter(getClass().getName(), presenter);
            presenter.bindView(this);
            return false;
        } else {
            presenter.bindView(this);
            return true;
        }
    }


    @LayoutRes
    protected abstract int getLayoutFile();

    /**
     * Inject all components for this class.
     */
    protected abstract void inject();

    public abstract void asignPresenterValuesToViewAfterRestore();

    @Override
    protected void onStop() {
        super.onStop();

        if (!isChangingConfigurations()) {
            // activity is stopped normally, remove the cached presenter so it's not cached
            // even if activity gets killed
            presenterCache.removePresenter(presenter);
        } else {
            presenterCache.putPresenter(getClass().getName(), presenter);
        }
        presenter.unbindView();
    }
}
