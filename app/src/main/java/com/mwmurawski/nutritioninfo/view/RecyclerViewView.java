package com.mwmurawski.nutritioninfo.view;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;

/**
 * Created by Mij on 2017-04-20.
 */

public interface RecyclerViewView<T> extends BaseView {

    void loadListOfItems(List<SearchItem> listOfItems);


}
