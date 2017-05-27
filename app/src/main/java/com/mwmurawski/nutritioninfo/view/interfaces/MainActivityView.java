package com.mwmurawski.nutritioninfo.view.interfaces;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;

/**
 * Created by Mij on 2017-04-23.
 */

public interface MainActivityView extends BaseView{

    void putListToAdapter(List<SearchItem> searchItems);

    void showProgressBar();

    void hideProgressBar();

    void startDetailsActivity(String ndbno);
}
