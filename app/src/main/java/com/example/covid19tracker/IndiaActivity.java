package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class IndiaActivity extends AppCompatActivity {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodaysCases,tvTotalDeaths,tvTodaysDeaths;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india);

        getSupportActionBar().setTitle("STATISTICS OF INDIA");

        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvTodaysCases=findViewById(R.id.tvTodaysCases);
        tvTotalDeaths=findViewById(R.id.tvTotalDeaths);
        tvTodaysDeaths=findViewById(R.id.tvTodaysDeath);

        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollStats);
        pieChart=findViewById(R.id.piechart);

        fetchData();
    }

    private void fetchData() {

        String url="https://corona.lmao.ninja/v2/countries/india/";


        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodaysCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodaysDeaths.setText(jsonObject.getString("todayDeaths"));


                            pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));

                            pieChart.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);

                            scrollView.setVisibility(View.VISIBLE);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(IndiaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void goTrackCountries(View view)
    {
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void goTrackStates(View view)
    {
        Intent intent=new Intent(getApplicationContext(), IndianStatesActivity.class);
        startActivity(intent);
    }
}