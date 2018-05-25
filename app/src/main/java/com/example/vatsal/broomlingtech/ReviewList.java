package com.example.vatsal.broomlingtech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.vatsal.broomlingtech.api.reviewList;
import com.example.vatsal.broomlingtech.models.Review;
import com.example.vatsal.broomlingtech.models.ReviewAPIModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewList extends AppCompatActivity {
    RecyclerView recyclerView;
    Retrofit retrofit;
    public static final String BASE_URL = "https://vatsal-api.herokuapp.com/";
    TextView addReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reviewList list = retrofit.create(reviewList.class);
        Callback<List<ReviewAPIModel>> callback = new Callback<List<ReviewAPIModel>>() {
            @Override
            public void onResponse(Call<List<ReviewAPIModel>> call, Response<List<ReviewAPIModel>> response) {
                if (response.isSuccessful() && response.body().size() > 0) {
                    ArrayList<Review> reviewArrayList = new ArrayList<>();
                    for (ReviewAPIModel reviewAPIModel : response.body())
                        if (reviewAPIModel.getReview() != null)
                            reviewArrayList.add(new Review(reviewAPIModel.getAuthor(), reviewAPIModel.getReview(), reviewAPIModel.getRating()));
                        else
                            reviewArrayList.add(new Review(reviewAPIModel.getAuthor(), reviewAPIModel.getRating()));
                    ReviewAdapter adapter = new ReviewAdapter(getApplicationContext(), reviewArrayList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ReviewAPIModel>> call, Throwable t) {

            }
        };
        list.getList(getIntent().getExtras().getString("id")).enqueue(callback);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Toolbar toolbar = (Toolbar) getActionBarView();
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setTitle(getIntent().getExtras().getString("name"));
//        }
        addReview = (TextView) findViewById(R.id.addReview);
        addReview.setOnClickListener((View v) -> {
            Intent intent = new Intent(ReviewList.this, AddReviewActivity.class);
            intent.putExtra("id",
                    getIntent()
                            .getExtras()
                            .getString("id"));
            intent.putExtra("name",
                    getIntent()
                            .getExtras()
                            .getString("name"));
            startActivity(intent);
        });
    }

    public View getActionBarView() {
        Window window = getWindow();
        View v = window.getDecorView();
        int resId = getResources().getIdentifier("action_bar_container", "id", "android");
        return v.findViewById(resId);
    }
}
