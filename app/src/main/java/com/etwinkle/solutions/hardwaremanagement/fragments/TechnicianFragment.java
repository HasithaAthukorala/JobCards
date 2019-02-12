package com.etwinkle.solutions.hardwaremanagement.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.adapters.JobsAdapterAccept;
import com.etwinkle.solutions.hardwaremanagement.adapters.JobsAdapterAssign;
import com.etwinkle.solutions.hardwaremanagement.models.AcceptJob;
import com.etwinkle.solutions.hardwaremanagement.models.TechJson;
import com.etwinkle.solutions.hardwaremanagement.models.UserObject;
import com.etwinkle.solutions.hardwaremanagement.network.GsonRequest;
import com.etwinkle.solutions.hardwaremanagement.network.VolleySingleton;
import com.etwinkle.solutions.hardwaremanagement.utils.CustomApplication;
import com.etwinkle.solutions.hardwaremanagement.utils.Helper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicianFragment extends Fragment {

    private List<AcceptJob> pendingJobsList;
    private RecyclerView recyclerView;

    public TechnicianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_engineer, container, false);
        UserObject user = ((CustomApplication)getActivity().getApplication()).getLoginUser();
        getDataFromRemoteServer(user.getEmployeeId());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);

        return view;
    }

    public void getDataFromRemoteServer(String id) {

        GsonRequest<TechJson> serverRequest = new GsonRequest<TechJson>(
                Request.Method.GET,
                Helper.PATH_TO_SERVER_GET_TECH_JOBS+"/"+id,
                TechJson.class,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(serverRequest);
    }

    public Response.Listener<TechJson> createRequestSuccessListener() {
        return new Response.Listener<TechJson>() {
            @Override
            public void onResponse(TechJson response) {
//                hideProgressDialog();
                try {
                    if (response != null) {
                        // enter main shop page
                        pendingJobsList = new ArrayList<>();
                        JsonArray jsonElements = response.getAssignTechnicianJobs();
                        for(int i = 0; i < jsonElements.size(); i++) {
                            JsonObject jsonObject = jsonElements.get(i).getAsJsonObject();
//                                AcceptJob pendingJobs = new AcceptJob(removeCommas(jsonObject.get("jobId")), removeCommas(jsonObject.get("description")),
//                                        removeCommas(jsonObject.get("faultImage")), removeCommas(jsonObject.get("departmentName")),
//                                        removeCommas(jsonObject.get("faultName")),
//                                        removeCommas(jsonObject.get("faultCategoryName")), removeCommas(jsonObject.get("date")),removeCommas(jsonObject.get("_id")));
                                AcceptJob acceptJob = new AcceptJob(removeCommas(jsonObject.get("accept")), removeCommas(jsonObject.get("_id")),
                                        removeCommas(jsonObject.get("technicianId")), removeCommas(jsonObject.get("jobId").getAsJsonObject().get("description")));
                                pendingJobsList.add(acceptJob);

//                            Fault fault = new Fault(jsonObject.get("_id").toString(),jsonObject.get("faultName").toString());
                        }
                        JobsAdapterAccept jobsAdapter = new JobsAdapterAccept(getContext(),pendingJobsList);
                        recyclerView.setAdapter(jobsAdapter);
//                        ArrayAdapter<String> colourArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, names);
//                        colourArrayAdapter.setDropDownViewResource( R.layout.simple_spinner_dropdown_item );

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

    public String removeCommas(JsonElement word){
        if(word == null){
            return " ";
        }else {
            return word.toString().substring(1, word.toString().length() - 1);
        }
    }


}
