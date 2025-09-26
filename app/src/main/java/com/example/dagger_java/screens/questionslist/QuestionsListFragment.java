package com.example.dagger_java.screens.questionslist;

import android.os.Bundle;
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
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;

public class QuestionsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, QuestionsListViewMvc.Listener, FetchQuestionUseCase.FetchCallback {


    private QuestionsListViewMvc viewMvc;

    private boolean isDataLoaded = false;

    private FetchQuestionUseCase fetchQuestionUseCase;

    private DialogsNavigator dialogsNavigator;

    private ScreensNavigator screensNavigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogsNavigator = getCompositionRoot().getDialogNavigator();

        fetchQuestionUseCase = getCompositionRoot().getFetchQuestionUseCase();

        screensNavigator = getCompositionRoot().getScreensNavigator();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc = new QuestionsListViewMvc(LayoutInflater.from(requireContext()), container);

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

        fetchQuestionUseCase.fetchQuestions(this);
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

