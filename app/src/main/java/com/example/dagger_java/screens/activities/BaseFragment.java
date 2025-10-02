package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.dependencyinjection.DaggerPresentationComponent;
import com.example.dagger_java.common.dependencyinjection.Injector;
import com.example.dagger_java.common.dependencyinjection.PresentationComponent;
import com.example.dagger_java.common.dependencyinjection.PresentationModule;

public class BaseFragment extends Fragment {

    private PresentationComponent presentationComponent;

    private PresentationComponent getPresentationComponent(){
        if(presentationComponent == null){
            presentationComponent = DaggerPresentationComponent
                    .builder()
                    .presentationModule(new PresentationModule(((BaseActivity) requireActivity()).getActivityCompositionRoot()))
                    .build();
        }
        return presentationComponent;
    }

    public Injector injector(){
        return new Injector(getPresentationComponent());
    }
}
