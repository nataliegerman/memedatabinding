package com.example.mvvmdatabinding.ui.viewmodels.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mvvmdatabinding.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * TODO: document your custom view class.
 */
public class MemeImageView extends ConstraintLayout {
    private String mTopTitleString;
    private String mBottomTitleString;

    private TextView topTitle;
    private TextView bottomTitle;
    private ImageView imageView;

    private int mExampleColor = Color.RED;
    private float mExampleDimension = 0;


    public MemeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.sample_meme_image_view, this, true);
        init(attrs, 0);
    }

    public MemeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MemeImageView, defStyle, 0);

        try {
            mTopTitleString = a.getString(R.styleable.MemeImageView_topTitle);
            mBottomTitleString = a.getString(R.styleable.MemeImageView_bottomTitle);

            mExampleColor = a.getColor(R.styleable.MemeImageView_exampleColor, mExampleColor);
            mExampleDimension = a.getDimension(R.styleable.MemeImageView_exampleDimension, mExampleDimension);

            imageView = findViewById(R.id.memeImage);
            topTitle = findViewById(R.id.memeTopTitle);
            bottomTitle =findViewById(R.id.memeBottomTitle);
            //set attributes to views
            topTitle.setText(mTopTitleString);
            bottomTitle.setText(mBottomTitleString);
        } finally {
            a.recycle();
        }

    }

    public void setTopTitle(String topTitleText) {
        mTopTitleString = topTitleText;
        topTitle.setText(mTopTitleString);
    }
    public void setBottomTitle(String bottomTitleText) {
        mBottomTitleString = bottomTitleText;
        bottomTitle.setText(mBottomTitleString);
    }

    public void setImageBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
    public void setImageDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }
    public void setImageUri(Uri uri) {
        imageView.setImageURI(uri);
    }
}