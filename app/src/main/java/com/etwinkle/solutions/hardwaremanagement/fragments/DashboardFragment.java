package com.etwinkle.solutions.hardwaremanagement.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.barcodereader.QRActivity;
import com.etwinkle.solutions.hardwaremanagement.models.ShopEquipment;
import com.etwinkle.solutions.hardwaremanagement.utils.CustomDialog;
import com.etwinkle.solutions.hardwaremanagement.utils.ManualLoginDialog;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private Button qrCodeScanBtn;
    private Button manualLogin;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        qrCodeScanBtn = (Button) view.findViewById(R.id.qr_scan_btn);
        manualLogin = (Button) view.findViewById(R.id.manual_login);
        qrCodeScanBtn.setOnClickListener(this);
        manualLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.qr_scan_btn:
                startActivity(new Intent(getActivity(),QRActivity.class));
                return;
            case R.id.manual_login:
                showNoticeDialog();
                return;
        }
    }
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new ManualLoginDialog();
        Log.d("safasfa","asfa");
        try {
            dialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), "NoticeDialogFragment");
        }catch (NullPointerException d){}
    }
}
