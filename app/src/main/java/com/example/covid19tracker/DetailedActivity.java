package com.example.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+AffectedCountries.countryList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        tvCountry=findViewById(R.id.tvCountry);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvTodayCases=findViewById(R.id.tvTodaysCases);
        tvTotalDeaths=findViewById(R.id.tvDeaths);
        tvTodayDeath=findViewById(R.id.tvTodaysDeath);

        tvCountry.setText(AffectedCountries.countryList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.countryList.get(positionCountry).getCases());
        tvRecovered.setText(AffectedCountries.countryList.get(positionCountry).getRecovered());
        tvCritical.setText(AffectedCountries.countryList.get(positionCountry).getCritical());
        tvActive.setText(AffectedCountries.countryList.get(positionCountry).getActive());
        tvTodayCases.setText(AffectedCountries.countryList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(AffectedCountries.countryList.get(positionCountry).getDeaths());
        tvTodayDeath.setText(AffectedCountries.countryList.get(positionCountry).getTodayDeaths());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}