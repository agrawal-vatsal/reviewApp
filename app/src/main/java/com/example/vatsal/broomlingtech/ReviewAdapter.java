package com.example.vatsal.broomlingtech;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.vatsal.broomlingtech.models.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.myViewHolder> {

    Context context;
    ArrayList<Review> list;

    public ReviewAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Review review = list.get(position);
        holder.ratingBar.setNumStars(review.getRating());
        holder.authorTextView.setText("by: " + review.getAuthor());
        if (review.getReview() != null)
            holder.reviewTextView.setText(review.getReview());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView authorTextView, reviewTextView;
        RatingBar ratingBar;

        public myViewHolder(View itemView) {
            super(itemView);
            authorTextView = (TextView) itemView.findViewById(R.id.author);
            reviewTextView = (TextView) itemView.findViewById(R.id.review);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }
}
