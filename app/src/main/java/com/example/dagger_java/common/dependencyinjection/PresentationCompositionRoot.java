package com.example.dagger_java.common.dependencyinjection;

import android.view.LayoutInflater;

import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;

public class PresentationCompositionRoot {
    private final ActivityCompositionRoot activityCompositionRoot;

    public PresentationCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        this.activityCompositionRoot = activityCompositionRoot;
    }

    private LayoutInflater layoutInflater(){
        return activityCompositionRoot.getLayoutInflater();
    }

    private FragmentManager supportFragmentManager(){
        return activityCompositionRoot.getSupportFragmentManager();
    }

    private StackoverflowApi stackoverflowApi(){
        return activityCompositionRoot.getStackoverflowApi();
    }

    public ScreensNavigator getScreensNavigator(){
        return activityCompositionRoot.getScreensNavigator();
    }

    public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(layoutInflater());
    }

    public DialogsNavigator getDialogNavigator(){
        return new DialogsNavigator(supportFragmentManager());
    }

    public FetchQuestionUseCase getFetchQuestionUseCase() {
        return new FetchQuestionUseCase(stackoverflowApi());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return new FetchQuestionDetailsUseCase(stackoverflowApi());
    }


}
