package com.example.kdubrowski.guidereviews.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kdubrowski.guidereviews.R;
import com.example.kdubrowski.guidereviews.databinding.ReviewItemBinding;
import com.example.kdubrowski.guidereviews.model.Review;

import java.util.List;
import java.util.Objects;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private List<? extends Review> mReviews;

    public ReviewsAdapter() {

    }

    /**
     * set the new review data on the adapter. This will check if items were inserted or removed or
     * if it is a completely new list and will notify the adapter of the new data in a way that allows
     * for animations to be performed
     *
     * @param reviews the list with the reviews to be set as data source
     */
    public void setReviews(final List<? extends Review> reviews) {
        if (mReviews == null) {
            mReviews = reviews;
            this.notifyItemRangeInserted(0, reviews.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mReviews.size();
                }

                @Override
                public int getNewListSize() {
                    return reviews.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mReviews.get(oldItemPosition).getId() == reviews.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Review review = reviews.get(newItemPosition);
                    Review oldReview = mReviews.get(oldItemPosition);
                    return review.getId() == oldReview.getId() &&
                            Objects.equals(review.getAuthor(), oldReview.getAuthor()) &&
                            Objects.equals(review.getTitle(), oldReview.getTitle()) &&
                            Objects.equals(review.getMessage(), oldReview.getMessage()) &&
                            Objects.equals(review.getRating(), review.getRating());
                }
            }, false);
            mReviews = reviews;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReviewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.review_item, parent, false);
        return new ReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.binding.setReview(mReviews.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mReviews == null ? 0 : mReviews.size();
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        final ReviewItemBinding binding;

        public ReviewViewHolder(ReviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
