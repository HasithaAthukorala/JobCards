package com.etwinkle.solutions.hardwaremanagement.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etwinkle.solutions.hardwaremanagement.R;


public class JobsViewHolder extends RecyclerView.ViewHolder{

    public TextView jobName;
    public ImageView jobImage;
    public TextView jobDate;
    public TextView catName;
    public TextView department;
    public TextView description;

    public JobsViewHolder(View itemView) {
        super(itemView);
        jobDate = (TextView)itemView.findViewById(R.id.date);
        jobImage = (ImageView)itemView.findViewById(R.id.jobimage);
        jobName = (TextView)itemView.findViewById(R.id.name);
        catName = (TextView) itemView.findViewById(R.id.cat_name);
        department = (TextView) itemView.findViewById(R.id.department);
        description = (TextView) itemView.findViewById(R.id.description);
    }
}
