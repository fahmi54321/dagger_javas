package com.example.dagger_java.screens.questionslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dagger_java.R;
import com.example.dagger_java.questions.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class QuestionsListViewMvc {

    interface Listener{
        void onRefreshClicked();
        void onQuestionClicked(Question clickedQuestion);
    }
    private LayoutInflater layoutInflater;
    private ViewGroup viewGroup;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private QuestionsAdapter questionsAdapter;

    public View rootView;

    private Context getContext() {
        return rootView.getContext();
    }

    private HashSet<Listener> listeners = new HashSet<>();

    public QuestionsListViewMvc(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.layoutInflater = layoutInflater;
        this.viewGroup = viewGroup;

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

    public void registerListener(Listener listener){
        listeners.add(listener);
    }

    public void unregisterListener(Listener listener){
        listeners.remove(listener);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }

    private void init(){

        rootView = layoutInflater.inflate(R.layout.layout_questions_list,viewGroup, false);

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
