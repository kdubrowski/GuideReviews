package com.example.kdubrowski.guidereviews.webservice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MockedReviewUploadAPI {
    @POST("/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json")
    Call<Void> postReview(@Body UploadReviewModel review);
}
