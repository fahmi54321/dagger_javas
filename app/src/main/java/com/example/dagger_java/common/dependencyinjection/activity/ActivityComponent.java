package com.example.dagger_java.common.dependencyinjection.activity;

import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    PresentationComponent newPresentationComponent();
}
