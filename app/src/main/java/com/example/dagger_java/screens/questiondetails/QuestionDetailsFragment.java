package com.example.dagger_java.screens.questiondetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.screens.activities.BaseFragment;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.toolbar.MyToolbar;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;

import javax.inject.Inject;

public class QuestionDetailsFragment extends BaseFragment implements MyToolbar.NavigateUpListener, QuestionDetailsMvc.Listener, FetchQuestionDetailsUseCase.FetchCallback {


    private String questionId;

    private QuestionDetailsMvc viewMvc;

    @Inject
    public FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;

    @Inject
    public DialogsNavigator dialogsNavigator;

    @Inject
    public ScreensNavigator screensNavigator;

    @Inject
    public ViewMvcFactory viewMvcFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questionId = requireActivity().getIntent().getStringExtra("EXTRA_QUESTION_ID");

        injector().inject(this);

        fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId, this);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc = viewMvcFactory.newQuestionDetailsMvc(container);

        return viewMvc.rootView;
    }

    @Override
    public void onStart() {
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
        requireActivity().onBackPressed();
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