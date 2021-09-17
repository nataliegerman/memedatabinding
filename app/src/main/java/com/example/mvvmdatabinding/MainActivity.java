package com.example.mvvmdatabinding;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmdatabinding.ui.viewmodels.view.MainFragment;

public class MainActivity extends AppCompatActivity {

    private static ActivityResultLauncher<Intent> selectImageActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment fragmentMain = new MainFragment();

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, fragmentMain);
        fragmentTransaction.commit();

//        FragmentMainBinding fragmentMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        fragmentMainBinding.setFragmentViewModel(new LoginViewModel());
//        fragmentMainBinding.executePendingBindings();
//
//        //set registerForActivityResult
//        selectImageActivityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        //TODO set image to view
//                        fragmentMainBinding.selectedImage.setImageURI(data.getData());
//                    }
//                });
    }

//    @BindingAdapter({"toastMessage"})
//    public static void runMe(View view, String message) {
//        if (message != null)
//            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
//    }

    public static void openGallery(View view, String url) {
        Intent pickImageIntent = new Intent();
        pickImageIntent.setType("image/*");
        pickImageIntent.setAction(Intent.ACTION_PICK);
        selectImageActivityResultLauncher.launch(pickImageIntent);
    }
}