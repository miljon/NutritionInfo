package com.mwmurawski.nutritioninfo.di.component.custom;

import com.mwmurawski.nutritioninfo.ui.base.BasePresenter;

public interface PresenterProviderInterface<T extends BasePresenter> {
    T getPresenter();
}
