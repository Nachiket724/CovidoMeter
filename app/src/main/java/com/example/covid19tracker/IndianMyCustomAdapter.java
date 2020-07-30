package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class IndianMyCustomAdapter extends ArrayAdapter<StatesIndian> {

    private Context context;
    private List<StatesIndian> statesIndianList ;
    private List<StatesIndian> statesIndianListFiltered;

    public IndianMyCustomAdapter(Context context, List<StatesIndian> statesIndianList) {
        super(context, R.layout.list_states_custom_item,statesIndianList);

        this.context=context;
        this.statesIndianList=statesIndianList;
        this.statesIndianListFiltered=statesIndianList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_states_custom_item,null,true);
        TextView tvStatesName=view.findViewById(R.id.tvStatesName);

        tvStatesName.setText(statesIndianListFiltered.get(position).getState());


        return view;
    }

    @Override
    public int getCount() {
        return statesIndianListFiltered.size();
    }

    @Nullable
    @Override
    public StatesIndian getItem(int position) {
        return statesIndianListFiltered.get(position);
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
                    filterResults.count=statesIndianList.size();
                    filterResults.values=statesIndianList;
                }
                else
                {
                    List<StatesIndian> resultModel=new ArrayList<>();
                    String searchStr= charSequence.toString().toLowerCase();

                    for(StatesIndian itemsModel:statesIndianList)
                    {
                        if(itemsModel.getState().toLowerCase().contains(searchStr))
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

                statesIndianListFiltered=(List<StatesIndian>) filterResults.values;
                IndianStatesActivity.statesList=(List<StatesIndian>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
