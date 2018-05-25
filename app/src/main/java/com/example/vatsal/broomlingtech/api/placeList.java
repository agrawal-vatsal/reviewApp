package com.example.vatsal.broomlingtech.api;

import com.example.vatsal.broomlingtech.MainActivity;
import com.example.vatsal.broomlingtech.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface placeList {
    @GET("maps/api/place/nearbysearch/json")
    Call<Example> getList(@Query("location") String location,
                          @Query("type") String type,
                          @Query("radius") int value,
                          @Query("keyword") String key,
                          @Query("key") String api);
}
