package com.example.dagger_java.common.dependencyinjection;

import android.app.Application;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.screens.common.ScreensNavigator;

import dagger.Component;

@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    AppCompatActivity getActivity();

    ScreensNavigator getScreensNavigator();

    StackoverflowApi getStackoverflowApi();

    FragmentManager getSupportFragmentManager();

    LayoutInflater getLayoutInflater();

    Application getApplication();
}
