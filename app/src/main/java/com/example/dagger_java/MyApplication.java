package com.example.dagger_java;

import android.app.Application;

import com.example.dagger_java.common.dependencyinjection.app.AppModule;

public class MyApplication extends Application {

    public AppModule appModule;

    public AppModule getAppCompisitionRoot() {
        return appModule;
    }

    @Override
    public void onCreate() {
        appModule = new AppModule(this);
        super.onCreate();
    }
}
