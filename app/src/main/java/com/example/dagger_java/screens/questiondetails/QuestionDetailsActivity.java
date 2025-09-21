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
import com.example.dagger_java.MyApplication;
import com.example.dagger_java.networking.SingleQuestionResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.activities.BaseActivity;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.dialogs.ServerErrorDialogFragment;
import com.example.dagger_java.screens.common.toolbar.MyToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionDetailsActivity extends BaseActivity implements MyToolbar.NavigateUpListener, QuestionDetailsMvc.Listener, FetchQuestionDetailsUseCase.FetchCallback {


    private String questionId;

    private QuestionDetailsMvc viewMvc;

    private FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;

    private DialogsNavigator dialogsNavigator;

    private ScreensNavigator screensNavigator;

    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApplication = (MyApplication) getApplication();

        viewMvc = new QuestionDetailsMvc(LayoutInflater.from(this),null);

        setContentView(viewMvc.rootView);

        questionId = getIntent().getStringExtra("EXTRA_QUESTION_ID");

        fetchQuestionDetailsUseCase = getAppCompisitionRoot().getFetchQuestionDetailsUseCase();

        dialogsNavigator = new DialogsNavigator(getSupportFragmentManager());

        screensNavigator = new ScreensNavigator(this);

        fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId, this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestionDetails();
    }

    private void onFetchFailed(){
        dialogsNavigator.showServerErrorDialog();
    }

    private void fetchQuestionDetails() {
        viewMvc.showProgressIndication();
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
        screensNavigator.navigateBack();
    }

    @Override
    public void onResult(FetchQuestionDetailsUseCase.Result result) {
        try {
            if(result instanceof FetchQuestionDetailsUseCase.Result.Success){
                viewMvc.setQuestionBody(((FetchQuestionDetailsUseCase.Result.Success) result).getBody());
            }else if(result instanceof FetchQuestionDetailsUseCase.Result.Failure){
                onFetchFailed();
            }
        }finally {
            viewMvc.hideProgressIndication();
        }
    }
}