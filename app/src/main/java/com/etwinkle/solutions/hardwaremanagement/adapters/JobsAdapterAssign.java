package com.etwinkle.solutions.hardwaremanagement.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.models.PendingJobs;

import java.util.List;

public class JobsAdapterAssign extends RecyclerView.Adapter<JobsViewHolderAssign>{

    private static final String TAG = JobsAdapterAssign.class.getSimpleName();

    private String myPlace;

    private Context context;
    private List<PendingJobs> categoryObject;

    public JobsAdapterAssign(Context context, List<PendingJobs> categoryObject) {
        this.context = context;
        myPlace = "Activity";
        this.categoryObject = categoryObject;
    }

    public JobsAdapterAssign(Context context, List<PendingJobs> categoryObject, String myPlace) {
        this.context = context;
        this.myPlace = myPlace;
        this.categoryObject = categoryObject;
    }

    @Override
    public JobsViewHolderAssign onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView;
        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_category_list_assign, parent, false);
        return new JobsViewHolderAssign(layoutView);
    }

    @Override
    public void onBindViewHolder(JobsViewHolderAssign holder, int position) {
        final PendingJobs productObject = categoryObject.get(position);
        if(productObject != null){
//            final int id = productObject.getId();
            holder.jobName.setText(productObject.getFaultName());
            holder.catName.setText(productObject.getFaultCategoryName());
            holder.description.setText(productObject.getDescription());
            holder.jobDate.setText(productObject.getDate());
            holder.department.setText(productObject.getDepartmentName());
            holder.assign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return categoryObject.size();
    }

}
