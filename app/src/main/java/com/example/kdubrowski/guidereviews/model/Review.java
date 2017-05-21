package com.example.kdubrowski.guidereviews.model;

public interface Review {

    long getId();

    String getMessage();

    String getTitle();

    String getAuthor();

    float getRating();

    String getDate();

}
