package com.mwmurawski.nutritioninfo.model.service;

import com.mwmurawski.nutritioninfo.model.search.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mij on 2017-04-17.
 */

public interface SearchService {

    @GET("/search/")
    Call<SearchResult> search(@Query("api_key") String apiKey, @Query("format") String format, @Query("q") String query, @Query("max") String max, @Query("offset") String offset);

    @GET("search/")
    Call<SearchResult> search(@Query("api_key") String apiKey, @Query("format") String format, @Query("q") String query);
}
