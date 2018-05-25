package com.example.vatsal.broomlingtech.api;

import com.example.vatsal.broomlingtech.models.ReviewAPIModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface reviewList {
    @GET("review/{id}")
    Call<List<ReviewAPIModel>> getList(@Path("id") String id);
}
