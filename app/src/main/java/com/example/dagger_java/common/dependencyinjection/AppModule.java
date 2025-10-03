package com.example.dagger_java.common.dependencyinjection;

import android.app.Application;

import androidx.annotation.UiThread;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.StackoverflowApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static Retrofit retrofit;

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static StackoverflowApi _stackoverflowApi;

    public StackoverflowApi stackoverflowApi() {
        if(AppModule._stackoverflowApi == null){
            _stackoverflowApi =  AppModule.getRetrofit().create(StackoverflowApi.class);
        }
        return _stackoverflowApi;
    }

    @Provides
    public StackoverflowApi getStackoverflowApi(){
        return stackoverflowApi();
    }

    @Provides
    public Application getApplication() {
        return application;
    }
}
