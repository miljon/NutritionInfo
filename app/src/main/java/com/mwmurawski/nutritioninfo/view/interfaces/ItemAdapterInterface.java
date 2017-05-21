package com.mwmurawski.nutritioninfo.view.interfaces;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;

import java.util.List;

public interface ItemAdapterInterface {
    void setData(List<SearchItem> listOfItems);
    void setPresenter(MainActivityPresenter presenter);
}
