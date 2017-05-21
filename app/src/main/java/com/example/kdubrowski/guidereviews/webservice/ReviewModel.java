package com.example.kdubrowski.guidereviews.webservice;

import com.example.kdubrowski.guidereviews.model.Review;
import com.google.gson.annotations.SerializedName;

public class ReviewModel implements Review {

    @SerializedName("review_id")
    private long id;
    @SerializedName("rating")
    private float rating;
    @SerializedName("title")
    private String title;
    @SerializedName("message")
    private String message;
    @SerializedName("author")
    private String author;
    @SerializedName("foreignLanguage")
    private boolean foreignLanguage;
    @SerializedName("date")
    private String date;
    @SerializedName("languageCode")
    private String languageCode;
    @SerializedName("reviewerName")
    private String reviewerName;
    @SerializedName("reviewerCountry")
    private String reviewerCountry;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public float getRating() {
        return rating;
    }

    @Override
    public String getDate() {
        return date;
    }
}
