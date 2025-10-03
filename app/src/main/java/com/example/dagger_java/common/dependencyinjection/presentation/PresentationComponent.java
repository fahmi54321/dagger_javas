package com.example.dagger_java.common.dependencyinjection.presentation;

import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;

import dagger.Component;

@Component(modules = {PresentationModule.class})
public interface PresentationComponent {
    ScreensNavigator screensNavigator();

    ViewMvcFactory viewMvcFactory();

    DialogsNavigator dialogsNavigator();

    FetchQuestionUseCase fetchQuestionUseCase();

    FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase();
}
