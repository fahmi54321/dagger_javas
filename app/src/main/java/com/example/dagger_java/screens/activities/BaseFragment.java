package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.dependencyinjection.Injector;
import com.example.dagger_java.common.dependencyinjection.PresentationCompositionRoot;

public class BaseFragment extends Fragment {

    private PresentationCompositionRoot presentationCompositionRoot;
    private PresentationCompositionRoot getCompositionRoot() {
        if(presentationCompositionRoot == null){
            presentationCompositionRoot = new PresentationCompositionRoot(((BaseActivity) requireActivity()).getActivityCompositionRoot());
        }
        return presentationCompositionRoot;
    }

    public Injector injector(){
        return new Injector(getCompositionRoot());
    }
}
