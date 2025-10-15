package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.MyApplication;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.app.AppComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;

public class BaseActivity extends AppCompatActivity {
    public AppComponent getAppComponent() {
        return ((MyApplication) getApplication()).getAppComponent();
    }


    public ActivityComponent getActivityComponent(){
        return getAppComponent().newActivityCompomentBuilder()
                .activity(this)
                .build();
    }

    private PresentationComponent getPresentationComponent(){
        return getActivityComponent().newPresentationComponent( );
    }

    public PresentationComponent injector(){
        return getPresentationComponent();
    }

}
