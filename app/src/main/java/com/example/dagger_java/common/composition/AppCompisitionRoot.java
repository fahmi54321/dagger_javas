package com.example.dagger_java.common.composition;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppCompisitionRoot {
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final StackoverflowApi stackoverflowApi = retrofit.create(StackoverflowApi.class);

    public FetchQuestionUseCase getFetchQuestionUseCase() {
        return new FetchQuestionUseCase(stackoverflowApi);
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

}
