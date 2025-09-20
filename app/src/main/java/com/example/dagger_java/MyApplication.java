package com.example.dagger_java;

import android.app.Application;

import com.example.dagger_java.networking.StackoverflowApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public StackoverflowApi stackoverflowApi = retrofit.create(StackoverflowApi.class);

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
