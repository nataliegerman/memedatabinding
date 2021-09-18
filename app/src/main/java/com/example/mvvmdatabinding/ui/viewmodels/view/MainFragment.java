package com.example.mvvmdatabinding.ui.viewmodels.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvvmdatabinding.data.model.MemeViewModel;
import com.example.mvvmdatabinding.databinding.FragmentMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);
        FragmentMainBinding fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);
        //here data must be an instance of the class MemeViewModel
        //set registerForActivityResult
        selectImageActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        //set selected image to view
                        //fragmentMainBinding.selectedImage.setImageURI(data.getData());
                        fragmentMainBinding.customImage.setImageUri(data.getData());
                    }
                });

        fragmentMainBinding.setFragmentViewModel(new MemeViewModel());
        fragmentMainBinding.executePendingBindings();

        fragmentMainBinding.selectImageBtn.setOnClickListener(view -> openGalleryToSelectImage());
        fragmentMainBinding.saveBtn.setOnClickListener(view -> saveImageToGalleryAndShare(fragmentMainBinding.customImage));

        return fragmentMainBinding.getRoot();
    }

//    @BindingAdapter({"toastMessage"})
//    public static void runMe(View view, String message) {
//        if (message != null) {
//            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
//        }
//    }

    //    @BindingAdapter({"selectImage"})
    private void openGalleryToSelectImage() {
        Intent pickImageIntent = new Intent();
        pickImageIntent.setType("image/*");
        pickImageIntent.setAction(Intent.ACTION_PICK);
        selectImageActivityResultLauncher.launch(pickImageIntent);
    }


    private void shareImageBitmap(Bitmap bitmap) {
        Uri uri = getImageToShare(bitmap);
        Intent intent = new Intent(Intent.ACTION_SEND);
        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        // setting type to image
        intent.setType("image/png");
        // calling startactivity() to share
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    // Retrieving the url to share
    private Uri getImageToShare(Bitmap bitmap) {
        File imagefolder = new File(getActivity().getCacheDir(), "images");
        Uri uri = null;
        try {
            imagefolder.mkdirs();
            String filename = "meme_" + System.currentTimeMillis() + ".png";
            File file = new File(imagefolder, filename);
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(getActivity(), "com.example.mvvmdatabinding.fileprovider", file);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return uri;
    }

    private void saveImageToGalleryAndShare(MemeImageView content) {
        //LinearLayout content = findViewById(R.id.rlid);
        content.setDrawingCacheEnabled(true);
        Bitmap bitmap = content.getDrawingCache();
        //file =new File(android.os.Environment.getExternalStorageDirectory(),"MyMemes");
        // if(!file.exists) file.mkdirs(). creates a folder under sdcard with MyMemes

        File file, f, dir;
        if (android.os.Build.VERSION.SDK_INT < 29) {
            // ==>  /storage/emulated/0    (Emulator)
            dir = Environment.getExternalStorageDirectory();

        } else { //if (android.os.Build.VERSION.SDK_INT >= 29)
            dir = getContext().getExternalFilesDir(null);
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(dir, "MyMemes");
            if (!file.exists()) {
                file.mkdirs();
            }
            String filename = "meme_" + System.currentTimeMillis();
            f = new File(file.getAbsolutePath() + File.separator + filename + ".png");

            FileOutputStream ostream = null;
            try {
                ostream = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);

            shareImageBitmap(bitmap);

            try {
                ostream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}