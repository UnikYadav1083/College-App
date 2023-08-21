package com.example.admincollegeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import kotlin.jvm.internal.PropertyReference0Impl;

public class UpdateTeacherActivity extends AppCompatActivity {

    private ImageView updateTeacherImage;
    private EditText updateTeacherName , updateTeacherEmail , updateTeacherPost;
    private Button updateTeacherbutton , deleteTeacherbutton;

    private String name , email ,image ,post;
    private final int REQ =1;
    private Bitmap bitmap = null;

    private String downloadUrl , uniquekey , category;

    private StorageReference storageReference;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        image = getIntent().getStringExtra("image");
       post = getIntent().getStringExtra("post");

         uniquekey = getIntent().getStringExtra("key");
         category = getIntent().getStringExtra("category");

        updateTeacherImage = findViewById(R.id.updateTeacherImage);
        updateTeacherName = findViewById(R.id.updataTeacherName);
        updateTeacherEmail = findViewById(R.id.updataTeacherEmail);
        updateTeacherPost = findViewById(R.id.updataTeacherPost);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        storageReference = FirebaseStorage.getInstance().getReference();

        updateTeacherbutton = findViewById(R.id.updateTeacherButton);
        deleteTeacherbutton = findViewById(R.id.deleteTeacherButton);

        try {
            Picasso.get().load(image).into(updateTeacherImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateTeacherEmail.setText(email);
        updateTeacherName.setText(name);
        updateTeacherPost.setText(post);

        updateTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


        updateTeacherbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = updateTeacherEmail.getText().toString();
                name = updateTeacherName.getText().toString();
                post = updateTeacherPost.getText().toString();
                checkValidation();
            }
        });

        deleteTeacherbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });


    }

    private void deleteData() {
        reference.child(category).child(uniquekey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateTeacherActivity.this , "Teacher Deleted Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateTeacherActivity.this, hello.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacherActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void checkValidation() {
        if(name.isEmpty()){
            updateTeacherName.setError("Empty");
            updateTeacherName.requestFocus();
        }
        else if(email.isEmpty()){
            updateTeacherEmail.setError("Empty");
            updateTeacherEmail.requestFocus();
        }
        else if(post.isEmpty()){
            updateTeacherPost.setError("Empty");
            updateTeacherPost.requestFocus();
        }
        else if(bitmap ==null){
            updateData(image);
        }
        else{
           uploadImage();
        }
    }

    private void updateData(String s) {
        HashMap hp = new HashMap();
        hp.put("name",name);
        hp.put("email" , email);
        hp.put("post" , post);
        hp.put("image", s);


        reference.child(category).child(uniquekey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateTeacherActivity.this , "Teacher Updated Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateTeacherActivity.this, hello.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Toast.makeText(UpdateTeacherActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void uploadImage() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG , 50 ,baos);
        byte[] finaling = baos.toByteArray();
        final StorageReference filepath;
        filepath = storageReference.child("Teachers").child(finaling+"jpg");
        final UploadTask uploadTask = filepath.putBytes(finaling);
        uploadTask.addOnCompleteListener(UpdateTeacherActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    updateData(downloadUrl);
                                }
                            }) ;
                        }
                    });
                }
                else{
                    Toast.makeText(UpdateTeacherActivity.this , "Something went wrong" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode ==RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
           updateTeacherImage.setImageBitmap(bitmap);
        }
    }


}