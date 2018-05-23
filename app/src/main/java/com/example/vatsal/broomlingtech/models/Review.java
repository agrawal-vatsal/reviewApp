package com.example.vatsal.broomlingtech.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Review {
    String author;
    String review;
    int rating;

    public Review(String author, String review, int rating) {
        this.author = author;
        this.review = review;
        this.rating = rating;
    }

    public Review(String author, int rating) {
        this.author = author;
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
