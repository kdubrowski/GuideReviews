package com.example.kdubrowski.guidereviews.model;

public interface EditableReview {
    String getTitle();

    void setTitle(String title);

    String getMessage();

    void setMessage(String message);

    float getRating();

    void setRating(float rating);

    boolean isAnonymous();

    void setAnonymous(boolean anonymous);
}
