package com.mwmurawski.nutritioninfo.ui.fooddetails;

import com.mwmurawski.nutritioninfo.ui.base.BaseView;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;

import java.util.List;

public interface FoodDetailsView extends BaseView {
    void comebackToMainActivity();

    void showNutritionDetails(String name, List<Nutrient> nutrients);

    void showProgressBar();

    void hideProgressBar();
}
