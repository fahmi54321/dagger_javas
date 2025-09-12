package com.example.dagger_java.screens.common.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.example.dagger_java.R;

public class MyToolbar extends Toolbar {

    public interface NavigateUpListener {
        void onNavigationUpClicked();
    }

    private NavigateUpListener navigateUpListener;
    private FrameLayout navigateUp;

    public MyToolbar(Context context) {
        super(context);
        init(context);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my_toolbar, this, true);
        setContentInsetsRelative(0, 0);
        navigateUp = view.findViewById(R.id.navigate_up);

        navigateUp.setOnClickListener(v -> {
            if (navigateUpListener != null) {
                navigateUpListener.onNavigationUpClicked();
            }
        });
    }

    public void setNavigateUpListener(NavigateUpListener listener) {
        this.navigateUpListener = listener;
        if (navigateUp != null) {
            navigateUp.setVisibility(View.VISIBLE);
        }
    }
}

