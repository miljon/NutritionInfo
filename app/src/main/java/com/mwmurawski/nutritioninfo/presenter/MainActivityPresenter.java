package com.mwmurawski.nutritioninfo.presenter;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.view.MainActivityView;

import java.util.List;

/**
 * Created by Mij on 2017-04-20.
 */

public class MainActivityPresenter {

    private MainActivityView mainActivityView;

    private List<SearchItem> itemList;

    public void onAttachView(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        mainActivityView.setPresenter(this);
    }

    public void onDetachView() {
        mainActivityView = null;
    }

    public void loadItemList(String query) {
        //get item list from server
    }


}
