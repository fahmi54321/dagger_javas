package com.example.dagger_java.screens.common;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.common.dependencyinjection.activity.ActivityScope;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

import javax.inject.Inject;

public class ScreensNavigator {

    public ScreensNavigator(AppCompatActivity activity) {
        this.activity = activity;
    }

    private final AppCompatActivity activity;

    public void navigateBack(){
        activity.onBackPressed();
    }
    public void toQuestionDetails(String questionId){
        QuestionDetailsActivity.start(activity, questionId);
    }
}
