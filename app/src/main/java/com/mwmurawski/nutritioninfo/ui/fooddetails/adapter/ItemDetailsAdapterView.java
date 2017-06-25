package com.mwmurawski.nutritioninfo.ui.fooddetails.adapter;

import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;

import java.util.List;

public interface ItemDetailsAdapterView {
    void setData(List<Nutrient> listOfItems);
    void setPresenter(FoodDetailsPresenter presenter);
}
