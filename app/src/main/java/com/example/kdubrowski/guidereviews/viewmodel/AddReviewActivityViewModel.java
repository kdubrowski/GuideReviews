package com.example.kdubrowski.guidereviews.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.kdubrowski.guidereviews.server.Server;
import com.example.kdubrowski.guidereviews.webservice.MockedReviewUploadAPI;
import com.example.kdubrowski.guidereviews.webservice.UploadReviewModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddReviewActivityViewModel extends ViewModel {

    private MutableLiveData<UploadReviewModel> mReviewModel;
    private Retrofit mRetrofit;

    public AddReviewActivityViewModel() {
        // start the mock server
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.getInstance().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        mRetrofit = new Retrofit.Builder().baseUrl("http://localhost:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mReviewModel = new MutableLiveData<>();
        mReviewModel.setValue(new UploadReviewModel());
    }

    public LiveData<UploadReviewModel> getReview() {
        return mReviewModel;
    }

    public void saveReview() {
        if (mReviewModel.getValue() != null) {
            mRetrofit.create(MockedReviewUploadAPI.class).postReview(mReviewModel.getValue()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        // handle success
                    } else {
                        // handle error
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    // handle error
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    protected void onCleared() {
        // perform server cleanup
        try {
            Server.getInstance().stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
