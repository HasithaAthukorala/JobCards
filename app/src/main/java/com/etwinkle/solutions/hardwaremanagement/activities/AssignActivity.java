package com.etwinkle.solutions.hardwaremanagement.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.models.Assign;
import com.etwinkle.solutions.hardwaremanagement.models.AssignJson;
import com.etwinkle.solutions.hardwaremanagement.network.GsonRequest;
import com.etwinkle.solutions.hardwaremanagement.network.VolleySingleton;
import com.etwinkle.solutions.hardwaremanagement.utils.Helper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AssignActivity extends Activity {
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    private static final int REQUEST_READ_CONTACTS = 0;
    private ImageView imgView;
    private Button upload,cancel;
    private Bitmap bitmap;
    private ProgressDialog dialog;
    Uri imageUri;
    private List<Assign> shopEquipmentsList;
    private  List<String> names;
    private Spinner faults;
    private String filePath;

    MediaPlayer mp=new MediaPlayer();

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        imgView = (ImageView) findViewById(R.id.ImageView);
        upload = (Button) findViewById(R.id.imguploadbtn);
        cancel = (Button) findViewById(R.id.imgcancelbtn);
        faults = (Spinner) findViewById(R.id.fault);

        getDataFromRemoteServer();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

        }else {
            if ((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {

            }else {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, REQUEST_READ_CONTACTS);
            }
        }

        upload.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (bitmap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Please select image", Toast.LENGTH_SHORT).show();
                } else {
//                    dialog = ProgressDialog.show(ImageUpload.this, "Uploading",
//                            "Please wait...", true);
                    Log.d("safaff",filePath);
                    Ion.with(getApplicationContext())
                            .load(Helper.PATH_TO_SERVER_UPLOAD_PATH)
//                            .uploadProgressBar(uploadProgressBar)
                            .setMultipartParameter("goop", "noop")
                            .setMultipartFile("faultImage", "image/jpg", new File(filePath))
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    // do stuff with the result or error
                                    try {
                                        Log.d("safaf", result.toString());
                                    }catch (NullPointerException s){}
                                    try {
                                        Log.d("safafaa", e.toString());
                                    }catch (NullPointerException s){}
                                }
                            });
//                    new ImageUploadTask().execute();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                ImageUpload.this.finish();
                //define the file-name to save photo taken by Camera activity
                String fileName = "new-photo-name.jpg";
                //create parameters for Intent with filename
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
                //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                //create new Intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, PICK_Camera_IMAGE);
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = "new-photo-name.jpg";
                //create parameters for Intent with filename
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
                //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                //create new Intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, PICK_Camera_IMAGE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:
                //define the file-name to save photo taken by Camera activity
                String fileName = "new-photo-name.jpg";
                //create parameters for Intent with filename
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
                //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                //create new Intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, PICK_Camera_IMAGE);
                return true;

            case R.id.gallery:
                try {
                    Intent gintent = new Intent();
                    gintent.setType("image/*");
                    gintent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(
                            Intent.createChooser(gintent, "Select Picture"),
                            PICK_IMAGE);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.e(e.getClass().getName(), e.getMessage(), e);
                }
                return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = null;
        filePath = null;
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImageUri = data.getData();
                }
                break;
            case PICK_Camera_IMAGE:
                if (resultCode == RESULT_OK) {
                    //use imageUri here to access the image
                    selectedImageUri = imageUri;
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        if(selectedImageUri != null){
            try {
                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
                String selectedImagePath = getPath(selectedImageUri);

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast.makeText(getApplicationContext(), "Unknown path",
                            Toast.LENGTH_LONG).show();
                    Log.e("Bitmap", "Unknown path");
                }

                if (filePath != null) {
                    decodeFile(filePath);
                } else {
                    bitmap = null;
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Internal error",
                        Toast.LENGTH_LONG).show();
                Log.e(e.getClass().getName(), e.getMessage(), e);
            }
        }

    }

