package com.etwinkle.solutions.hardwaremanagement.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class CustomSharedPreference {

    private SharedPreferences sharedPref;

    public CustomSharedPreference(Context context) {
        sharedPref = context.getSharedPreferences(Helper.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public SharedPreferences getInstanceOfSharedPreference(){
        return sharedPref;
    }

    //Save user information
    public void setUserData(String userData){
        sharedPref.edit().putString(Helper.USER_DATA, userData).apply();
    }

    public String getUserData(){
        return sharedPref.getString(Helper.USER_DATA, "");
    }

    public void storeNotification(String notificationList){
        sharedPref.edit().putString(Helper.NOTIFICATION_LIST, notificationList).apply();
    }

    public String getStoredNotifications(){
        return sharedPref.getString(Helper.NOTIFICATION_LIST, "");
    }

    public String getTabTitles(){
        return sharedPref.getString(Helper.TAB_TITLE, "");
    }


}
