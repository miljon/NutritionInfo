package com.mwmurawski.nutritioninfo.ui.main;

import com.mwmurawski.nutritioninfo.ui.base.BaseView;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;

import java.util.List;

/**
 * Created by Mij on 2017-04-23.
 */

public interface MainView extends BaseView {

    void putListToAdapter(List<SearchItem> searchItems);

    void showProgressBar();

    void hideProgressBar();

    void startDetailsActivity(String ndbno);
}
