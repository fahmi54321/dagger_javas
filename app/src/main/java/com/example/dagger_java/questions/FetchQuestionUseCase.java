package com.example.dagger_java.questions;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.QuestionsListResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;

import java.util.List;
import java.util.concurrent.CancellationException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchQuestionUseCase {
    private final StackoverflowApi stackoverflowApi;

    @Inject
    public FetchQuestionUseCase(StackoverflowApi stackoverflowApi) {
        this.stackoverflowApi = stackoverflowApi;
    }

    public void fetchQuestions(FetchCallback callback){
        try {
            stackoverflowApi.lastActiveQuestions(20).enqueue(new Callback<QuestionsListResponseSchema>() {
                @Override
                public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onResult(new Result.Success(response.body().getQuestions()));
                    } else {
                        callback.onResult(Result.Failure.INSTANCE);
                    }

                }

                @Override
                public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                    callback.onResult(Result.Failure.INSTANCE);
                }
            });
        }catch (Throwable t) {
            if (!(t instanceof CancellationException)) {
                callback.onResult(Result.Failure.INSTANCE);
            } else {
                throw t;
            }
        }
    }

    public static abstract class Result {
        private Result() {}

        public static final class Success extends Result {
            private final List<Question> questions;

            public Success(List<Question> questions) {
                this.questions = questions;
            }

            public List<Question> getQuestions() {
                return questions;
            }
        }

        public static final class Failure extends Result {
            public static final Failure INSTANCE = new Failure();

            private Failure() {}
        }
    }

    public interface FetchCallback {
        void onResult(Result result);
    }
}
