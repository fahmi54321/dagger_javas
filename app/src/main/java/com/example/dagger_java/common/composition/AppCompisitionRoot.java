package com.example.dagger_java.common.composition;

import androidx.annotation.UiThread;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.StackoverflowApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@UiThread
public class AppCompisitionRoot {

    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static StackoverflowApi stackoverflowApi;

    public StackoverflowApi getStackoverflowApi() {
        if(AppCompisitionRoot.stackoverflowApi == null){
            stackoverflowApi =  AppCompisitionRoot.getRetrofit().create(StackoverflowApi.class);
        }
        return stackoverflowApi;
    }

}
