package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.dependencyinjection.ActivityComponent;
import com.example.dagger_java.common.dependencyinjection.DaggerActivityComponent;
import com.example.dagger_java.common.dependencyinjection.DaggerPresentationComponent;
import com.example.dagger_java.common.dependencyinjection.Injector;
import com.example.dagger_java.common.dependencyinjection.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.PresentationModule;

public class BaseFragment extends Fragment {

    private PresentationComponent presentationComponent;

    private ActivityComponent activityComponent;

    private ActivityComponent getActivityComponent(){
        if(activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(((BaseActivity) requireActivity()).getActivityModule())
                    .build();
        }
        return activityComponent;
    }

    private PresentationComponent getPresentationComponent(){
        if(presentationComponent == null){
            presentationComponent = DaggerPresentationComponent
                    .builder()
                    .presentationModule(new PresentationModule(getActivityComponent()))
                    .build();
        }
        return presentationComponent;
    }

    public Injector injector(){
        return new Injector(getPresentationComponent());
    }
}
