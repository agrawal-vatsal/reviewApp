package com.example.vatsal.broomlingtech;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vatsal.broomlingtech.api.placeList;
import com.example.vatsal.broomlingtech.models.Example;
import com.example.vatsal.broomlingtech.models.Result;
import com.example.vatsal.broomlingtech.models.Review;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String api_key = "AIzaSyDVqkduN9kA0LZrCUinHYy0GFs-LupVELk";
    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    AutoCompleteTextView autoCompleteTextView;
    Spinner spinner;
    LocationManager locationManager;
    Location loc;
    public static final String TAG = "TAG";
    Retrofit retrofit;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } else
            Toast.makeText(getApplicationContext(),
                    "Couldn't get location permission", Toast.LENGTH_LONG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting current location
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.places, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //getting search to work
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.search);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = autoCompleteTextView.getText().toString();
                if (input != "") {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    placeList list = retrofit.create(placeList.class);
                    Callback<Example> callback = new Callback<Example>() {
                        @Override
                        public void onResponse(Call<Example> call, final Response<Example> response) {

                            if (response.isSuccessful() && response.body().getResults().size() > 0) {

                                final ArrayList<String> arrayList = new ArrayList<>();
                                for (Result result : response.body().getResults())
                                    arrayList.add(result.getName());

                                final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                                        android.R.layout.simple_list_item_1,
                                        arrayList);

                                autoCompleteTextView.setAdapter(adapter);
                                autoCompleteTextView.setThreshold(3);
                                autoCompleteTextView.setTextColor(Color.BLACK);
                                autoCompleteTextView.showDropDown();

                                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(MainActivity.this, ReviewList.class);
                                        intent.putExtra("id", response
                                                .body()
                                                .getResults()
                                                .get(position)
                                                .getId());
                                        getApplicationContext().startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Example> call, Throwable t) {
                            Log.d(TAG, "onFailure: can't fetch api data");
                        }
                    };
                    list.getList(loc.getLatitude(), loc.getLongitude(), spinner.getSelectedItem().toString(), input).enqueue(callback);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
