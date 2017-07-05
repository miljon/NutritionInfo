package com.mwmurawski.nutritioninfo.ui.main.adapter;

import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;

import java.util.List;

import io.reactivex.Single;

public interface ItemAdapterView {
    void setData(List<SearchItem> listOfItems);
    void setPresenter(MainPresenter presenter);
    Single<String> getNdbnoClickSingle();

}
