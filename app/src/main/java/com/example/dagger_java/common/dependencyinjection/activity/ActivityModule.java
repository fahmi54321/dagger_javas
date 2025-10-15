package com.example.dagger_java.common.dependencyinjection.activity;

import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.screens.common.ScreensNavigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    @ActivityScope
    public static ScreensNavigator getScreensNavigator(AppCompatActivity activity){
        return new ScreensNavigator(activity);
    }

    @Provides
    public static FragmentManager getSupportFragmentManager(AppCompatActivity activity){
        return activity.getSupportFragmentManager();
    }

    @Provides
    public static LayoutInflater getLayoutInflater(AppCompatActivity activity){
        return LayoutInflater.from(activity);
    }

}
