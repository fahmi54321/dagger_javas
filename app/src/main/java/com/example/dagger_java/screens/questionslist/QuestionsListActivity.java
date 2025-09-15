package com.example.dagger_java.screens.questionslist;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.QuestionsListResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.common.dialogs.ServerErrorDialogFragment;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, QuestionsListViewMvc.Listener {

    private StackoverflowApi stackoverflowApi;

    private QuestionsListViewMvc viewMvc;

    private boolean isDataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewMvc = new QuestionsListViewMvc(LayoutInflater.from(this), null);

        setContentView(viewMvc.rootView);

        // init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stackoverflowApi = retrofit.create(StackoverflowApi.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
        if (!isDataLoaded) {
            fetchQuestions();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewMvc.unregisterListener(this);
    }

    @Override
    public void onRefresh() {
        fetchQuestions();
    }

    private void fetchQuestions() {
        viewMvc.showProgressIndication();

        // Retrofit call (asynchronous)
        stackoverflowApi.lastActiveQuestions(20).enqueue(new Callback<QuestionsListResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                viewMvc.hideProgressIndication();
                if (response.isSuccessful() && response.body() != null) {
                    viewMvc.bindQuestions(response.body().getQuestions());
                    isDataLoaded = true;
                } else {
                    onFetchFailed();
                }
            }

            @Override
            public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                viewMvc.hideProgressIndication();
                onFetchFailed();
            }
        });
    }

    private void onFetchFailed(){
        getSupportFragmentManager().beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(),null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onRefreshClicked() {
        fetchQuestions();
    }

    @Override
    public void onQuestionClicked(Question clickedQuestion) {
        QuestionDetailsActivity.start(this, clickedQuestion.getId());
    }
}

