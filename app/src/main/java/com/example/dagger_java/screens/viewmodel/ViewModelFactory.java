package com.example.dagger_java.screens.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.dagger_java.questions.FetchQuestionUseCase;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> providers;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providers) {
        this.providers = providers;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass, @NonNull CreationExtras extras) {
        SavedStateHandle savedStateHandle = SavedStateHandleSupport.createSavedStateHandle(extras);
        Provider<? extends ViewModel> provider = providers.get(modelClass);
        if (provider == null) {
            throw new RuntimeException("Unsupported ViewModel type: " + modelClass);
        }

        ViewModel viewModel = provider.get();

        if (viewModel instanceof SavedStateViewModel) {
            ((SavedStateViewModel) viewModel).init(savedStateHandle);
        }

        return (T) viewModel;
    }
}
