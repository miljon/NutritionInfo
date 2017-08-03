package com.mwmurawski.nutritioninfo.data.network;

import com.mwmurawski.nutritioninfo.data.db.model.report.FoodReport;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("/search/")
    Observable<SearchResult> search(@Query("api_key") String apiKey, @Query("format") String format, @Query("q") String query, @Query("max") String max, @Query("offset") String offset);

    @GET("search/")
    Single<SearchResult> search(@Query("api_key") String apiKey, @Query("format") String format, @Query("q") String query);

    @GET("reports/")
    Single<FoodReport> foodReport(@Query("api_key") String apiKey, @Query("format") String format, @Query("type") String type, @Query("ndbno") String ndbno);
}
