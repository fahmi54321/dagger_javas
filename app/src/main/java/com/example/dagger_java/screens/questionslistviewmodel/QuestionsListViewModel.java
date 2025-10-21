package com.example.dagger_java.screens.questionslistviewmodel;


import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.viewmodel.SavedStateViewModel;

import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;

import javax.inject.Inject;

public class QuestionsListViewModel extends SavedStateViewModel {

    public FetchQuestionUseCase fetchQuestionUseCase;

    private MutableLiveData<List<Question>> _questions;
    private final MutableLiveData<Boolean> _isDataLoaded = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> _isFetchFailed = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> _hideProgressIndication = new MutableLiveData<>(true);

    public LiveData<List<Question>> questions(){
        return _questions;
    }

    public LiveData<Boolean> isDataLoaded(){
        return _isDataLoaded;
    }

    public LiveData<Boolean> isFetchFailed(){
        return _isFetchFailed;
    }

    public LiveData<Boolean> hideProgressIndication(){
        return _hideProgressIndication;
    }

    @Inject
    public QuestionsListViewModel(FetchQuestionUseCase fetchQuestionUseCase) {
        this.fetchQuestionUseCase = fetchQuestionUseCase;
    }

    @Override
    protected void init(SavedStateHandle savedStateHandle) {
        _questions = savedStateHandle.getLiveData("questions", Collections.emptyList());
    }

    public void fetchQuestion(){
        try {
            fetchQuestionUseCase.fetchQuestions(new FetchQuestionUseCase.FetchCallback() {
                @Override
                public void onResult(FetchQuestionUseCase.Result result) {
                    if(result instanceof FetchQuestionUseCase.Result.Success){
                        _questions.setValue(((FetchQuestionUseCase.Result.Success) result).getQuestions());
                        _isDataLoaded.setValue(true);

                    }else if(result instanceof FetchQuestionUseCase.Result.Failure){
                        _isFetchFailed.setValue(true);
                    }
                }
            });
        } finally {
            _hideProgressIndication.setValue(true);
        }
    }
}
