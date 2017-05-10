package com.mwmurawski.nutritioninfo.view.interfaces;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;

/**
 * Created by Mij on 2017-04-23.
 */

public interface MainActivityView extends BaseView{

    void makeToast(String toastText);

    void putListToAdapter(List<SearchItem> searchItems);

    void showProgressBar();

    void hideProgressBar();

    void setSwipeRefreshingToFalse();

//    void loadListOfItems(List<SearchItem> itemList);
//
//    void showProgressBar();
//
//    void loadingComplete();
//
//    void loadingError();
}
