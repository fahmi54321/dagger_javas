package com.example.dagger_java.common.dependencyinjection.activity;

import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    PresentationComponent newPresentationComponent(PresentationModule presentationModule);
}
