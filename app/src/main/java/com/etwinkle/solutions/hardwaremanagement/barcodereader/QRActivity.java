/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.etwinkle.solutions.hardwaremanagement.barcodereader;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.activities.MachineActivity;
import com.etwinkle.solutions.hardwaremanagement.activities.ShopActivity;
import com.etwinkle.solutions.hardwaremanagement.barcodereader.QRActivity;
import com.etwinkle.solutions.hardwaremanagement.models.Machine;
import com.etwinkle.solutions.hardwaremanagement.models.MachineData;
import com.etwinkle.solutions.hardwaremanagement.models.UserObject;
import com.etwinkle.solutions.hardwaremanagement.network.GsonRequest;
import com.etwinkle.solutions.hardwaremanagement.network.VolleySingleton;
import com.etwinkle.solutions.hardwaremanagement.utils.CustomApplication;
import com.etwinkle.solutions.hardwaremanagement.utils.Helper;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class QRActivity extends AppCompatActivity implements View.OnClickListener {

    // use a compound button so either checkbox or switch widgets work.
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;

    private View mProgressView;
    private View mProgressViewRelative;
    private View mBarcodeFormView;

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        getSupportActionBar().hide();

        statusMessage = (TextView) findViewById(R.id.status_message);
        barcodeValue = (TextView) findViewById(R.id.barcode_value);

        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);

        mProgressView = (View) findViewById(R.id.barcode_progress);
        mProgressViewRelative = (View) findViewById(R.id.barcode_progress_relative);
        mBarcodeFormView = (View) findViewById(R.id.barcode_view);

        autoFocus.setChecked(true);

        findViewById(R.id.read_barcode).setOnClickListener(this);

        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
        intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
        startActivityForResult(intent, RC_BARCODE_CAPTURE);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }

    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
//                    statusMessage.setText(R.string.barcode_success);
//                    barcodeValue.setText(barcode.displayValue);
//                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                    showProgress(true);
                    getDataFromRemoteServer(barcode.displayValue);
                } else {
                    statusMessage.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getDataFromRemoteServer(String shopID) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(Helper.SERIAL_NUMBER, shopID);

        GsonRequest<Machine> serverRequest = new GsonRequest<Machine>(
                Request.Method.POST,
                Helper.PATH_TO_SERVER_CHECK_SERIAL_NUMBER,
                Machine.class,
                params,
                createRequestSuccessListener(shopID),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(QRActivity.this).addToRequestQueue(serverRequest);
    }

    public Response.Listener<Machine> createRequestSuccessListener(final String shopID) {
        return new Response.Listener<Machine>() {
            @Override
            public void onResponse(Machine response) {
//                hideProgressDialog();
                try {


                    if (response != null) {
                        // enter main shop page
                        List<Machine> shopEquipmentsList = new ArrayList<>();
//                        for(int i = 0; i < response.length; i++){
//                            shopEquipmentsList.add(response[i]);
//                        }

                        JsonObject jsonObject = response.getUnique();
                        MachineData machineData = new MachineData(jsonObject.get("_id").toString(),jsonObject.get("serialNumber").toString(),
                                jsonObject.get("location").toString(),jsonObject.get("supervisorId").toString(),
                                jsonObject.get("departmentId").getAsJsonObject().get("departmentName").toString(),
                                jsonObject.get("departmentId").getAsJsonObject().get("_id").toString());

                        String json = ((CustomApplication) getApplication()).getGsonObject().toJson(machineData);

                        Log.d(TAG, "Json Response " + json);
                        Helper.displayErrorMessage(QRActivity.this, "Successfull", "success");
                        Intent loginUserIntent = new Intent(QRActivity.this, MachineActivity.class);
                        loginUserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        loginUserIntent.putExtra("Json",json);
                        startActivity(loginUserIntent);
                        finish();
                    } else {
                        showProgress(false);
                        Helper.displayErrorMessage(QRActivity.this, "No items", "error");
                        statusMessage.setText(R.string.barcode_invalid);
                        barcodeValue.setText(R.string.try_again);
                    }
                } catch (Exception e) {
                    showProgress(false);
                    Helper.displayErrorMessage(getApplicationContext(), "Invalid shop ID", "error");
                    e.printStackTrace();
                }
            }
        };
    }

    public Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showProgress(false);
                Helper.displayErrorMessage(QRActivity.this, "Something went wrong!", "error");
//                hideProgressDialog();
                error.printStackTrace();
            }
        };
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mBarcodeFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mBarcodeFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mBarcodeFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressViewRelative.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressViewRelative.setVisibility(show ? View.VISIBLE : View.GONE);
            mBarcodeFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
