package com.example.dagger_java.common.dependencyinjection;

import com.example.dagger_java.screens.questiondetails.QuestionDetailsFragment;
import com.example.dagger_java.screens.questionslist.QuestionsListFragment;

public class Injector {
    private final PresentationCompositionRoot compositionRoot;

    public Injector(PresentationCompositionRoot compositionRoot) {
        this.compositionRoot = compositionRoot;
    }

    public void inject(QuestionsListFragment fragment) {
        fragment.dialogsNavigator = compositionRoot.getDialogNavigator();
        fragment.screensNavigator = compositionRoot.getScreensNavigator();
        fragment.fetchQuestionUseCase = compositionRoot.getFetchQuestionUseCase();
        fragment.viewMvcFactory = compositionRoot.getViewMvcFactory();
    }

    public void inject(QuestionDetailsFragment fragment) {
        fragment.dialogsNavigator = compositionRoot.getDialogNavigator();
        fragment.screensNavigator = compositionRoot.getScreensNavigator();
        fragment.fetchQuestionDetailsUseCase = compositionRoot.getFetchQuestionDetailsUseCase();
        fragment.viewMvcFactory = compositionRoot.getViewMvcFactory();
    }
}
