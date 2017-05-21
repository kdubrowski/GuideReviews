package com.example.kdubrowski.guidereviews;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.kdubrowski.guidereviews.databinding.ActivityReviewsBinding;
import com.example.kdubrowski.guidereviews.ui.ReviewsAdapter;
import com.example.kdubrowski.guidereviews.viewmodel.ReviewsActivityViewModel;
import com.example.kdubrowski.guidereviews.webservice.ReviewModel;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.List;

public class ReviewsActivity extends LifecycleActivity {

    private ActivityReviewsBinding mBinding;
    private ReviewsAdapter mReviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_reviews);

        mReviewsAdapter = new ReviewsAdapter();
        mBinding.reviewsList.setAdapter(mReviewsAdapter);

        final ReviewsActivityViewModel viewModel = ViewModelProviders.of(this).get(ReviewsActivityViewModel.class);
        subscribeToData(viewModel);

        mBinding.reviewsList.addOnScrollListener(new InfiniteScrollListener(ReviewsActivityViewModel.PAGE_SIZE, (LinearLayoutManager) mBinding.reviewsList.getLayoutManager()) {
            @Override
            public void onScrolledToEnd(int firstVisibleItemPosition) {
                viewModel.loadMore();
            }
        });

        mBinding.addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddReview();

            }
        });

    }

    private void subscribeToData(ReviewsActivityViewModel viewModel) {
        viewModel.getReviews().observe(this, new Observer<List<ReviewModel>>() {
            @Override
            public void onChanged(@Nullable List<ReviewModel> reviewModels) {
                if (reviewModels != null) {
                    mBinding.setIsLoading(false);
                    mReviewsAdapter.setReviews(reviewModels);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    private void showAddReview() {
        Intent addReviewIntent = new Intent(this, AddReviewActivity.class);
        startActivity(addReviewIntent);
    }
}
