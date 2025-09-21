package com.example.dagger_java;

import android.app.Application;

import com.example.dagger_java.common.composition.AppCompisitionRoot;

public class MyApplication extends Application {

    public AppCompisitionRoot appCompisitionRoot;

    @Override
    public void onCreate() {
        appCompisitionRoot = new AppCompisitionRoot();
        super.onCreate();
    }
}
