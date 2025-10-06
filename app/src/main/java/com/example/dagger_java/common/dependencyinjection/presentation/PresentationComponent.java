package com.example.dagger_java.common.dependencyinjection.presentation;

import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsFragment;
import com.example.dagger_java.screens.questionslist.QuestionsListFragment;

import dagger.Component;

@PresentationScope
@Component(dependencies = {ActivityComponent.class},modules = {PresentationModule.class})
public interface PresentationComponent {
    void inject(QuestionsListFragment questionsListFragment);

    void inject(QuestionDetailsFragment questionDetailsFragment);
}
