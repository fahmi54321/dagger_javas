package com.example.dagger_java.screens.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dagger_java.R;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.activities.BaseActivity;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

import javax.inject.Inject;

public class ViewModelActivity extends BaseActivity {

    @Inject
    public MyViewModel.MyViewModelFactory myViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injector().inject(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_model);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyViewModel myViewModel = new ViewModelProvider(this, myViewModelFactory).get(MyViewModel.class);
        myViewModel.question.observe(this, questions -> Toast.makeText(ViewModelActivity.this, "fetched :"+questions.size(), Toast.LENGTH_SHORT).show());
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ViewModelActivity.class);
        context.startActivity(intent);
    }
}