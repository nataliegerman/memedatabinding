package com.example.mvvmdatabinding.data.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Meme {
    private String topTitle;
    private String bottomTitle;
    private String imageUrl;

    public Meme(String topTitle, String bottomTitle, String imageUrl) {
        this.topTitle = topTitle;
        this.bottomTitle = bottomTitle;
        this.imageUrl = imageUrl;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setBottomTitle(String bottomTitle) {
        this.bottomTitle = bottomTitle;
    }

    public String getBottomTitle() {
        return bottomTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("android:loadDefaultImage")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView).load(imageUrl).into(imageView);
    }
}
