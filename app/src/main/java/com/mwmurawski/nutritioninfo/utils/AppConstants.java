package com.mwmurawski.nutritioninfo.utils;

import com.mwmurawski.nutritioninfo.BuildConfig;

public class AppConstants {

    public static String BASE_URL = "https://api.nal.usda.gov/ndb/"; //not final because in tests we switch it to mock

    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String JSON = "json";

    //=== ERROR STRINGS ===
    public static final String EMPTY_STRING = "Error: empty text";
    public static final String NETWORK_PROBLEM = "Unable to load\nCheck network connection";
    public static final String OBSERVER_PROBLEM = "Observer problem";
    public static final String EMPTY_RESPONSE = "Empty response";

    //===  ===
    //===  ===
    //===  ===
    //===  ===

}
