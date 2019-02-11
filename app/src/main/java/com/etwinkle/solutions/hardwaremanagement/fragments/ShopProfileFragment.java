package com.etwinkle.solutions.hardwaremanagement.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.activities.LoginActivity;
import com.etwinkle.solutions.hardwaremanagement.models.Shop;
import com.etwinkle.solutions.hardwaremanagement.network.GsonRequest;
import com.etwinkle.solutions.hardwaremanagement.network.VolleySingleton;
import com.etwinkle.solutions.hardwaremanagement.utils.CustomApplication;
import com.etwinkle.solutions.hardwaremanagement.utils.Helper;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopProfileFragment extends Fragment {

    private static String shopID;
    private TextView shopName,lastPendingBill,lastPendingBillDate, chequeReturn, creditLimit, size, riskScore, status;
    private ImageView photo;


    public ShopProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop_profile, container, false);
        shopName = (TextView) v.findViewById(R.id.s_name);
        lastPendingBill = (TextView) v.findViewById(R.id.last_pending_bill);
        lastPendingBillDate = (TextView) v.findViewById(R.id.last_pending_bill_date);
        chequeReturn = (TextView) v.findViewById(R.id.cheque_return);
        creditLimit = (TextView) v.findViewById(R.id.credit_limit);
        size = (TextView) v.findViewById(R.id.size);
        riskScore = (TextView) v.findViewById(R.id.risk_score);
        status = (TextView) v.findViewById(R.id.status);
        photo = (ImageView) v.findViewById(R.id.profile_image);
        getShopDetailsFromServer(shopID);
        return v;
    }

    private void getShopDetailsFromServer(String shopID) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(Helper.SHOPID, shopID);

        GsonRequest<Shop> serverRequest = new GsonRequest<Shop>(
                Request.Method.POST,
                Helper.PATH_TO_SERVER_GET_SHOP_DETAILS,
                Shop.class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getContext()).addToRequestQueue(serverRequest);
    }

    private Response.Listener<Shop> createRequestSuccessListener() {
        return new Response.Listener<Shop>() {
            @Override
            public void onResponse(Shop response) {
//                hideProgressDialog();
                try {

                    if(response != null){
                        Glide.with(getContext()).load(Helper.PUBLIC_IMAGE_FOLDER+response.getPhoto()).into(photo);
                        shopName.setText(response.getShopName());
                        lastPendingBill.setText(response.getLastPendingBill());
                        lastPendingBillDate.setText(response.getLastPendingBillDate());
                        chequeReturn.setText(response.getChequeReturn());
                        creditLimit.setText(response.getCreditLimit());
                        size.setText(response.getSize());
                        riskScore.setText(response.getRiskScore());
                        status.setText(response.getStatus());
                        String dateString = response.getLastPendingBillDate();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date;
                        try {
                            date = df.parse(dateString);
                            Timestamp datetime = new Timestamp(date.getTime());
                            Date date1 = new Date(datetime.getTime());
                            dateString = date1.toString();
                            lastPendingBillDate.setText(dateString);
                        } catch (ParseException e) {
                        }
                    }
                } catch (Exception e) {
                    Helper.displayErrorMessage(getActivity(), "Something went wrong", "error");
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Helper.displayErrorMessage(getContext(), "Something went wrong!", "error");
//                hideProgressDialog();
                error.printStackTrace();
            }
        };
    }

    public static String getShopID() {
        return shopID;
    }

    public static void setShopID(String shopID) {
        ShopProfileFragment.shopID = shopID;
    }
}
