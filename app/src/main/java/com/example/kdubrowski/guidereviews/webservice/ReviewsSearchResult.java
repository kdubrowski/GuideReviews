package com.example.kdubrowski.guidereviews.webservice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsSearchResult {
    @SerializedName("status")
    private boolean status;
    @SerializedName("total_reviews")
    private int totalCount;
    @SerializedName("data")
    private List<ReviewModel> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ReviewModel> getData() {
        return data;
    }

    public void setData(List<ReviewModel> data) {
        this.data = data;
    }
}
