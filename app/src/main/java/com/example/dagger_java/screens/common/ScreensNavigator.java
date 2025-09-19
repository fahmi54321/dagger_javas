package com.example.dagger_java.screens.common;

import android.app.Activity;

import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

public class ScreensNavigator {

    public ScreensNavigator(Activity activity) {
        this.activity = activity;
    }

    private Activity activity;

    public void navigateBack(){
        activity.onBackPressed();
    }
    public void toQuestionDetails(String questionId){
        QuestionDetailsActivity.start(activity, questionId);
    }
}
