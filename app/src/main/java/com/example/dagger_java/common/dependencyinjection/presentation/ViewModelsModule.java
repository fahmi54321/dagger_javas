package com.example.dagger_java.common.dependencyinjection.presentation;

import androidx.lifecycle.ViewModel;

import com.example.dagger_java.common.dependencyinjection.ViewModelKey;
import com.example.dagger_java.screens.questionslistviewmodel.QuestionsListViewModel;
import com.example.dagger_java.screens.viewmodel.MyViewModel;
import com.example.dagger_java.screens.viewmodel.MyViewModel2;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel.class)
    abstract ViewModel myViewModel(MyViewModel myViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel2.class)
    abstract ViewModel myViewModel2(MyViewModel2 myViewModel2);

    @Binds
    @IntoMap
    @ViewModelKey(QuestionsListViewModel.class)
    abstract ViewModel questionsListViewModel(QuestionsListViewModel questionsListViewModel);
}
