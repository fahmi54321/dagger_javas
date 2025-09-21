package com.example.dagger_java;

import android.app.Application;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private StackoverflowApi stackoverflowApi = retrofit.create(StackoverflowApi.class);

    public FetchQuestionUseCase getFetchQuestionUseCase() {
        return new FetchQuestionUseCase(stackoverflowApi);
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
