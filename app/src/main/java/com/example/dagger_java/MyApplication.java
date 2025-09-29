package com.example.dagger_java;

import android.app.Application;

import com.example.dagger_java.common.dependencyinjection.AppCompisitionRoot;

public class MyApplication extends Application {

    public AppCompisitionRoot appCompisitionRoot;

    public AppCompisitionRoot getAppCompisitionRoot() {
        return appCompisitionRoot;
    }

    @Override
    public void onCreate() {
        appCompisitionRoot = new AppCompisitionRoot(this);
        super.onCreate();
    }
}
