package com.example.dagger_java.screens.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.questions.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MyViewModel extends ViewModel {

    private final FetchQuestionUseCase fetchQuestionUseCase;
    private final SavedStateHandle savedStateHandle;

    private MutableLiveData<List<Question>> _question;
    public LiveData<List<Question>> question;

    @Inject
    public MyViewModel(FetchQuestionUseCase fetchQuestionUseCase, SavedStateHandle savedStateHandle) {
        this.fetchQuestionUseCase = fetchQuestionUseCase;
        this.savedStateHandle = savedStateHandle;
        init();
    }

    private void init(){
        _question = savedStateHandle.getLiveData("questions", Collections.emptyList());
        question = _question;

        fetchQuestionUseCase.fetchQuestions(result -> {
            if(result instanceof FetchQuestionUseCase.Result.Success){
                _question.setValue(((FetchQuestionUseCase.Result.Success) result).getQuestions());
            }else{
                _question.setValue(new ArrayList<>());
            }
        });
    }
}
