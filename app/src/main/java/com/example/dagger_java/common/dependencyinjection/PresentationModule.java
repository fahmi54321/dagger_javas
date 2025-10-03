package com.example.dagger_java.common.dependencyinjection;

import android.view.LayoutInflater;

import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {
    private final ActivityComponent activityComponent;

    public PresentationModule(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    @Provides
    public LayoutInflater layoutInflater(){
        return activityComponent.getLayoutInflater();
    }

    @Provides
    public FragmentManager supportFragmentManager(){
        return activityComponent.getSupportFragmentManager();
    }

    @Provides
    public StackoverflowApi stackoverflowApi(){
        return activityComponent.getStackoverflowApi();
    }

    @Provides
    public ScreensNavigator getScreensNavigator(){
        return activityComponent.getScreensNavigator();
    }

    @Provides
    public ViewMvcFactory getViewMvcFactory(LayoutInflater layoutInflater){
        return new ViewMvcFactory(layoutInflater);
    }

    @Provides
    public DialogsNavigator getDialogNavigator(FragmentManager fragmentManager){
        return new DialogsNavigator(fragmentManager);
    }

    @Provides
    public FetchQuestionUseCase getFetchQuestionUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionUseCase(stackoverflowApi);
    }

    @Provides
    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi){
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

}
