package com.example.dagger_java.screens.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.dagger_java.questions.FetchQuestionUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MyViewModel2 extends ViewModel {

    private final FetchQuestionUseCase fetchQuestionUseCase;

    @Inject
    public MyViewModel2(FetchQuestionUseCase fetchQuestionUseCase) {
        this.fetchQuestionUseCase = fetchQuestionUseCase;
        init();
    }

    private void init(){
    }
}
