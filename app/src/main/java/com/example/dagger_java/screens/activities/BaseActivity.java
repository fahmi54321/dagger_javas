package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.common.composition.ActivityCompositionRoot;
import com.example.dagger_java.common.composition.AppCompisitionRoot;

public class BaseActivity extends AppCompatActivity {
    private AppCompisitionRoot getAppCompisitionRoot() {
        return new AppCompisitionRoot();
    }

    public ActivityCompositionRoot getActivityCompositionRoot(){
        return new ActivityCompositionRoot(this, getAppCompisitionRoot());
    }

}
