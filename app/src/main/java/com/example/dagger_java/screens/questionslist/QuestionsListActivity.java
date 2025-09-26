package com.example.dagger_java.screens.questionslist;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.Constants;
import com.example.dagger_java.MyApplication;
import com.example.dagger_java.R;
import com.example.dagger_java.networking.QuestionsListResponseSchema;
import com.example.dagger_java.networking.StackoverflowApi;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.activities.BaseActivity;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.dialogs.ServerErrorDialogFragment;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

