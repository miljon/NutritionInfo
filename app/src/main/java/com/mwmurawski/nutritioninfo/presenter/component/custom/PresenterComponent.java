package com.mwmurawski.nutritioninfo.presenter.component.custom;

import com.mwmurawski.nutritioninfo.presenter.presenter.BasePresenter;

public interface PresenterComponent<T extends BasePresenter> {
    T getPresenter();
}
