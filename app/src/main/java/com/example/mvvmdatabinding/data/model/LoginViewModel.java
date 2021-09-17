package com.example.mvvmdatabinding.data.model;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvmdatabinding.BR;
import com.example.mvvmdatabinding.data.model.Meme;

public class LoginViewModel extends BaseObservable {
    private Meme meme;
    private String successMessage = "successful";
    private String errorMessage = "top or bottom not valid";

    @Bindable
    private String selectedImage = "https://images.unsplash.com/photo-1518020382113-a7e8fc38eac9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=660&q=80";

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void setEmployeeEmail(String email) {
        meme.setEmail(email);
        notifyPropertyChanged(BR.employeeEmail);
    }

    public void setSelectedImage(String imageURL) {
        meme.setImageUrl(imageURL);
        notifyPropertyChanged(BR.employeeEmail);
    }

    @Bindable
    public String getEmployeeEmail() {
        return meme.getEmail();
    }

    @Bindable
    public String getEmployeePassword() {
        return meme.getPassword();
    }

    public void setEmployeePassword(String password) {
        meme.setPassword(password);
        notifyPropertyChanged(BR.employeePassword);
    }

    public String getSelectedImageUrl() {
        return selectedImage;
    }


    public LoginViewModel() {
        meme = new Meme("", "", "");
    }

    public void onLoginClicked() {
        if (isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getEmployeeEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmployeeEmail()).matches() &&
                getEmployeePassword().length() > 5;
    }

    public void onImageClicked() {
        notifyPropertyChanged(BR.selectedImage);
    }
}