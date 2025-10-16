package com.example.dagger_java.screens.imageloader;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

public class ImageLoader {

    private final AppCompatActivity activity;

    @Inject
    public ImageLoader(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void showImage(String url, ImageView image){
        Glide
                .with(activity)
                .load(url)
                .centerCrop()
                .into(image);
    }
}