//    class ImageUploadTask extends AsyncTask<void, void,="" string=""> {
//        @SuppressWarnings("unused")
//        @Override
//        protected String doInBackground(Void... unsued) {
//            InputStream is;
//            BitmapFactory.Options bfo;
//            Bitmap bitmapOrg;
//            ByteArrayOutputStream bao ;
//
//            bfo = new BitmapFactory.Options();
//            bfo.inSampleSize = 2;
////bitmapOrg = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + customImage, bfo);
//
//            bao = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
//            byte [] ba = bao.toByteArray();
//            String ba1 = Base64.encodeBytes(ba);
//            ArrayList nameValuePairs = new ArrayList();
//            nameValuePairs.add(new BasicNameValuePair("image",ba1));
//            nameValuePairs.add(new BasicNameValuePair("cmd","image_android"));
//            Log.v("log_tag", System.currentTimeMillis()+".jpg");
//            try{
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpPost httppost = new
////  Here you need to put your server file address
//                        HttpPost("http://www.picsily.com/upload_photo.php");
//                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//                HttpResponse response = httpclient.execute(httppost);
//                HttpEntity entity = response.getEntity();
//                is = entity.getContent();
//                Log.v("log_tag", "In the try Loop" );
//            }catch(Exception e){
//                Log.v("log_tag", "Error in http connection "+e.toString());
//            }
//            return "Success";
//// (null);
//        }
//
//
//        @Override
//        protected void onPostExecute(String sResponse) {
//            try {
//                if (dialog.isShowing())
//                    dialog.dismiss();
//            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(),
//                        e.getMessage(),
//                        Toast.LENGTH_LONG).show();
//                Log.e(e.getClass().getName(), e.getMessage(), e);
//            }
//        }
//
//    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public void decodeFile(String filePath) {
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);

        imgView.setImageBitmap(bitmap);

    }

    public void getDataFromRemoteServer() {

        GsonRequest<AssignJson> serverRequest = new GsonRequest<AssignJson>(
                Request.Method.GET,
                Helper.PATH_TO_SERVER_GET_FAULTS,
                AssignJson.class,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(AssignActivity.this).addToRequestQueue(serverRequest);
    }

    public Response.Listener<AssignJson> createRequestSuccessListener() {
        return new Response.Listener<AssignJson>() {
            @Override
            public void onResponse(AssignJson response) {
//                hideProgressDialog();
                try {


                    if (response != null) {
                        // enter main shop page
                        shopEquipmentsList = new ArrayList<>();
                        names = new ArrayList<>();
                        JsonArray jsonElements = response.getDetails();
                        for(int i = 0; i < jsonElements.size(); i++) {
                            JsonObject jsonObject = jsonElements.get(i).getAsJsonObject();
                            Assign fault = new Assign(removeCommas(jsonObject.get("technicianId").getAsJsonObject().get("_id").toString()),removeCommas(jsonObject.get("technicianId").getAsJsonObject().get("firstName").toString()),removeCommas(jsonObject.get("_id").toString()));
                            shopEquipmentsList.add(fault);
                            names.add(removeCommas(jsonObject.get("faultName").toString()));
                        }

                        ArrayAdapter<String> colourArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, names);
                        colourArrayAdapter.setDropDownViewResource( R.layout.simple_spinner_dropdown_item );

                        try {
                            faults.setAdapter(colourArrayAdapter);
                            faults.setSelection(0);
                            if(faults != null){
//                index = allCarSizes.indexOf(selectedCarSize);
//                selectCarSize.setSelection(index);
                            }
                        }catch (NullPointerException g){

                        }

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
//                showProgress(false);
                Helper.displayErrorMessage(AssignActivity.this, "Something went wrong!", "error");
//                hideProgressDialog();
                error.printStackTrace();
            }
        };
    }

    public String removeCommas(String word){
        return  word.substring(1, word.length() - 1);
    }

}
