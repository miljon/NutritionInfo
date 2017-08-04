package com.mwmurawski.nutritioninfo.ui.fooddetails;

import com.mwmurawski.nutritioninfo.ui.base.BaseView;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;

import java.util.List;

public interface FoodDetailsView extends BaseView {

    /**
     * Goes back to mainActivity
     */
    void comebackToMainActivity();

    /**
     * Show name in a textView and put nutrient list in a recyclerView
     * @param name name of food
     * @param nutrients nutrient list
     */
    void showNutritionDetails(String name, List<Nutrient> nutrients);

    /**
     * Shows progress bar
     */
    void showProgressBar();

    /**
     * Hides progress bar
     */
    void hideProgressBar();
}
