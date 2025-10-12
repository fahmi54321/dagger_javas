package com.example.dagger_java.questions;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.SingleQuestionResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;

import java.util.concurrent.CancellationException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchQuestionDetailsUseCase {

    private final StackoverflowApi stackoverflowApi;


    @Inject
    public FetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        this.stackoverflowApi = stackoverflowApi;
    }

    public void fetchQuestionDetails(String questionId, FetchCallback callback){
        try {
            stackoverflowApi.questionDetails(questionId).enqueue(new Callback<SingleQuestionResponseSchema>() {
                @Override
                public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {

                    if (response.isSuccessful() && response.body() != null) {
                        String questionBody = response.body().getQuestion().getBody();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            callback.onResult(new Result.Success(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)));
                        } else {
                            //noinspection deprecation
                            callback.onResult(new Result.Success(Html.fromHtml(questionBody)));
                        }
                    } else {
                        callback.onResult(Result.Failure.INSTANCE);
                    }
                }

                @Override
                public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable t) {
                    callback.onResult(Result.Failure.INSTANCE);
                }
            });
        }catch (Throwable t){
            if (!(t instanceof CancellationException)) {
                callback.onResult(Result.Failure.INSTANCE);
            } else {
                throw t;
            }
        }
    }

    public static abstract class Result{
        public Result() {
        }

        public static class Success extends Result{
            private final Spanned body;

            public Spanned getBody() {
                return body;
            }

            public Success(Spanned body) {
                this.body = body;
            }
        }

        public static class Failure extends Result{
            public static final Result.Failure INSTANCE = new Result.Failure();

            private Failure() {}
        }
    }

    public interface FetchCallback{
        void onResult(Result result);
    }
}
