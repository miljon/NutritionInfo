package com.mwmurawski.nutritioninfo.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mwmurawski.nutritioninfo.App;
import com.mwmurawski.nutritioninfo.di.component.ActivityComponent;
import com.mwmurawski.nutritioninfo.di.component.ApplicationComponent;
import com.mwmurawski.nutritioninfo.di.component.DaggerActivityComponent;
import com.mwmurawski.nutritioninfo.di.module.ActivityModule;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T presenter;

    private ActivityComponent activityComponent;

    protected ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutFile());

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        inject();
        ButterKnife.bind(this);
    }

    /**
     * Restoring presenter
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean restoreOrCreatePresenter() {
        presenter = getPresenterCache().getPresenter(getClass().getName());
        if (presenter == null) {
            //no cached presenter, create new
            injectPresenter();
            getPresenterCache().putPresenter(getClass().getName(), presenter);
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
     * Inject component for this class.
     */
    protected abstract void inject();

    /**
     * Specifically inject presenter from a component.
     * LookUp other activities to see how.
     */
    protected abstract void injectPresenter();

    /**
     * Assign values that presenter stores to a activity when it's recreated (for example when orientation changes)
     */
    public abstract void assignPresenterValuesToViewAfterRestore();

    @Override
    protected void onStart() {
        super.onStart();

        if (restoreOrCreatePresenter())
            assignPresenterValuesToViewAfterRestore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!isChangingConfigurations()) {
            // activity is stopped normally, remove the cached presenter so it's not cached
            // even if activity gets killed
            getPresenterCache().removePresenter(presenter);
        } else {
            getPresenterCache().putPresenter(getClass().getName(), presenter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbindView();
    }

    /**
     * Makes toast on the device, only short length.
     * @param toastText toast message
     */
    public void makeToast(String toastText) {
        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
    }

    /**
     * Get presenter cache to check if there presenter stored
     * @return presenter cache
     */
    public PresenterCache getPresenterCache() {
        return activityComponent.getPresenterCache();
    }

    /**
     * Get presenter to access presenter method.
     * For test purpose
     * @return specific presenter
     */
    public T getPresenter() {
        return presenter;
    }

    /**
     * Set presenter to match a activity
     * @param presenter presenter to set
     */
    protected void setPresenter(T presenter) {
        this.presenter = presenter;
    }
}
