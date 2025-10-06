package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.MyApplication;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityModule;
import com.example.dagger_java.common.dependencyinjection.activity.DaggerActivityComponent;
import com.example.dagger_java.common.dependencyinjection.app.AppComponent;
import com.example.dagger_java.common.dependencyinjection.app.AppModule;
import com.example.dagger_java.common.dependencyinjection.app.DaggerAppComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.DaggerPresentationComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationModule;

public class BaseActivity extends AppCompatActivity {
    private AppModule getAppModule() {
        return ((MyApplication) getApplication()).getAppCompisitionRoot();
    }

    private ActivityModule activityModule;

    private AppComponent appComponent;

    public AppComponent getAppComponent(){
        if(appComponent == null){
            appComponent = DaggerAppComponent
                    .builder()
                    .appModule(getAppModule())
                    .build();
        }
        return appComponent;
    }

    public ActivityModule getActivityModule(){
        if(activityModule == null){
            activityModule = new ActivityModule(this);
        }
        return activityModule;
    }

    private ActivityComponent activityComponent;

    private ActivityComponent getActivityComponent(){
        if(activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .appComponent(getAppComponent())
                    .activityModule(getActivityModule())
                    .build();
        }
        return activityComponent;
    }

    private PresentationComponent presentationComponent;

    private PresentationComponent getPresentationComponent(){
        if(presentationComponent == null){
            presentationComponent = DaggerPresentationComponent.builder()
                    .activityComponent(getActivityComponent())
                    .presentationModule(new PresentationModule())
                    .build();
        }
        return presentationComponent;
    }

    public PresentationComponent injector(){
        return getPresentationComponent();
    }

}
