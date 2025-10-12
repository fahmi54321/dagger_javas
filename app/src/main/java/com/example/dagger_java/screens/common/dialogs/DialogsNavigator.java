package com.example.dagger_java.screens.common.dialogs;


import androidx.fragment.app.FragmentManager;

import javax.inject.Inject;

public class DialogsNavigator {
    private FragmentManager fragmentManager;

    @Inject
    public DialogsNavigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showServerErrorDialog(){
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(),null)
                .commitAllowingStateLoss();
    }
}
