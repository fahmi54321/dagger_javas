package com.example.dagger_java.common.composition;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public static StackoverflowApi getStackoverflowApi() {
        if(AppCompisitionRoot.stackoverflowApi == null){
            stackoverflowApi =  AppCompisitionRoot.getRetrofit().create(StackoverflowApi.class);
        }
        return stackoverflowApi;
    }

    public FetchQuestionUseCase getFetchQuestionUseCase() {
        return new FetchQuestionUseCase(AppCompisitionRoot.getStackoverflowApi());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return new FetchQuestionDetailsUseCase(AppCompisitionRoot.getStackoverflowApi());
    }

}
