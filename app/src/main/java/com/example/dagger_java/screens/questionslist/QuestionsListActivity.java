package com.example.dagger_java.screens.questionslist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.Constants;
import com.example.dagger_java.R;
import com.example.dagger_java.networking.QuestionsListResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.common.dialogs.ServerErrorDialogFragment;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, QuestionsAdapter.OnQuestionClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private QuestionsAdapter questionsAdapter;
    private StackoverflowApi stackoverflowApi;

    private boolean isDataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questions_list);

        // init pull-down-to-refresh
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        // init recycler view
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionsAdapter = new QuestionsAdapter(this);
        recyclerView.setAdapter(questionsAdapter);

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
        if (!isDataLoaded) {
            fetchQuestions();
        }
    }

    @Override
    public void onRefresh() {
        fetchQuestions();
    }

    private void fetchQuestions() {
        showProgressIndication();

        // Retrofit call (asynchronous)
        stackoverflowApi.lastActiveQuestions(20).enqueue(new Callback<QuestionsListResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                hideProgressIndication();
                if (response.isSuccessful() && response.body() != null) {
                    questionsAdapter.bindData(response.body().getQuestions());
                    isDataLoaded = true;
                } else {
                    onFetchFailed();
                }
            }

            @Override
            public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                hideProgressIndication();
                onFetchFailed();
            }
        });
    }

    private void onFetchFailed(){
        getSupportFragmentManager().beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(),null)
                .commitAllowingStateLoss();
    }

    private void showProgressIndication(){
        swipeRefreshLayout.setRefreshing(true);
    }

    private void hideProgressIndication(){
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onQuestionClick(Question question) {
        QuestionDetailsActivity.start(this, question.getId());
    }
}

