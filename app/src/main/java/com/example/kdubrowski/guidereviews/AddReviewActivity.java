package com.example.kdubrowski.guidereviews;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.kdubrowski.guidereviews.databinding.ActivityAddReviewBinding;
import com.example.kdubrowski.guidereviews.model.EditableReview;
import com.example.kdubrowski.guidereviews.viewmodel.AddReviewActivityViewModel;
import com.example.kdubrowski.guidereviews.webservice.UploadReviewModel;

public class AddReviewActivity extends LifecycleActivity {

    private ActivityAddReviewBinding mBinding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_review);

        final AddReviewActivityViewModel viewModel = ViewModelProviders.of(this).get(AddReviewActivityViewModel.class);
        mBinding.saveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.saveReview();
            }
        });
        subscribeToData(viewModel);
    }

    private void subscribeToData(AddReviewActivityViewModel viewModel) {
        viewModel.getReview().observe(this, new Observer<UploadReviewModel>() {
            @Override
            public void onChanged(@Nullable UploadReviewModel uploadReviewModel) {
                mBinding.setReview(uploadReviewModel);
            }
        });
    }
}
