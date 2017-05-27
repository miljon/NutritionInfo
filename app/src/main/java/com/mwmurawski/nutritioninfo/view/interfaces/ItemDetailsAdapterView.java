package com.mwmurawski.nutritioninfo.view.interfaces;

import com.mwmurawski.nutritioninfo.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.presenter.presenter.FoodDetailsPresenter;

import java.util.List;

public interface ItemDetailsAdapterView {
    void setData(List<Nutrient> listOfItems);
    void setPresenter(FoodDetailsPresenter presenter);
}
