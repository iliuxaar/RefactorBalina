package com.example.iliuxa.refactorbalina.application;

import android.app.Application;

import com.example.iliuxa.refactorbalina.model.HelperFactory;


public class MyApplication extends Application {
    public static final String TAG = "ERROR";

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }
}
