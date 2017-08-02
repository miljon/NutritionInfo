package com.mwmurawski.nutritioninfo.ui.main;

import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.ui.base.BaseView;

import java.util.List;

public interface MainView extends BaseView {

    /**
     * Sets new list to adapter
     * @param searchItems list of #SearchItem to set
     */
    void putListToAdapter(List<SearchItem> searchItems);

    /**
     * Shows progress bar
     */
    void showProgressBar();

    /**
     * Hides progress bar
     */
    void hideProgressBar();

    /**
     * Starts FoodDetailsActivity for specific nutrient
     * @param ndbno nutrient database number
     */
    void startDetailsActivity(String ndbno);
}
