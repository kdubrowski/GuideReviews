package com.example.kdubrowski.guidereviews.webservice;

import com.example.kdubrowski.guidereviews.model.EditableReview;
import com.google.gson.annotations.SerializedName;

public class UploadReviewModel implements EditableReview {

    @SerializedName("message")
    private String message;
    @SerializedName("title")
    private String title;
    @SerializedName("isAnonymousComment")
    private boolean isAnonymous;
    @SerializedName("rating")
    private float rating;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public float getRating() {
        return rating;
    }

    @Override
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean isAnonymous() {
        return isAnonymous;
    }

    @Override
    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
