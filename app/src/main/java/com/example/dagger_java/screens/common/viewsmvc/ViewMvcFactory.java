package com.example.dagger_java.screens.common.viewsmvc;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dagger_java.screens.imageloader.ImageLoader;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsMvc;
import com.example.dagger_java.screens.questionslist.QuestionsListViewMvc;
import com.example.dagger_java.screens.questionslistviewmodel.QuestionsListView;

import javax.inject.Inject;

public class ViewMvcFactory {
    private final LayoutInflater inflater;
    private final ImageLoader imageLoader;

    @Inject
    public ViewMvcFactory(LayoutInflater inflater, ImageLoader imageLoader) {
        this.inflater = inflater;
        this.imageLoader = imageLoader;
    }

    public QuestionsListViewMvc newQuestionsListViewMvc(ViewGroup viewGroup){
        return new QuestionsListViewMvc(inflater, viewGroup);
    }

    public QuestionDetailsMvc newQuestionDetailsMvc(ViewGroup viewGroup){
        return new QuestionDetailsMvc(inflater, viewGroup, imageLoader);
    }

    public QuestionsListView newQuestionsListView(ViewGroup viewGroup) {
        return new QuestionsListView(inflater, viewGroup);
    }
}
