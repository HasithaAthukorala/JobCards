package com.etwinkle.solutions.hardwaremanagement.utils;

import android.app.Application;

import com.etwinkle.solutions.hardwaremanagement.models.UserObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class CustomApplication extends Application {

    private Gson gson;
    private GsonBuilder builder;

    private CustomSharedPreference shared;

    @Override
    public void onCreate() {
        super.onCreate();
        builder = new GsonBuilder();
        gson = builder.create();
        shared = new CustomSharedPreference(getApplicationContext());
    }

    public CustomSharedPreference getShared(){
        return shared;
    }

    public Gson getGsonObject(){
        return gson;
    }

    /**
     * get current login user
     * @return
     */
    public UserObject getLoginUser(){
        Gson mGson = getGsonObject();
        String storedUser = getShared().getUserData();
        return mGson.fromJson(storedUser, UserObject.class);
    }
}