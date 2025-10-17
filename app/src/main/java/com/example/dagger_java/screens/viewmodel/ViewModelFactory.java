package com.example.dagger_java.screens.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Provider<MyViewModel> myViewModelProvider;
    private final Provider<MyViewModel2> myViewModel2Provider;

    @Inject
    public ViewModelFactory(Provider<MyViewModel> myViewModelProvider, Provider<MyViewModel2> myViewModel2Provider) {
        this.myViewModelProvider = myViewModelProvider;
        this.myViewModel2Provider = myViewModel2Provider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MyViewModel.class)){
            return ((T) myViewModelProvider.get());
        }else if(modelClass.isAssignableFrom(MyViewModel2.class)){
            return ((T) myViewModel2Provider.get());
        }else{
            throw new IllegalArgumentException("Unsupported ViewModel type: $modelClass");
        }
    }
}
