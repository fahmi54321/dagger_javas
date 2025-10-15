package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.MyApplication;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityModule;
import com.example.dagger_java.common.dependencyinjection.app.AppComponent;
import com.example.dagger_java.common.dependencyinjection.app.AppModule;
import com.example.dagger_java.common.dependencyinjection.app.DaggerAppComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;

public class BaseActivity extends AppCompatActivity {
    public AppComponent getAppComponent() {
        return ((MyApplication) getApplication()).getAppComponent();
    }

    private ActivityModule activityModule;

    private ActivityModule activityModule(){
        if(activityModule == null){
            activityModule = new ActivityModule();
        }
        return activityModule;
    }


    public ActivityComponent getActivityComponent(){
        return getAppComponent().newActivityCompomentBuilder()
                .activity(this)
                .activityModule(activityModule())
                .build();
    }

    private PresentationComponent getPresentationComponent(){
        return getActivityComponent().newPresentationComponent( );
    }

    public PresentationComponent injector(){
        return getPresentationComponent();
    }

}
