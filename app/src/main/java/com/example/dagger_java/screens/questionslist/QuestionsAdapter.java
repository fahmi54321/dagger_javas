package com.example.dagger_java.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger_java.R;
import com.example.dagger_java.questions.Question;

import java.util.ArrayList;
import java.util.List;

class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    public interface OnQuestionClickListener {
        void onQuestionClick(Question question);
    }

    private List<Question> questionsList = new ArrayList<>();
    private final OnQuestionClickListener onQuestionClickListener;

    public QuestionsAdapter(OnQuestionClickListener onQuestionClickListener) {
        this.onQuestionClickListener = onQuestionClickListener;
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
        }
    }

    public void bindData(List<Question> questions) {
        this.questionsList = new ArrayList<>(questions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_question_list_item, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionsList.get(position);
        holder.title.setText(question.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (onQuestionClickListener != null) {
                onQuestionClickListener.onQuestionClick(question);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }
}
