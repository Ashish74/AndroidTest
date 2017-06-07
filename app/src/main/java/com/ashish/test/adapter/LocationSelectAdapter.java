package com.ashish.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashish.test.R;
import com.ashish.test.model.TestModel;

import java.util.ArrayList;


public class LocationSelectAdapter extends RecyclerView.Adapter<LocationSelectAdapter.LocationSelectHolder> {

    public int intPosition1;
    public Context context;
    ArrayList<TestModel> testModelArrayList;

    TestModel testModelObj;

    public LocationSelectAdapter(Context context, ArrayList<TestModel> testModelArrayList) {
        this.context = context;
        this.testModelArrayList = testModelArrayList;
    }


    @Override
    public LocationSelectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_location_selection, parent, false);

        LocationSelectHolder viewHolder = new LocationSelectHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationSelectHolder holder, int position) {

        testModelObj = testModelArrayList.get(position);

        holder.tvALocationName.setText(testModelObj.getName());
    }

    @Override
    public int getItemCount() {
        if (testModelArrayList == null) {
            return 0;
        } else {
            return testModelArrayList.size();
        }
    }

    public class LocationSelectHolder extends RecyclerView.ViewHolder {
        private TextView tvALocationName;

        public LocationSelectHolder(View itemView) {
            super(itemView);
            tvALocationName = (TextView) itemView.findViewById(R.id.tvALocationName);
        }
    }
}
