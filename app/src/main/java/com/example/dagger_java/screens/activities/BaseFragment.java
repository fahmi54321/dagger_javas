package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.composition.ActivityCompositionRoot;
import com.example.dagger_java.common.composition.PresentationCompositionRoot;

public class BaseFragment extends Fragment {

    private PresentationCompositionRoot presentationCompositionRoot;
    protected PresentationCompositionRoot getCompositionRoot() {
        if(presentationCompositionRoot == null){
            presentationCompositionRoot = new PresentationCompositionRoot(((BaseActivity) requireActivity()).getActivityCompositionRoot());
        }
        return presentationCompositionRoot;
    }
}
