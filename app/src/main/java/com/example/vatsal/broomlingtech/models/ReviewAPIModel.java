package com.example.vatsal.broomlingtech.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewAPIModel {

    @SerializedName("reviewText")
    @Expose
    private String reviewText;
    @SerializedName("starRating")
    @Expose
    private Integer starRating;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("placeID")
    @Expose
    private String placeID;
    @SerializedName("time")
    @Expose
    private String time;

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return getUsername();
    }

    public int getRating() {
        return getStarRating();
    }

    public String getReview() {
        if (getReviewText() == "")
            return null;
        return getReviewText();
    }


}
