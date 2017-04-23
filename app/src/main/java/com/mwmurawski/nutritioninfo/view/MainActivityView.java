package com.mwmurawski.nutritioninfo.view;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;

/**
 * Created by Mij on 2017-04-23.
 */

public interface MainActivityView<T> extends BaseView{

    void loadListOfItems(List<SearchItem> itemList);

    void showProgressBar();

    void loadingComplete();

    void loadingError();
}
