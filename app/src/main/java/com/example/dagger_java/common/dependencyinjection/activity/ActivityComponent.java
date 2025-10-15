package com.example.dagger_java.common.dependencyinjection.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    PresentationComponent newPresentationComponent();

    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        Builder activity(AppCompatActivity activity);
        Builder activityModule(ActivityModule activityModule);
        ActivityComponent build();
    }
}
