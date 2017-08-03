package com.mwmurawski.nutritioninfo.ui.base;

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

    /**
     * Unbinds the view from a presenter.
     */
    public void unbindView() {
        viewReference = null;
        getCompositeDisposable().clear();
    }

    /**
     * Method checks if view is binded
     * @return true if view is binded, false if it's not
     */
    public boolean isViewBinded(){
        return viewReference != null && viewReference.get() != null;
    }


    /**
     * @return The view attached to this presenter, or {@code null} if it was unbound.
     */
    public T getView() {
        if (viewReference == null) {
            return null;
        }
        return viewReference.get();
    }

}
