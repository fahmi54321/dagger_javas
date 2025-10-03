package com.example.dagger_java.common.dependencyinjection.app;

import android.app.Application;

import com.example.dagger_java.networking.StackoverflowApi;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    StackoverflowApi getStackoverflowApi();

    Application getApplication();

}
