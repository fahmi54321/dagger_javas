package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.dependencyinjection.activity.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.activity.DaggerActivityComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.DaggerPresentationComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.presentation.PresentationModule;

public class BaseFragment extends Fragment {

    private PresentationComponent presentationComponent;

    private ActivityComponent activityComponent;

    private ActivityComponent getActivityComponent(){
        if(activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .appComponent(((BaseActivity) requireActivity()).getAppComponent())
                    .activityModule(((BaseActivity) requireActivity()).getActivityModule())
                    .build();
        }
        return activityComponent;
    }

    private PresentationComponent getPresentationComponent(){
        if(presentationComponent == null){
            presentationComponent = DaggerPresentationComponent
                    .builder()
                    .activityComponent(getActivityComponent())
                    .presentationModule(new PresentationModule())
                    .build();
        }
        return presentationComponent;
    }

    public PresentationComponent injector(){
        return getPresentationComponent();
    }
}
