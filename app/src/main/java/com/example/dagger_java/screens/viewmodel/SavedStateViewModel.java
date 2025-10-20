package com.example.dagger_java.screens.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

abstract class SavedStateViewModel extends ViewModel {
    abstract void init(SavedStateHandle savedStateHandle);
}
