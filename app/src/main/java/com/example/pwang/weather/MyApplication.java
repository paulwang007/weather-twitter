package com.example.pwang.weather;


import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}