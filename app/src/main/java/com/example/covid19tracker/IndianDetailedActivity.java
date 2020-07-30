package com.example.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class IndianDetailedActivity extends AppCompatActivity {

    private  int positionState;

    TextView tvStates,tvCases,tvRecovered,tvActive,tvTotalDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_detailed);

        Intent intent=getIntent();
        positionState=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+IndianStatesActivity.statesList.get(positionState).getState());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        tvStates=findViewById(R.id.tvStates);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvActive=findViewById(R.id.tvActive);
        tvTotalDeaths=findViewById(R.id.tvDeaths);

        tvStates.setText(IndianStatesActivity.statesList.get(positionState).getState());
        tvCases.setText(IndianStatesActivity.statesList.get(positionState).getCases());
        tvRecovered.setText(IndianStatesActivity.statesList.get(positionState).getRecovered());

        tvActive.setText(IndianStatesActivity.statesList.get(positionState).getActive());

        tvTotalDeaths.setText(IndianStatesActivity.statesList.get(positionState).getDeaths());

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