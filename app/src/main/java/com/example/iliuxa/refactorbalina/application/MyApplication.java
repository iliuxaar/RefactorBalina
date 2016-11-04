package com.example.iliuxa.refactorbalina.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.iliuxa.refactorbalina.model.DataBaseFactory;


public class MyApplication extends Application {
    public static final String TAG = "ERROR";

    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseFactory.setHelper(getApplicationContext());
    }

    public static  boolean isNetworkAvailable(Context context) {
        if(context == null) { return false; }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
