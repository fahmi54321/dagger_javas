package com.example.dagger_java.common.dependencyinjection;

import android.app.Activity;
import android.app.Application;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.screens.common.ScreensNavigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;
    private final AppComponent appComponent;
    private ScreensNavigator _screensNavigator;
    private StackoverflowApi _stackoverflowApi;

    public ActivityModule(AppCompatActivity activity, AppComponent appComponent) {
        this.activity = activity;
        this.appComponent = appComponent;
    }

    private ScreensNavigator screensNavigator() {
        if(_screensNavigator == null){
            _screensNavigator = new ScreensNavigator(activity);
        }
        return _screensNavigator;
    }

    private StackoverflowApi stackoverflowApi() {
        if(_stackoverflowApi == null){
            _stackoverflowApi = appComponent.getStackoverflowApi();
        }
        return _stackoverflowApi;
    }

    @Provides
    public AppCompatActivity getActivity(){
        return activity;
    }

    @Provides
    public ScreensNavigator getScreensNavigator(){
        return screensNavigator();
    }

    @Provides
    public StackoverflowApi getStackoverflowApi(){
        return stackoverflowApi();
    }

    @Provides
    public FragmentManager getSupportFragmentManager(){
        return getActivity().getSupportFragmentManager();
    }

    @Provides
    public LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(getActivity());
    }

    @Provides
    public Application getApplication(){
        return appComponent.getApplication();
    }
}
