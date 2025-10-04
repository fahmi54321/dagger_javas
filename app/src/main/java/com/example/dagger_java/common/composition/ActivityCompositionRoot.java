package com.example.dagger_java.common.composition;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;

public class ActivityCompositionRoot {

    private final AppCompatActivity activity;
    private final AppCompisitionRoot appCompisitionRoot;
    private ScreensNavigator screensNavigator;

    public ActivityCompositionRoot(AppCompatActivity activity, AppCompisitionRoot appCompisitionRoot) {
        this.activity = activity;
        this.appCompisitionRoot = appCompisitionRoot;
    }

    private StackoverflowApi getStackoverflowApi() {
        return appCompisitionRoot.getStackoverflowApi();
    }

    private FragmentManager getSupportFragmentManager(){
        return activity.getSupportFragmentManager();
    }

    public ScreensNavigator getScreensNavigator() {
        if(screensNavigator == null){
            screensNavigator = new ScreensNavigator(activity);
        }
        return screensNavigator;
    }

    public DialogsNavigator getDialogNavigator(){
        return new DialogsNavigator(getSupportFragmentManager());
    }

    public FetchQuestionUseCase getFetchQuestionUseCase() {
        return new FetchQuestionUseCase(getStackoverflowApi());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return new FetchQuestionDetailsUseCase(getStackoverflowApi());
    }
}
