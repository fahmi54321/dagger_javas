package com.example.dagger_java.common.dependencyinjection.app;

import android.app.Application;

import com.example.dagger_java.Constants;
import com.example.dagger_java.common.dependencyinjection.Retrofit1;
import com.example.dagger_java.common.dependencyinjection.Retrofit2;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.networking.UrlProvider;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @AppScope
    @Retrofit1
    public Retrofit retrofit1(UrlProvider urlProvider) {
        return new Retrofit.Builder()
                .baseUrl(urlProvider.baseUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    @Retrofit2
    public Retrofit retrofit2(UrlProvider urlProvider) {
        return new Retrofit.Builder()
                .baseUrl(urlProvider.baseUrl2())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    public UrlProvider urlProvider(){
        return new UrlProvider();
    }

    @Provides
    @AppScope
    public StackoverflowApi stackoverflowApi(@Retrofit1 Retrofit retrofit){
        return retrofit.create(StackoverflowApi.class);
    }
}
