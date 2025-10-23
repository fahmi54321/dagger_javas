package com.example.dagger_java.common.dependencyinjection.activity;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.ScreensNavigatorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;

@Module
@InstallIn(ActivityComponent.class)
abstract class ActivityModule {
    @ActivityScoped
    @Binds
    abstract ScreensNavigator getScreensNavigator(ScreensNavigatorImpl screensNavigator);

    @Provides
    public static AppCompatActivity appCompatActivity(Activity activity){
        return (AppCompatActivity) activity;
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
