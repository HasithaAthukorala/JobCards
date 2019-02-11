package com.etwinkle.solutions.hardwaremanagement.tableview.holder;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.tableview.TableViewModel;
import com.etwinkle.solutions.hardwaremanagement.utils.Helper;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

public class ImageCellViewHolder extends AbstractViewHolder {
    public final LinearLayout cell_container;
    public final ImageView cell_image;

    public ImageCellViewHolder(View itemView) {
        super(itemView);
        cell_container = (LinearLayout) itemView.findViewById(R.id.cell_container);
        cell_image = (ImageView) itemView.findViewById(R.id.cell_image);
    }


    public void setData(Object data) {
        int mood = (int) data;
        String serverImagePath = Helper.PUBLIC_IMAGE_FOLDER + String.valueOf(data);
        Glide.with(itemView.getContext()).load(serverImagePath).into(cell_image);
//        Drawable moodDrawable = ContextCompat.getDrawable(itemView.getContext(), mood ==
//                TableViewModel.HAPPY ? R.drawable.ic_happy : R.drawable.ic_sad);
//
//        cell_image.setImageDrawable(moodDrawable);
    }
}
