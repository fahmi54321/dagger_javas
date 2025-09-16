package com.example.dagger_java.screens.common.viewsmvc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import java.util.HashSet;

public class BaseViewMvc<LISTENER_TYPE> {

    private LayoutInflater layoutInflater;
    private ViewGroup viewGroup;

    @LayoutRes private Integer layoudId;

    public View rootView;

    public BaseViewMvc(LayoutInflater layoutInflater, ViewGroup viewGroup, Integer layoudId) {
        this.layoutInflater = layoutInflater;
        this.viewGroup = viewGroup;
        this.layoudId = layoudId;
        init();
    }

    private void init(){
        rootView = layoutInflater.inflate(layoudId,viewGroup,false);
    }

    protected final HashSet<LISTENER_TYPE> listeners = new HashSet<>();

    @SuppressWarnings("unchecked")
    protected  <T extends View> T findViewById(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }

    protected Context getContext() {
        return rootView.getContext();
    }

    public void registerListener(LISTENER_TYPE listener){
        listeners.add(listener);
    }

    public void unregisterListener(LISTENER_TYPE listener){
        listeners.remove(listener);
    }
}
