package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;


public class FullimageView extends AppCompatActivity {

    private PhotoView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage_view);

        String image = getIntent().getStringExtra("image");
        imageView = findViewById(R.id.imageview);

        Glide.with(this).load(image).into(imageView);


    }
}