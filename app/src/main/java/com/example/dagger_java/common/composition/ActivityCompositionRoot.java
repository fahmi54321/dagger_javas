package com.example.dagger_java.common.composition;

import android.app.Activity;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.common.ScreensNavigator;

public class ActivityCompositionRoot {

    private final Activity activity;
    private final AppCompisitionRoot appCompisitionRoot;
    private ScreensNavigator screensNavigator;
    private StackoverflowApi stackoverflowApi;

    public ActivityCompositionRoot(Activity activity, AppCompisitionRoot appCompisitionRoot) {
        this.activity = activity;
        this.appCompisitionRoot = appCompisitionRoot;
    }

    private StackoverflowApi getStackoverflowApi() {
        if(stackoverflowApi == null){
            stackoverflowApi = appCompisitionRoot.getStackoverflowApi();
        }
        return stackoverflowApi;
    }

    public ScreensNavigator getScreensNavigator() {
        if(screensNavigator == null){
            screensNavigator = new ScreensNavigator(activity);
        }
        return screensNavigator;
    }

    public FetchQuestionUseCase getFetchQuestionUseCase() {
        return new FetchQuestionUseCase(getStackoverflowApi());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return new FetchQuestionDetailsUseCase(getStackoverflowApi());
    }
}
