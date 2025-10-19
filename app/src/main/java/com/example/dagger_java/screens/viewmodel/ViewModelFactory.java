package com.example.dagger_java.screens.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.dagger_java.questions.FetchQuestionUseCase;

import javax.inject.Inject;
import javax.inject.Provider;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Provider<FetchQuestionUseCase> fetchQuestionUseCaseProvider;

    @Inject
    public ViewModelFactory(Provider<FetchQuestionUseCase> fetchQuestionUseCaseProvider) {
        this.fetchQuestionUseCaseProvider = fetchQuestionUseCaseProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass, @NonNull CreationExtras extras) {
        SavedStateHandle savedStateHandle = SavedStateHandleSupport.createSavedStateHandle(extras);
        if (modelClass.isAssignableFrom(MyViewModel.class)) {
            return (T) new MyViewModel(
                    fetchQuestionUseCaseProvider.get(),
                    savedStateHandle
            );
        } else if (modelClass.isAssignableFrom(MyViewModel2.class)) {
            return (T) new MyViewModel2(
                    fetchQuestionUseCaseProvider.get()
            );
        } else {
            throw new RuntimeException("Unsupported ViewModel type: " + modelClass);
        }
    }
}
