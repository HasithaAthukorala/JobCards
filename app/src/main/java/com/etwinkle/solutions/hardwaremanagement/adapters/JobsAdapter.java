package com.etwinkle.solutions.hardwaremanagement.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.models.PendingJobs;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsViewHolder>{

    private static final String TAG = JobsAdapter.class.getSimpleName();

    private String myPlace;

    private Context context;
    private List<PendingJobs> categoryObject;

    public JobsAdapter(Context context, List<PendingJobs> categoryObject) {
        this.context = context;
        myPlace = "Activity";
        this.categoryObject = categoryObject;
    }

    public JobsAdapter(Context context, List<PendingJobs> categoryObject, String myPlace) {
        this.context = context;
        this.myPlace = myPlace;
        this.categoryObject = categoryObject;
    }

    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView;
        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_category_list, parent, false);
        return new JobsViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(JobsViewHolder holder, int position) {
        final PendingJobs productObject = categoryObject.get(position);
        if(productObject != null){
//            final int id = productObject.getId();
            holder.jobName.setText(productObject.getFaultName());
            holder.catName.setText(productObject.getFaultCategoryName());
            holder.description.setText(productObject.getDescription());
            holder.jobDate.setText(productObject.getDate());
            holder.department.setText(productObject.getDepartmentName());
        }

    }

    @Override
    public int getItemCount() {
        return categoryObject.size();
    }

}
