package com.example.vatsal.broomlingtech.api;

import com.example.vatsal.broomlingtech.MainActivity;
import com.example.vatsal.broomlingtech.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface placeList {
    @GET("location={latitude},{longitude}&radius=15000&type={type}&keyword={key}&key=" + MainActivity.api_key)
    Call<Example> getList(@Path("latitude") double latitude,
                          @Path("longitude") double longitude,
                          @Path("type") String type,
                          @Path("key") String key);
}
