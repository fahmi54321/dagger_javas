package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.MyApplication;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityModule;
import com.example.dagger_java.common.dependencyinjection.app.AppComponent;
import com.example.dagger_java.common.dependencyinjection.app.AppModule;
import com.example.dagger_java.common.dependencyinjection.app.DaggerAppComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationModule;
import com.example.dagger_java.common.dependencyinjection.presentation.UseCaseModule;

public class BaseActivity extends AppCompatActivity {
    private AppModule getAppModule() {
        return ((MyApplication) getApplication()).getAppCompisitionRoot();
    }

    private ActivityModule activityModule;
    private PresentationModule presentationModule;
    private UseCaseModule useCaseModule;

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

    public PresentationModule getPresentationModule(){
        if(presentationModule == null){
            presentationModule = new PresentationModule();
        }
        return presentationModule;
    }

    public UseCaseModule getUseCaseModule(){
        if(useCaseModule == null){
            useCaseModule = new UseCaseModule();
        }
        return useCaseModule;
    }


    private ActivityComponent getActivityComponent(){
        return getAppComponent().newActivityModule(getActivityModule());
    }

    private PresentationComponent getPresentationComponent(){
        return getActivityComponent().newPresentationComponent(
                getPresentationModule(),
                getUseCaseModule()
        );
    }

    public PresentationComponent injector(){
        return getPresentationComponent();
    }

}
