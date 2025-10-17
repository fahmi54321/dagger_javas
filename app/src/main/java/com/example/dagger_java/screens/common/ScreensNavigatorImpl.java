package com.example.dagger_java.screens.common;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;
import com.example.dagger_java.screens.viewmodel.ViewModelActivity;

import javax.inject.Inject;

public class ScreensNavigatorImpl implements ScreensNavigator {

    @Inject
    public ScreensNavigatorImpl(AppCompatActivity activity) {
        this.activity = activity;
    }

    private final AppCompatActivity activity;

    @Override
    public void navigateBack(){
        activity.onBackPressed();
    }

    @Override
    public void toQuestionDetails(String questionId){
        QuestionDetailsActivity.start(activity, questionId);
    }

    @Override
    public void toViewModel() {
        ViewModelActivity.start(activity);
    }
}
