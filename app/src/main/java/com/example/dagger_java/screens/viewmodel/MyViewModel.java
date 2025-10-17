package com.example.dagger_java.screens.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.questions.Question;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyViewModel extends ViewModel {

    private final FetchQuestionUseCase fetchQuestionUseCase;

    private MutableLiveData<List<Question>> _question = new MutableLiveData<>();
    public LiveData<List<Question>> question = _question;

    @Inject
    public MyViewModel(FetchQuestionUseCase fetchQuestionUseCase) {
        this.fetchQuestionUseCase = fetchQuestionUseCase;
        init();
    }

    private void init(){
        fetchQuestionUseCase.fetchQuestions(result -> {
            if(result instanceof FetchQuestionUseCase.Result.Success){
                _question.setValue(((FetchQuestionUseCase.Result.Success) result).getQuestions());
            }else{
                _question.setValue(new ArrayList<>());
            }
        });
    }
}
