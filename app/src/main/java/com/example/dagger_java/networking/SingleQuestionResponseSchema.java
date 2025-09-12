package com.example.dagger_java.networking;

import com.example.dagger_java.questions.QuestionWithBody;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleQuestionResponseSchema {

    @SerializedName("items")
    private List<QuestionWithBody> questions;

    public List<QuestionWithBody> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionWithBody> questions) {
        this.questions = questions;
    }

    public QuestionWithBody getQuestion() {
        if (questions != null && !questions.isEmpty()) {
            return questions.get(0);
        }
        return null; // biar aman kalau list kosong
    }
}

