package com.example.dagger_java.questions;

import com.google.gson.annotations.SerializedName;

public class QuestionWithBody {
    @SerializedName("title")
    private String title;
    @SerializedName("question_id")
    private String id;
    @SerializedName("body")
    private String body;

    public QuestionWithBody(String title, String id, String body) {
        this.title = title;
        this.id = id;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
