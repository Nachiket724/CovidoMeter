package com.example.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IndianStatesActivity extends AppCompatActivity {

    EditText edtSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<StatesIndian> statesList=new ArrayList<>();

    StatesIndian statesIndian;
    IndianMyCustomAdapter myCustomAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_states);
        statesList.clear();

        edtSearch=findViewById(R.id.edtSearch);
        listView=findViewById(R.id.listView);
        simpleArcLoader=findViewById(R.id.loader);

        getSupportActionBar().setTitle("Indian States");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),IndianDetailedActivity.class);
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                myCustomAdapter.getFilter().filter(charSequence);
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {

        String url="https://disease.sh/v3/covid-19/gov/India/";

        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject=new JSONObject(response);

                            JSONArray jsonObject1=jsonObject.getJSONArray("states");

                            JSONArray jsonArray=new JSONArray(jsonObject1.toString());

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject3=jsonArray.getJSONObject(i);
                                String stateName=jsonObject3.getString("state");
                                String active=jsonObject3.getString("active");
                                String recovered=jsonObject3.getString("recovered");
                                String deaths=jsonObject3.getString("deaths");
                                String cases=jsonObject3.getString("cases");

                                statesIndian=new StatesIndian(stateName,cases,deaths,recovered,active);
                                statesList.add(statesIndian);

                            }


                            myCustomAdapter=new IndianMyCustomAdapter(IndianStatesActivity.this,statesList);
                            listView.setAdapter(myCustomAdapter);
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(IndianStatesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }




}