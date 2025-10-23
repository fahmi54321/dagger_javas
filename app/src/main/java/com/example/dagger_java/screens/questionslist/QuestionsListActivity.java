package com.example.dagger_java.screens.questionslist;

import android.os.Bundle;

import com.example.dagger_java.R;
import com.example.dagger_java.screens.activities.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionsListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_content,new QuestionsListFragment())
                    .commit();
        }

    }
}

