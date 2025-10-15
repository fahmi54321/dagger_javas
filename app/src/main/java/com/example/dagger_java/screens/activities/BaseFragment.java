package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;

public class BaseFragment extends Fragment {

    private ActivityComponent getActivityComponent(){
        return ((BaseActivity) requireActivity()).getActivityComponent();
    }

    private PresentationComponent getPresentationComponent(){
        return getActivityComponent().newPresentationComponent();
    }

    public PresentationComponent injector(){
        return getPresentationComponent();
    }
}
