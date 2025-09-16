package com.example.dagger_java.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.R;
import com.example.dagger_java.questions.Question;
import com.example.dagger_java.screens.common.viewsmvc.BaseViewMvc;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvc extends BaseViewMvc<QuestionsListViewMvc.Listener> {

    interface Listener{
        void onRefreshClicked();
        void onQuestionClicked(Question clickedQuestion);
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private QuestionsAdapter questionsAdapter;

    public QuestionsListViewMvc(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater, viewGroup, R.layout.layout_questions_list);

        init();
    }

    public void bindQuestions(List<Question> questions) {
        questionsAdapter.bindData(questions);
    }

    public void showProgressIndication(){
        swipeRefreshLayout.setRefreshing(true);
    }

    public void hideProgressIndication(){
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void init(){
        // init pull-down-to-refresh
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for(Listener listener: listeners){
                    listener.onRefreshClicked();
                }
            }
        });

        // init recycler view
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        questionsAdapter = new QuestionsAdapter(new QuestionsAdapter.OnQuestionClickListener() {
            @Override
            public void onQuestionClick(Question question) {
                for(Listener listener: listeners){
                    listener.onQuestionClicked(question);
                }
            }
        });
        recyclerView.setAdapter(questionsAdapter);
    }

    static class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

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
}
