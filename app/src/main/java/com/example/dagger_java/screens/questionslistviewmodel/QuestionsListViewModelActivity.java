package com.example.dagger_java.screens.questionslistviewmodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.activities.BaseActivity;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionsListViewModelActivity extends BaseActivity implements QuestionsListView.Listener, SwipeRefreshLayout.OnRefreshListener {

    private QuestionsListView viewMvc;
    private QuestionsListViewModel viewModelQuestionsList;

    private boolean isDataLoaded = false;

    @Inject
    public DialogsNavigator dialogsNavigator;

    @Inject
    public ScreensNavigator screensNavigator;

    @Inject
    public ViewMvcFactory viewMvcFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMvc = viewMvcFactory.newQuestionsListView(null);
        setContentView(viewMvc.rootView);

        initViewModel();
        observer();

    }

    private void initViewModel(){
        viewModelQuestionsList = new ViewModelProvider(this).get(QuestionsListViewModel.class);
    }

    private void observer(){
        viewModelQuestionsList.questions().observe(this, questions -> viewMvc.bindQuestions(questions));

        viewModelQuestionsList.isDataLoaded().observe(this, data -> isDataLoaded = data);

        viewModelQuestionsList.hideProgressIndication().observe(this, data -> {
            if(data){
                viewMvc.hideProgressIndication();
            }
        });

        viewModelQuestionsList.isFetchFailed().observe(this, isFailed -> {
            if(isFailed){
                onFetchFailed();
            }
        });
    }

    private void fetchQuestions() {
        viewMvc.showProgressIndication();
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            viewModelQuestionsList.fetchQuestion();
        }, 3000);

    }

    private void onFetchFailed(){
        dialogsNavigator.showServerErrorDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.registerListener(this);

        if (!isDataLoaded) {
            fetchQuestions();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }

    @Override
    public void onRefresh() {
        fetchQuestions();
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
    public void toViewModel() {
        screensNavigator.toViewModel();
    }
}

