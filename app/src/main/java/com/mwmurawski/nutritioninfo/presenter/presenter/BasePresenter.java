package com.mwmurawski.nutritioninfo.presenter.presenter;

import com.mwmurawski.nutritioninfo.view.interfaces.BaseView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<T extends BaseView> extends CorePresenter{

    private WeakReference<T> viewReference;

    /**
     * Binds the given view to this presenter.
     *
     * @param view View to bind.
     */
    public void bindView(T view) {
        if (viewReference == null || viewReference.get() != view) {
            this.viewReference = new WeakReference<>(view);
        }
    }

    public void unbindView() {
        viewReference = null;
    }

    /**
     * Method checks if view is binded
      * @return true if view is binded, false if it's not
     */
    public boolean isViewBinded(){
        return viewReference.get() != null;
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
