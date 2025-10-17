package com.example.dagger_java.common.dependencyinjection.presentation;

import com.example.dagger_java.screens.questiondetails.QuestionDetailsFragment;
import com.example.dagger_java.screens.questionslist.QuestionsListFragment;
import com.example.dagger_java.screens.viewmodel.ViewModelActivity;

import dagger.Subcomponent;

@PresentationScope
@Subcomponent
public interface PresentationComponent {
    void inject(QuestionsListFragment questionsListFragment);

    void inject(QuestionDetailsFragment questionDetailsFragment);

    void inject(ViewModelActivity viewModelActivity);
}
