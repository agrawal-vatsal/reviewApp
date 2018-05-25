package com.example.vatsal.broomlingtech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.vatsal.broomlingtech.api.reviewPost;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddReviewActivity extends AppCompatActivity {
    TextView name;
    RatingBar ratingBar;
    TextView submit;
    TextView error;
    Retrofit retrofit;
    EditText review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        name = (TextView) findViewById(R.id.textView);
        name.setText(getIntent().getExtras().getString("name"));
        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        submit = (TextView) findViewById(R.id.Submit);
        error = (TextView) findViewById(R.id.errorMessage);
        review = (EditText) findViewById(R.id.review);
        submit.setOnClickListener((View v) -> {
            if (ratingBar.getNumStars() == 0)
                error.setAlpha(1f);
            else {
                retrofit = new Retrofit.Builder()
                        .baseUrl(ReviewList.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                reviewPost post = retrofit.create(reviewPost.class);
                post.post(
                        review.getText().toString(),
                        ratingBar.getNumStars(),
                        "Vatsal",
                        getIntent().getExtras().getString("id")
                );
            }
            Intent intent = new Intent(AddReviewActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
