package com.example.mvvmdatabinding.data.model;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvmdatabinding.BR;

public class MemeViewModel extends BaseObservable {
    private Meme meme;
    private String successMessage = "successful";
    private String errorMessage = "top or bottom not valid";

    @Bindable
    private String selectedImage = "https://images.unsplash.com/photo-1518020382113-a7e8fc38eac9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=660&q=80";

    @Bindable
    private String toastMessage = null;

    @Bindable
    private String topMessage = null;
    @Bindable
    private String bottomMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }



    public void setMemeImageUrl(String imageURL) {
        meme.setImageUrl(imageURL);
        notifyPropertyChanged(BR.memeTopTitle);
    }

    public void setMemeTopTitle(String topTitle) {
        meme.setTopTitle(topTitle);
        notifyPropertyChanged(BR.memeTopTitle);
    }

    @Bindable
    public String getMemeTopTitle() {
        return meme.getTopTitle();
    }

    @Bindable
    public String getMemeBottomTitle() {
        return meme.getBottomTitle();
    }

    public void setMemeBottomTitle(String password) {
        meme.setBottomTitle(password);
        notifyPropertyChanged(BR.memeBottomTitle);
    }
    @Bindable
    public String getSelectedImageUrl() {
        return selectedImage;
    }


    public MemeViewModel() {
        meme = new Meme("", "", "");
    }

    public void onTryClicked() {
        if (isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    //one of the titles should not be empty
    public boolean isInputDataValid() {
        if (TextUtils.isEmpty(getMemeTopTitle()) && TextUtils.isEmpty(getMemeBottomTitle())) {
            return false;
        }
        return true;
    }

    public void onImageClicked() {
        notifyPropertyChanged(BR.selectedImage);
    }

    public void onTopTextChanged(CharSequence s, int start, int before, int count) {
        topMessage = s.toString();
        meme.setTopTitle(s.toString());
        notifyPropertyChanged(BR.memeTopTitle);
    }

    public void onBottomTextChanged(CharSequence s, int start, int before, int count) {
        bottomMessage = s.toString();
        meme.setBottomTitle(s.toString());
        notifyPropertyChanged(BR.memeBottomTitle);
    }
}