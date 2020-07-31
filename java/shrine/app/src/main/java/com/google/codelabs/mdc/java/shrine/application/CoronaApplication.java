package com.google.codelabs.mdc.java.shrine.application;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;

public class CoronaApplication extends Application {
    private static CoronaApplication instance;
    private static Context appContext;

    public static CoronaApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context mAppContext) {
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}