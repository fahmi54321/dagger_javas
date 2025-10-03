package com.example.dagger_java.common.dependencyinjection.activity;

import android.app.Application;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.common.dependencyinjection.app.AppComponent;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.screens.common.ScreensNavigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;
    private final AppComponent appComponent;
    public ActivityModule(AppCompatActivity activity, AppComponent appComponent) {
        this.activity = activity;
        this.appComponent = appComponent;
    }

    @Provides
    public AppCompatActivity getActivity(){
        return activity;
    }

    @Provides
    @ActivityScope
    public ScreensNavigator getScreensNavigator(){
        return new ScreensNavigator(activity);
    }

    @Provides
    public StackoverflowApi getStackoverflowApi(){
        return appComponent.getStackoverflowApi();
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
