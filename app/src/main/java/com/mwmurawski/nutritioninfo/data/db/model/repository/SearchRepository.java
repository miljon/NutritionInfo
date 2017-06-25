package com.mwmurawski.nutritioninfo.data.db.model.repository;

import com.mwmurawski.nutritioninfo.utils.AppConstants;
import com.mwmurawski.nutritioninfo.data.db.model.report.FoodReport;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.data.network.SearchService;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;


public class SearchRepository {
    private Retrofit retrofit;
    private SearchService searchService;

    @Inject
    public SearchRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
        searchService = retrofit.create(SearchService.class);
    }

    public Single<SearchResult> getSearchResult(String queryString){
        return searchService.search(AppConstants.API_KEY, AppConstants.JSON, queryString);
    }

    public Single<FoodReport> getFoodReport(String ndbno){
        return searchService.foodReport(AppConstants.API_KEY, AppConstants.JSON, "b", ndbno);
    }


}
