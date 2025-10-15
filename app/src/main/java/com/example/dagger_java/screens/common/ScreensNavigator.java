package com.example.dagger_java.screens.common;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

public interface ScreensNavigator {
    void navigateBack();
    void toQuestionDetails(String questionId);
}
