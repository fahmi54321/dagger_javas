package com.example.dagger_java.common.dependencyinjection.app;

import android.app.Application;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.StackoverflowApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @AppScope
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    public StackoverflowApi stackoverflowApi(Retrofit retrofit){
        return retrofit.create(StackoverflowApi.class);
    }

    @Provides
    public Application getApplication() {
        return application;
    }
}
