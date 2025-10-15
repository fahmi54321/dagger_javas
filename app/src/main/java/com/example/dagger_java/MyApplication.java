package com.example.dagger_java;

import android.app.Application;

import com.example.dagger_java.common.dependencyinjection.app.AppComponent;
import com.example.dagger_java.common.dependencyinjection.app.AppModule;
import com.example.dagger_java.common.dependencyinjection.app.DaggerAppComponent;

public class MyApplication extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent(){
        if(appComponent == null){
            appComponent = DaggerAppComponent
                    .builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
