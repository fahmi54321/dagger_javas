package com.example.dagger_java.screens.activities;

import androidx.fragment.app.Fragment;

import com.example.dagger_java.common.composition.ActivityCompositionRoot;

public class BaseFragment extends Fragment {
    protected ActivityCompositionRoot getCompositionRoot() {
        return ((BaseActivity) requireActivity()).getActivityCompositionRoot();
    }
}
