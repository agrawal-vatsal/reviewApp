package com.example.vatsal.broomlingtech.api;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface reviewPost {
    @POST("reviewPost/")
    void post(@Query("reviewText") String reviewText,
              @Query("starRating") int starRating,
              @Query("username") String username,
              @Query("placeID") String id);
}
