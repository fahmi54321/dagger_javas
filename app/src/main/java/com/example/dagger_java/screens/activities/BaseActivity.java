package com.example.dagger_java.screens.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger_java.common.composition.AppCompisitionRoot;

public class BaseActivity extends AppCompatActivity {
    public AppCompisitionRoot getAppCompisitionRoot() {
        return new AppCompisitionRoot();
    }
}
