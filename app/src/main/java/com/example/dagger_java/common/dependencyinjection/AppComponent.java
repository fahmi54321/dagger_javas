package com.example.dagger_java.common.dependencyinjection;

import android.app.Application;

import com.example.dagger_java.networking.StackoverflowApi;

import dagger.Component;
import dagger.Provides;

@Component(modules = {AppModule.class})
public interface AppComponent {

    StackoverflowApi getStackoverflowApi();

    Application getApplication();

}
