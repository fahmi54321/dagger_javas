package com.example.dagger_java.screens.questiondetails;

import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.R;
import com.example.dagger_java.screens.common.toolbar.MyToolbar;
import com.example.dagger_java.screens.common.viewsmvc.BaseViewMvc;
import com.example.dagger_java.screens.imageloader.ImageLoader;

public class QuestionDetailsMvc extends BaseViewMvc<QuestionDetailsMvc.Listener> {

    interface Listener{
        void onBack();
    }

    private MyToolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView txtQuestionBody;

    private ImageLoader imageLoader;

    public QuestionDetailsMvc(LayoutInflater layoutInflater, ViewGroup viewGroup, ImageLoader imageLoader) {
        super(layoutInflater,viewGroup,R.layout.layout_question_details);
        this.imageLoader = imageLoader;

        init();
    }

    private void init(){
        txtQuestionBody = findViewById(R.id.txt_question_body);

        // init toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigateUpListener(new MyToolbar.NavigateUpListener() {
            @Override
            public void onNavigationUpClicked() {
                for(Listener listener : listeners){
                    listener.onBack();
                }
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setEnabled(true);
    }

    public void showProgressIndication(){
        swipeRefreshLayout.setRefreshing(true);
    }

    public void hideProgressIndication(){
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setQuestionBody(Spanned spanned) {
        txtQuestionBody.setText(spanned);
    }
}
