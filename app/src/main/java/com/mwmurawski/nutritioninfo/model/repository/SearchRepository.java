package com.mwmurawski.nutritioninfo.model.repository;

import com.mwmurawski.nutritioninfo.cons.Cons;
import com.mwmurawski.nutritioninfo.model.report.FoodReport;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.model.service.SearchService;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;


public class SearchRepository {
    Retrofit retrofit;
    SearchService searchService;

    @Inject
    public SearchRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
        searchService = retrofit.create(SearchService.class);
    }

    public Single<SearchResult> getSearchResult(String queryString){
        return searchService.search(Cons.API_KEY, Cons.JSON, queryString);
    }

    public Single<FoodReport> getFoodReport(String ndbno){
        return searchService.foodReport(Cons.API_KEY, Cons.JSON, "b", ndbno);
    }


}
