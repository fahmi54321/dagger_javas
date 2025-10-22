package com.example.dagger_java.screens.questionslist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.activities.BaseFragment;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.ScreensNavigatorImpl;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, QuestionsListViewMvc.Listener, FetchQuestionUseCase.FetchCallback {


    private QuestionsListViewMvc viewMvc;

    private boolean isDataLoaded = false;

    @Inject
    public FetchQuestionUseCase fetchQuestionUseCase;

    @Inject
    public DialogsNavigator dialogsNavigator;

    @Inject
    public ScreensNavigator screensNavigator;

    @Inject
    public ViewMvcFactory viewMvcFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc = viewMvcFactory.newQuestionsListViewMvc(container);

        return viewMvc.rootView;
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

    private void fetchQuestions() {
        viewMvc.showProgressIndication();
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            fetchQuestionUseCase.fetchQuestions(this);
        }, 2000);
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
    public void toViewModel() {
        screensNavigator.toViewModel();
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

