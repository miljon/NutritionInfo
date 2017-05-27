package com.mwmurawski.nutritioninfo.view.interfaces;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;

import java.util.List;

import io.reactivex.Observable;

public interface ItemAdapterView {
    void setData(List<SearchItem> listOfItems);
    void setPresenter(MainActivityPresenter presenter);
    Observable<String> getNdbnoClickObservable();

}
