package com.example.dagger_java.screens.common.viewsmvc;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dagger_java.screens.questiondetails.QuestionDetailsMvc;
import com.example.dagger_java.screens.questionslist.QuestionsListFragment;
import com.example.dagger_java.screens.questionslist.QuestionsListViewMvc;

public class ViewMvcFactory {
    private LayoutInflater inflater;

    public ViewMvcFactory(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public QuestionsListViewMvc newQuestionsListViewMvc(ViewGroup viewGroup){
        return new QuestionsListViewMvc(inflater, viewGroup);
    }

    public QuestionDetailsMvc newQuestionDetailsMvc(ViewGroup viewGroup){
        return new QuestionDetailsMvc(inflater, viewGroup);
    }
}
