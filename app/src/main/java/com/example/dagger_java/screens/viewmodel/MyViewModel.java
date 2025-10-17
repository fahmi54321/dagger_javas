package com.example.dagger_java.screens.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.questions.Question;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

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
        fetchQuestionUseCase.fetchQuestions(new FetchQuestionUseCase.FetchCallback() {
            @Override
            public void onResult(FetchQuestionUseCase.Result result) {
                if(result instanceof FetchQuestionUseCase.Result.Success){
                    _question.setValue(((FetchQuestionUseCase.Result.Success) result).getQuestions());
                }else{
                    _question.setValue(new ArrayList<>());
                }
            }
        });
    }

    static class MyViewModelFactory implements ViewModelProvider.Factory {
        private final Provider<FetchQuestionUseCase> fetchQuestionUseCaseProvider;

        @Inject
        public MyViewModelFactory(Provider<FetchQuestionUseCase> fetchQuestionUseCaseProvider) {
            this.fetchQuestionUseCaseProvider = fetchQuestionUseCaseProvider;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return ((T) new MyViewModel(fetchQuestionUseCaseProvider.get()));
        }
    }
}
