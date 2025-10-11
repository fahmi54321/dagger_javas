package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationModule;

public class BaseFragment extends Fragment {

    private ActivityComponent getActivityComponent(){
        return ((BaseActivity) requireActivity()).getAppComponent().newActivityModule(((BaseActivity) requireActivity()).getActivityModule());
    }

    private PresentationComponent getPresentationComponent(){
        return getActivityComponent().newPresentationComponent(
                ((BaseActivity) requireActivity()).getPresentationModule(),
                ((BaseActivity) requireActivity()).getUseCaseModule()
        );
    }

    public PresentationComponent injector(){
        return getPresentationComponent();
    }
}
