package com.example.dagger_java.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.Constants;
import com.example.dagger_java.networking.SingleQuestionResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.screens.common.dialogs.ServerErrorDialogFragment;
import com.example.dagger_java.screens.common.toolbar.MyToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionDetailsActivity extends AppCompatActivity implements MyToolbar.NavigateUpListener, QuestionDetailsMvc.Listener {

    private StackoverflowApi stackoverflowApi;
    private String questionId;

    private QuestionDetailsMvc viewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewMvc = new QuestionDetailsMvc(LayoutInflater.from(this),null);
        setContentView(viewMvc.rootView);

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

    private void fetchQuestionDetails() {
        viewMvc.showProgressIndication();

        stackoverflowApi.questionDetails(questionId).enqueue(new Callback<SingleQuestionResponseSchema>() {
            @Override
            public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {
                viewMvc.hideProgressIndication();

                if (response.isSuccessful() && response.body() != null) {
                    String questionBody = response.body().getQuestion().getBody();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        viewMvc.setQuestionBody(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        //noinspection deprecation
                        viewMvc.setQuestionBody(Html.fromHtml(questionBody));
                    }
                } else {
                    onFetchFailed();
                }
            }

            @Override
            public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable t) {
                viewMvc.hideProgressIndication();
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

    @Override
    public void onBack() {
        onBackPressed();
    }
}