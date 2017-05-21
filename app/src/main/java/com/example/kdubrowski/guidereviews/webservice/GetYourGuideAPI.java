package com.example.kdubrowski.guidereviews.webservice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetYourGuideAPI {

    @Headers({
            "Accept: application/json",
            "User-Agent: GetYourGuideAPP"
    })
    @GET("/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json")
    Call<ReviewsSearchResult> getReviews(@Query("count") Integer count, @Query("page") int page);

}
