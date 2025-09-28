package com.example.dagger_java.common.composition;

import android.app.Application;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.screens.common.ScreensNavigator;

public class ActivityCompositionRoot {

    private final AppCompatActivity activity;
    private final AppCompisitionRoot appCompisitionRoot;
    private ScreensNavigator screensNavigator;
    private StackoverflowApi stackoverflowApi;

    public ActivityCompositionRoot(AppCompatActivity activity, AppCompisitionRoot appCompisitionRoot) {
        this.activity = activity;
        this.appCompisitionRoot = appCompisitionRoot;
    }

    public ScreensNavigator getScreensNavigator() {
        if(screensNavigator == null){
            screensNavigator = new ScreensNavigator(activity);
        }
        return screensNavigator;
    }

    public StackoverflowApi getStackoverflowApi() {
        if(stackoverflowApi == null){
            stackoverflowApi = appCompisitionRoot.getStackoverflowApi();
        }
        return stackoverflowApi;
    }

    public FragmentManager getSupportFragmentManager(){
        return activity.getSupportFragmentManager();
    }

    public LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(activity);
    }

    public Application getApplication(){
        return appCompisitionRoot.getApplication();
    }
}
