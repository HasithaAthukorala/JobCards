package com.etwinkle.solutions.hardwaremanagement.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.activities.AcceptActivity;
import com.etwinkle.solutions.hardwaremanagement.activities.AssignActivity;
import com.etwinkle.solutions.hardwaremanagement.models.AcceptJob;

import java.util.List;

public class JobsAdapterAccept extends RecyclerView.Adapter<JobsViewHolderAccept>{

    private static final String TAG = JobsAdapterAccept.class.getSimpleName();

    private String myPlace;

    private Context context;
    private List<AcceptJob> categoryObject;

    public JobsAdapterAccept(Context context, List<AcceptJob> categoryObject) {
        this.context = context;
        myPlace = "Activity";
        this.categoryObject = categoryObject;
    }

    public JobsAdapterAccept(Context context, List<AcceptJob> categoryObject, String myPlace) {
        this.context = context;
        this.myPlace = myPlace;
        this.categoryObject = categoryObject;
    }

    @Override
    public JobsViewHolderAccept onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView;
        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_category_list_accept, parent, false);
        return new JobsViewHolderAccept(layoutView);
    }

    @Override
    public void onBindViewHolder(JobsViewHolderAccept holder, int position) {
        final AcceptJob productObject = categoryObject.get(position);
        if(productObject != null){
//            final int id = productObject.getId();
            holder.jobName.setText(productObject.get_id());
            holder.catName.setText(productObject.getJobID());
            holder.description.setText(productObject.getDescription());
            Log.d("saaaaa",productObject.getAccept());
            if(productObject.getAccept().equals("ru")){
                holder.assign.setVisibility(View.GONE);
                holder.jobDate.setText("Accepted");
            }else {
                holder.assign.setVisibility(View.VISIBLE);
            }
//            holder.jobDate.setText(productObject.getDate());
//            holder.department.setText(productObject.getDepartmentName());
            holder.assign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,AcceptActivity.class);
                    intent.putExtra("jobid",productObject.get_id());
                    intent.putExtra("tid",productObject.getJobID());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return categoryObject.size();
    }

}
