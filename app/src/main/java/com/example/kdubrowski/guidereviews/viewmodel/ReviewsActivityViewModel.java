package com.example.kdubrowski.guidereviews.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kdubrowski.guidereviews.BuildConfig;
import com.example.kdubrowski.guidereviews.R;
import com.example.kdubrowski.guidereviews.webservice.GetYourGuideAPI;
import com.example.kdubrowski.guidereviews.webservice.ReviewModel;
import com.example.kdubrowski.guidereviews.webservice.ReviewsSearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewsActivityViewModel extends ViewModel {

    public final static int PAGE_SIZE = 20;
    private final MutableLiveData<List<ReviewModel>> mObservableReviews;
    private final Retrofit mRetrofit;
    private Call<ReviewsSearchResult> mReviewsCall;
    private int mCurrentPageNumber;
    private int mTotalItems;

    public ReviewsActivityViewModel() {
        mCurrentPageNumber = 0;

        mObservableReviews = new MutableLiveData<>();
        mObservableReviews.setValue(null);

        mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.SERVER_URL).build();
        loadFromWebService(mCurrentPageNumber, new Callback<ReviewsSearchResult>() {
            @Override
            public void onResponse(Call<ReviewsSearchResult> call, Response<ReviewsSearchResult> response) {
                if (response.isSuccessful()) {
                    List<ReviewModel> retrievedData = response.body().getData();
                    if (retrievedData != null) {
                        mObservableReviews.setValue(retrievedData);
                    }
                    mTotalItems = response.body().getTotalCount();
                }
            }

            @Override
            public void onFailure(Call<ReviewsSearchResult> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    public LiveData<List<ReviewModel>> getReviews() {
        return mObservableReviews;
    }

    public void loadMore() {
        if (canLoadMore()) {
            mCurrentPageNumber++;
            loadFromWebService(mCurrentPageNumber, new Callback<ReviewsSearchResult>() {
                @Override
                public void onResponse(Call<ReviewsSearchResult> call, Response<ReviewsSearchResult> response) {
                    if (response.isSuccessful()) {
                        mTotalItems = response.body().getTotalCount();
                        List<ReviewModel> reviews = new ArrayList<>();
                        List<ReviewModel> oldReviews = mObservableReviews.getValue();
                        List<ReviewModel> newReviews = response.body().getData();
                        if (oldReviews != null) {
                            reviews.addAll(oldReviews);
                        }
                        if (newReviews != null) {
                            reviews.addAll(newReviews);
                        }
                        mObservableReviews.setValue(reviews);
                    }
                }

                @Override
                public void onFailure(Call<ReviewsSearchResult> call, Throwable t) {

                }
            });
        }
    }

    private boolean canLoadMore() {
        int totalPageNumber = mTotalItems / PAGE_SIZE;
        boolean isLastPage = mCurrentPageNumber == totalPageNumber;
        return (mReviewsCall == null || (mReviewsCall.isExecuted() && !isLastPage));
    }

    private void loadFromWebService(int pageNumber, Callback<ReviewsSearchResult> callback) {
        mReviewsCall = mRetrofit.create(GetYourGuideAPI.class).getReviews(PAGE_SIZE, pageNumber);
        mReviewsCall.enqueue(callback);
    }

}
