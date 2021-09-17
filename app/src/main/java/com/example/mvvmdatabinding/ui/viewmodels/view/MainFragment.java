package com.example.mvvmdatabinding.ui.viewmodels.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvvmdatabinding.R;
import com.example.mvvmdatabinding.data.model.LoginViewModel;
import com.example.mvvmdatabinding.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private ActivityResultLauncher<Intent> selectImageActivityResultLauncher;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set registerForActivityResult
        selectImageActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        //TODO set image to view
                        //fragmentMainBinding.selectedImage.setImageURI(data.getData());
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);
        FragmentMainBinding fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);
//        //here data must be an instance of the class LoginViewModel
        fragmentMainBinding.setFragmentViewModel(new LoginViewModel());
        fragmentMainBinding.executePendingBindings();
        fragmentMainBinding.button.setOnClickListener(view -> openGalleryToSelectImage());
        return fragmentMainBinding.getRoot();
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

//    @BindingAdapter({"selectImage"})
    private void openGalleryToSelectImage() {
        Intent pickImageIntent = new Intent();
        pickImageIntent.setType("image/*");
        pickImageIntent.setAction(Intent.ACTION_PICK);
        selectImageActivityResultLauncher.launch(pickImageIntent);
    }
}