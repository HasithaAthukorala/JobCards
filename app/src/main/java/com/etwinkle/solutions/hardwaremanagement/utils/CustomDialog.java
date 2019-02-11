package com.etwinkle.solutions.hardwaremanagement.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.models.ShopEquipment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog extends DialogFragment {

    private static List<ShopEquipment> shopEquipmentsList;
    private static int rowID;
    private ImageView eqImage;
    private TextView eqName,eqCode;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v= inflater.inflate(R.layout.custom_dialog, null);
        final EditText balance = (EditText) v.findViewById(R.id.balance);
        final EditText sold = (EditText) v.findViewById(R.id.sold);
        eqCode = (TextView) v.findViewById(R.id.eq_code);
        eqName = (TextView) v.findViewById(R.id.eq_name);
        eqImage = (ImageView) v.findViewById(R.id.eq_image);

        eqCode.setText(shopEquipmentsList.get(rowID).getEquipCode());
        eqName.setText(shopEquipmentsList.get(rowID).getEquipname());
        String serverImagePath = Helper.PUBLIC_IMAGE_FOLDER + shopEquipmentsList.get(rowID).getPhoto();
        Glide.with(getContext()).load(serverImagePath).into(eqImage);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        EventBus.getDefault().post(new OnDataChangeEvent(shopEquipmentsList,rowID,String.valueOf(balance.getText()),String.valueOf(sold.getText())));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CustomDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();

    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public static List<ShopEquipment> getShopEquipmentsList() {
        return shopEquipmentsList;
    }

    public static void setShopEquipmentsList(List<ShopEquipment> shopEquipmentsList) {
        CustomDialog.shopEquipmentsList = shopEquipmentsList;
    }

    public static int getRowID() {
        return rowID;
    }

    public static void setRowID(int rowID) {
        CustomDialog.rowID = rowID;
    }
}
