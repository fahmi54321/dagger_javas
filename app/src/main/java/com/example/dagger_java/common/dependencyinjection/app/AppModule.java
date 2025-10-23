package com.example.dagger_java.common.dependencyinjection.app;

import com.example.dagger_java.common.dependencyinjection.Retrofit1;
import com.example.dagger_java.common.dependencyinjection.Retrofit2;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.networking.UrlProvider;

import javax.inject.Singleton;

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
    @Singleton
    @Retrofit1
    public Retrofit retrofit1(UrlProvider urlProvider) {
        return new Retrofit.Builder()
                .baseUrl(urlProvider.baseUrl1())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Retrofit2
    public Retrofit retrofit2(UrlProvider urlProvider) {
        return new Retrofit.Builder()
                .baseUrl(urlProvider.baseUrl2())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public UrlProvider urlProvider(){
        return new UrlProvider();
    }

    @Provides
    @Singleton
    public StackoverflowApi stackoverflowApi(@Retrofit1 Retrofit retrofit){
        return retrofit.create(StackoverflowApi.class);
    }
}
