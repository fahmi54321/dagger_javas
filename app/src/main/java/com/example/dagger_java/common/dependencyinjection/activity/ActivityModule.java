package com.example.dagger_java.common.dependencyinjection.activity;

import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.screens.common.ScreensNavigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;
    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
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
    public FragmentManager getSupportFragmentManager(){
        return getActivity().getSupportFragmentManager();
    }

    @Provides
    public LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(getActivity());
    }

}
