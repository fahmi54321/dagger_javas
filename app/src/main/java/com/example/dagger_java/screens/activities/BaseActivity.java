package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.MyApplication;
import com.example.dagger_java.common.dependencyinjection.ActivityCompositionRoot;
import com.example.dagger_java.common.dependencyinjection.AppCompisitionRoot;
import com.example.dagger_java.common.dependencyinjection.Injector;
import com.example.dagger_java.common.dependencyinjection.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {
    private AppCompisitionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getAppCompisitionRoot();
    }

    private ActivityCompositionRoot activityCompositionRoot;

    public ActivityCompositionRoot getActivityCompositionRoot(){
        if(activityCompositionRoot == null){
            activityCompositionRoot = new ActivityCompositionRoot(this, getAppCompositionRoot());
        }
        return activityCompositionRoot;
    }

    private PresentationCompositionRoot compositionRoot(){
        return new PresentationCompositionRoot(getActivityCompositionRoot());
    }

    public Injector injector(){
        return new Injector(compositionRoot());
    }

}
