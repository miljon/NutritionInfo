package com.mwmurawski.nutritioninfo.presenter.presenter;

import com.mwmurawski.nutritioninfo.view.interfaces.BaseView;

import java.lang.ref.WeakReference;

public class BasePresenter<T extends BaseView> extends BaseBasePresenter {

    private WeakReference<T> viewReference;


    /**
     * Binds the given view to this presenter.
     *
     * @param view View to bind.
     */
    public void bindView(T view) {
        if (viewReference != null && viewReference.get() == view) {
            return;
        } else {
            this.viewReference = new WeakReference<T>(view);
        }
    }

    public void unbindView() {
        viewReference = null;
    }


    /**
     * @return The view attached to this presenter, or {@code null} if it was unbound.
     */
    protected T getView() {
        if (viewReference == null) {
            return null;
        }
        return viewReference.get();
    }
}
