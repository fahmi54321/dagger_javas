package com.example.dagger_java.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.Constants;
import com.example.dagger_java.R;
import com.example.dagger_java.networking.SingleQuestionResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.screens.common.dialogs.ServerErrorDialogFragment;
import com.example.dagger_java.screens.common.toolbar.MyToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionDetailsActivity extends AppCompatActivity implements MyToolbar.NavigateUpListener {

    private MyToolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView txtQuestionBody;
    private StackoverflowApi stackoverflowApi;
    private String questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_question_details);

        txtQuestionBody = findViewById(R.id.txt_question_body);

        // init toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigateUpListener(this);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setEnabled(true);

        // inti retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stackoverflowApi = retrofit.create(StackoverflowApi.class);

        // retrieve question ID passed from outside
        questionId = getIntent().getStringExtra("EXTRA_QUESTION_ID");

        if(questionId != null){
            Log.v("questionId",questionId);
        }else{
            Log.v("questionId","null");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestionDetails();
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
        swipeRefreshLayout.setRefreshing(false);
    }

    private void fetchQuestionDetails() {
        showProgressIndication();

        stackoverflowApi.questionDetails(questionId).enqueue(new Callback<SingleQuestionResponseSchema>() {
            @Override
            public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {
                hideProgressIndication();

                if (response.isSuccessful() && response.body() != null) {
                    String questionBody = response.body().getQuestion().getBody();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        txtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        //noinspection deprecation
                        txtQuestionBody.setText(Html.fromHtml(questionBody));
                    }
                } else {
                    onFetchFailed();
                }
            }

            @Override
            public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable t) {
                hideProgressIndication();
                onFetchFailed();
            }
        });
    }


    @Override
    public void onNavigationUpClicked() {
        onBackPressed();
    }

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

}