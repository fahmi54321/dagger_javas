package com.example.dagger_java.questions;

import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("title")
    private String title;
    @SerializedName("question_id")
    private String id;

    public Question(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
