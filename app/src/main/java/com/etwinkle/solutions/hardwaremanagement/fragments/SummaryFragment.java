package com.etwinkle.solutions.hardwaremanagement.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.adapters.JobsAdapterAssign;
import com.etwinkle.solutions.hardwaremanagement.models.PendingJobs;
import com.etwinkle.solutions.hardwaremanagement.models.Summary;
import com.etwinkle.solutions.hardwaremanagement.network.GsonRequest;
import com.etwinkle.solutions.hardwaremanagement.network.VolleySingleton;
import com.etwinkle.solutions.hardwaremanagement.utils.Helper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {


    private TextView completed,incompleted,pending;

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        completed = (TextView) view.findViewById(R.id.completed);
        incompleted = (TextView) view.findViewById(R.id.incompleted);
        pending = (TextView) view.findViewById(R.id.pending);
        getDataFromRemoteServer();
        return view;
    }

    public void getDataFromRemoteServer() {

        GsonRequest<Summary> serverRequest = new GsonRequest<Summary>(
                Request.Method.GET,
                Helper.PATH_TO_SERVER_GET_SUMMARY_JOBS,
                Summary.class,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(serverRequest);
    }

    public Response.Listener<Summary> createRequestSuccessListener() {
        return new Response.Listener<Summary>() {
            @Override
            public void onResponse(Summary response) {
//                hideProgressDialog();
                try {
                    if (response != null) {
                        // enter main shop page
                        Log.d("summary",response.getTodayAllJobs());
//                        ArrayAdapter<String> colourArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, names);
//                        colourArrayAdapter.setDropDownViewResource( R.layout.simple_spinner_dropdown_item );

                        completed.setText(response.getTodayCompletedJobs());
                        incompleted.setText(response.getTodayIncompletedJobs());
                        pending.setText(response.getTodayAllJobs());
//                        try {
//                            faults.setAdapter(colourArrayAdapter);
//                            faults.setSelection(0);
//                            if(faults != null){
////                index = allCarSizes.indexOf(selectedCarSize);
////                selectCarSize.setSelection(index);
//                            }
//                        }catch (NullPointerException g){
//
//                        }

//                        Log.d("sdfaf", "Json Response " + json);
//                        Helper.displayErrorMessage(ImageUpload.this, "Successfull", "success");
//                        Intent loginUserIntent = new Intent(ImageUpload.this, MachineActivity.class);
//                        loginUserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        loginUserIntent.putExtra("Json",json);
//                        startActivity(loginUserIntent);
//                        finish();
                    } else {
//                        showProgress(false);
//                        Helper.displayErrorMessage(QRActivity.this, "No items", "error");
//                        statusMessage.setText(R.string.barcode_invalid);
//                        barcodeValue.setText(R.string.try_again);
                    }
                } catch (Exception e) {
//                    showProgress(false);
//                    Helper.displayErrorMessage(getContext(), "Invalid shop ID", "error");
                    e.printStackTrace();
                }
            }
        };
    }

    public Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                showProgress(false);
                Helper.displayErrorMessage(getActivity(), "Something went wrong!", "error");
//                hideProgressDialog();
                error.printStackTrace();
            }
        };
    }

}
