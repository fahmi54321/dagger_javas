package com.example.dagger_java.screens.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public abstract class SavedStateViewModel extends ViewModel {
    protected abstract void init(SavedStateHandle savedStateHandle);
}
