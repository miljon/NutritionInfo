package com.mwmurawski.nutritioninfo.view.interfaces;

import com.mwmurawski.nutritioninfo.model.report.Nutrient;

import java.util.List;

public interface FoodDetailsView extends BaseView {
    void comebackToMainActivity();

    void showNutritionDetails(String name, List<Nutrient> nutrients);

    void showProgressBar();

    void hideProgressBar();
}
