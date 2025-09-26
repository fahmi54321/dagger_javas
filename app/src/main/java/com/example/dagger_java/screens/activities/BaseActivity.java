package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.MyApplication;
import com.example.dagger_java.common.composition.ActivityCompositionRoot;
import com.example.dagger_java.common.composition.AppCompisitionRoot;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;

public class BaseActivity extends AppCompatActivity {
    private AppCompisitionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getAppCompisitionRoot();
    }

    public ActivityCompositionRoot getActivityCompositionRoot(){
        return new ActivityCompositionRoot(this, getAppCompositionRoot());
    }

}
