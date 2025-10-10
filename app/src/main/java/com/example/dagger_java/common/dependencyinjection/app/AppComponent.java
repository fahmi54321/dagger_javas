package com.example.dagger_java.common.dependencyinjection.app;

import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.activity.ActivityModule;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    ActivityComponent newActivityModule(ActivityModule activityModule);

}
