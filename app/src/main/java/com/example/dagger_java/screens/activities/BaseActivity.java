package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.MyApplication;
import com.example.dagger_java.common.dependencyinjection.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.ActivityModule;
import com.example.dagger_java.common.dependencyinjection.AppComponent;
import com.example.dagger_java.common.dependencyinjection.AppModule;
import com.example.dagger_java.common.dependencyinjection.DaggerActivityComponent;
import com.example.dagger_java.common.dependencyinjection.DaggerAppComponent;
import com.example.dagger_java.common.dependencyinjection.DaggerPresentationComponent;
import com.example.dagger_java.common.dependencyinjection.Injector;
import com.example.dagger_java.common.dependencyinjection.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.PresentationModule;

public class BaseActivity extends AppCompatActivity {
    private AppModule getAppModule() {
        return ((MyApplication) getApplication()).getAppCompisitionRoot();
    }

    private ActivityModule activityModule;

    private AppComponent appComponent;

    private AppComponent getAppComponent(){
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
            activityModule = new ActivityModule(this, getAppComponent());
        }
        return activityModule;
    }

    private ActivityComponent activityComponent;

    private ActivityComponent getActivityComponent(){
        if(activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(getActivityModule())
                    .build();
        }
        return activityComponent;
    }

    private PresentationComponent presentationComponent;

    private PresentationComponent getPresentationComponent(){
        if(presentationComponent == null){
            presentationComponent = DaggerPresentationComponent.builder()
                    .presentationModule(new PresentationModule(getActivityComponent()))
                    .build();
        }
        return presentationComponent;
    }

    public Injector injector(){
        return new Injector(getPresentationComponent());
    }

}
