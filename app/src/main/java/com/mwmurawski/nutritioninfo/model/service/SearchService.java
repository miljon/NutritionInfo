package com.mwmurawski.nutritioninfo.model.service;

import com.mwmurawski.nutritioninfo.model.report.FoodReport;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mij on 2017-04-17.
 */

public interface SearchService {

    @GET("/search/")
    Observable<SearchResult> search(@Query("api_key") String apiKey, @Query("format") String format, @Query("q") String query, @Query("max") String max, @Query("offset") String offset);

    @GET("search/")
    Single<SearchResult> search(@Query("api_key") String apiKey, @Query("format") String format, @Query("q") String query);

    @GET("reports/")
    Single<FoodReport> foodReport(@Query("api_key") String apiKey, @Query("format") String format, @Query("type") String type, @Query("ndbno") String ndbno);
}
