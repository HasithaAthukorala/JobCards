package com.etwinkle.solutions.hardwaremanagement.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.etwinkle.solutions.hardwaremanagement.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Helper {

    private static final String TAG = Helper.class.getSimpleName();

    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String SHOPID = "shopID";
    public static final String SERIAL_NUMBER = "serialNumber";
    public static final String BALANCE = "balance";
    public static final String SOLD = "sold";
    public static final String EQUIPID = "equipID";

    public static final String PUBLIC_PATH = "https://localhost:5000";

    public static final String PUBLIC_IMAGE_FOLDER = "https://card1.herokuapp.com/mobile/";
    public static final String PUBLIC_API_FOLDER = "https://card1.herokuapp.com/api/";

    public static final String PATH_TO_SERVER_LOGIN = PUBLIC_API_FOLDER + "login";
    public static final String PATH_TO_SERVER_SHOP_EQUIPMENTS = PUBLIC_API_FOLDER + "machines/check/serialNumber";
    public static final String PATH_TO_SERVER_GET_FAULTS = PUBLIC_API_FOLDER + "faults";
    public static final String PATH_TO_SERVER_GET_ATTENDS = PUBLIC_API_FOLDER + "attends";
    public static final String PATH_TO_SERVER_ASSIGN_TECH = PUBLIC_API_FOLDER + "assignTechnicians";
    public static final String PATH_TO_SERVER_GET_PENDING_JOBS = PUBLIC_API_FOLDER + "assignTechnicians/pending";
    public static final String PATH_TO_SERVER_GET_TECH_JOBS = PUBLIC_API_FOLDER + "assignTechnicians/technician";
    public static final String PATH_TO_SERVER_GET_TECH_ACCEPT = PUBLIC_API_FOLDER + "assignTechnicians/accept";
    public static final String PATH_TO_SERVER_GET_INCOMPLETE_JOBS = PUBLIC_API_FOLDER + "solves/incomplete";
    public static final String PATH_TO_SERVER_GET_COMPLETE_JOBS = PUBLIC_API_FOLDER + "solves/complete";
    public static final String PATH_TO_SERVER_UPLOAD_PATH = PUBLIC_API_FOLDER + "jobs";
    public static final String PATH_TO_SERVER_CHECK_SERIAL_NUMBER = PUBLIC_API_FOLDER + "machines/check/serialNumber";
    public static final String PATH_TO_SERVER_UPDATE_SHOP_EQUIPMENTS = PUBLIC_API_FOLDER + "updateShopEquipments.php";
    public static final String PATH_TO_SERVER_GET_SHOP_DETAILS = PUBLIC_API_FOLDER + "getShopDetails.php";


    public static final int MY_SOCKET_TIMEOUT_MS = 1000;

    public static final String USER_DATA = "USER_DATA";
    public static final String SHARED_PREF = "SHARED_PREFERENCE";
    public static final String NOTIFICATION_LIST = "NOTIFY";
    public static final String TAB_TITLE = "TAB_TITLE";

    public static void displayErrorMessage(Context context, String message, String type){
        if(type.equals("error")){
            MDToast mdToast = MDToast.makeText(context, message, MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
            mdToast.show();
        }else if(type.equals("info")){
            MDToast mdToast = MDToast.makeText(context, message, MDToast.LENGTH_LONG, MDToast.TYPE_INFO);
            mdToast.show();
        }else if(type.equals("success")){
            MDToast mdToast = MDToast.makeText(context, message, MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
            mdToast.show();
        }else if(type.equals("warning")){
            MDToast mdToast = MDToast.makeText(context, message, MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
            mdToast.show();
        }else {
            StyleableToast.makeText(context, message, Toast.LENGTH_LONG, R.style.mytoast).show();
        }

        //Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String convertDateToString(Date date) {
        String dateStr = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            dateStr = df.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateStr;
    }

    public static Date convertStringToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date convertDate = null;
        try {
            convertDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertDate;
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static int getPriceDiscount(int price, int discount){
        int realDiscount = ((price * discount) / 100);
        return  realDiscount;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showMessageDialog(Context context, String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static String numberFormatter(String number){
        try {
            NumberFormat formatter = new DecimalFormat("#,###.##");
            double myNumber = Double.valueOf(number);
            String formattedNumber = formatter.format(myNumber);
            return formattedNumber;
        }catch (NumberFormatException f){
            return number;
        }
    }
}
