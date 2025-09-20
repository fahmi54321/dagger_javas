package com.example.dagger_java.screens.questionslist;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.Constants;
import com.example.dagger_java.MyApplication;
import com.example.dagger_java.networking.QuestionsListResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.dialogs.ServerErrorDialogFragment;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, QuestionsListViewMvc.Listener, FetchQuestionUseCase.FetchCallback {


    private QuestionsListViewMvc viewMvc;

    private boolean isDataLoaded = false;

    private FetchQuestionUseCase fetchQuestionUseCase;

    private DialogsNavigator dialogsNavigator;

    private ScreensNavigator screensNavigator;

    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApplication = (MyApplication) getApplication();

        viewMvc = new QuestionsListViewMvc(LayoutInflater.from(this), null);

        setContentView(viewMvc.rootView);

        dialogsNavigator = new DialogsNavigator(getSupportFragmentManager());

        fetchQuestionUseCase = new FetchQuestionUseCase(this,myApplication.stackoverflowApi);

        screensNavigator = new ScreensNavigator(this);

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
    protected void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }

    @Override
    public void onRefresh() {
        fetchQuestions();
    }

    private void fetchQuestions() {
        viewMvc.showProgressIndication();

        fetchQuestionUseCase.fetchQuestions();
    }

    private void onFetchFailed(){
        dialogsNavigator.showServerErrorDialog();
    }

    @Override
    public void onRefreshClicked() {
        fetchQuestions();
    }

    @Override
    public void onQuestionClicked(Question clickedQuestion) {
        screensNavigator.toQuestionDetails(clickedQuestion.getId());
    }

    @Override
    public void onResult(FetchQuestionUseCase.Result result) {
        try {
            if(result instanceof FetchQuestionUseCase.Result.Success){
                viewMvc.bindQuestions(((FetchQuestionUseCase.Result.Success) result).getQuestions());
                isDataLoaded = true;

            }else if(result instanceof FetchQuestionUseCase.Result.Failure){
                onFetchFailed();
            }
        }finally {
            viewMvc.hideProgressIndication();
        }
    }
}

