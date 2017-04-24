package com.mwmurawski.nutritioninfo.view;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;

/**
 * Created by Mij on 2017-04-23.
 */

public interface MainActivityView{
    
    void makeToast(String toastText);

    void putListToAdapter(List<SearchItem> searchItems);

//    void loadListOfItems(List<SearchItem> itemList);
//
//    void showProgressBar();
//
//    void loadingComplete();
//
//    void loadingError();
}
