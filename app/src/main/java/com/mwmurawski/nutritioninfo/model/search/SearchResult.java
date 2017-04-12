package com.mwmurawski.nutritioninfo.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("list")
    @Expose
    private SearchList searchList;

    public SearchList getSearchList() {
        return searchList;
    }

    public void setSearchList(SearchList searchList) {
        this.searchList = searchList;
    }

}
