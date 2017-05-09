package com.mwmurawski.nutritioninfo.view.interfaces;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;

public interface ItemAdapterInterface {
    void setData(List<SearchItem> listOfItems);
}
