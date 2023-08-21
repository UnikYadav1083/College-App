package com.example.admincollegeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView uploadNotice , addGalleryImage , addEbook , faculty ,deleteNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice = findViewById(R.id.addNotice);
        uploadNotice.setOnClickListener(this);

        addGalleryImage = findViewById(R.id.addGalleryImage);
        addGalleryImage.setOnClickListener(this);

        addEbook = findViewById(R.id.addEbook);
        addEbook.setOnClickListener(this);

        faculty = findViewById(R.id.faculty);
        faculty.setOnClickListener(this);

        deleteNotice = findViewById(R.id.deleteNotice);
        deleteNotice.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.addNotice:
                 intent = new Intent(MainActivity.this , UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                 intent = new Intent(MainActivity.this , UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent = new Intent(MainActivity.this , UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent = new Intent(MainActivity.this ,hello.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent = new Intent(MainActivity.this ,DeleteNoticeActivity.class);
                startActivity(intent);
                break;
        }
    }
}