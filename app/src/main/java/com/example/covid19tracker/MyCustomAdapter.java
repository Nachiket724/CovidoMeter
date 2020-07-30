package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<Country> {

    private Context context;
    private List<Country> countryList;
    private List<Country> countryListFiltered;

    public MyCustomAdapter(Context context, List<Country> countryList) {
        super(context, R.layout.list_custom_item,countryList);

        this.context=context;
        this.countryList=countryList;
        this.countryListFiltered=countryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView tvCountryName=view.findViewById(R.id.tvCountryName);
        ImageView imageFlag=view.findViewById(R.id.imageFlag);

        tvCountryName.setText(countryListFiltered.get(position).getCountry());

        Glide.with(context).load(countryListFiltered.get(position).getFlag()).into(imageFlag);

        return view;
    }

    @Override
    public int getCount() {
        return countryListFiltered.size();
    }

    @Nullable
    @Override
    public Country getItem(int position) {
        return countryListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults=new FilterResults();
                if(charSequence==null || charSequence.length()==0)
                {
                    filterResults.count=countryList.size();
                    filterResults.values=countryList;
                }
                else
                {
                    List<Country> resultModel=new ArrayList<>();
                    String searchStr= charSequence.toString().toLowerCase();

                    for(Country itemsModel:countryList)
                    {
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr))
                        {
                            resultModel.add(itemsModel);
                        }
                        filterResults.count=resultModel.size();
                        filterResults.values=resultModel;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                countryListFiltered=(List<Country>) filterResults.values;
                AffectedCountries.countryList=(List<Country>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
