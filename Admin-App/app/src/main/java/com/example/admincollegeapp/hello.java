package com.example.admincollegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class hello extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView csDepartment  , electricalDepartment,mechanicalDepartment ,civilDepartment;
    private LinearLayout csnoData , electricalnoData , mechanicalnoData , civilnoData;

    private List<TeacherData> list1 , list2 , list3 , list4;
    private TeacherAdapter adapter;

    private DatabaseReference reference , dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        csDepartment = findViewById(R.id.csDepartment);
        electricalDepartment = findViewById(R.id.electricalDepartment);
        mechanicalDepartment = findViewById(R.id.mechanicalDepartment);
        civilDepartment = findViewById(R.id.civilDepartment);

        csnoData = findViewById(R.id.csnodata);
        electricalnoData = findViewById(R.id.electricalnodata);
        mechanicalnoData = findViewById(R.id.mechanicalnodata);
        civilnoData = findViewById(R.id.civilnodata);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartment();
        electricalDepartment();
        mechanicalDepartment();
        civilDepartment();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hello.this , AddTeacher.class));
            }
        });
    }

    private void csDepartment() {
        dbref = reference.child("Computer Science");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               list1 = new ArrayList<>();
               if(!snapshot.exists()){
                   csnoData.setVisibility(View.VISIBLE);
                   csDepartment.setVisibility(View.GONE);
               }
               else{
                   csnoData.setVisibility(View.GONE);
                   csDepartment.setVisibility(View.VISIBLE);
                   for(DataSnapshot snap: snapshot.getChildren()){
                       TeacherData data  = snap.getValue(TeacherData.class);
                       list1.add(data);
                   }
                   csDepartment.setHasFixedSize(true);
                   csDepartment.setLayoutManager(new LinearLayoutManager(hello.this));
                   adapter = new TeacherAdapter(list1 , hello.this ,"Computer Science");
                   csDepartment.setAdapter(adapter);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hello.this,error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void electricalDepartment() {
        dbref = reference.child("Electrical");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if(!snapshot.exists()){
                    electricalnoData.setVisibility(View.VISIBLE);
                    electricalDepartment.setVisibility(View.GONE);
                }
                else{
                    electricalnoData.setVisibility(View.GONE);
                   electricalDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snap: snapshot.getChildren()){
                        TeacherData data  = snap.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    electricalDepartment.setHasFixedSize(true);
                    electricalDepartment.setLayoutManager(new LinearLayoutManager(hello.this));
                    adapter = new TeacherAdapter(list2 , hello.this ,"Electrical");
                    electricalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hello.this,error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechanicalDepartment() {
        dbref = reference.child("Mechanical");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if(!snapshot.exists()){
                    mechanicalnoData.setVisibility(View.VISIBLE);
                    mechanicalDepartment.setVisibility(View.GONE);
                }
                else{
                    mechanicalnoData.setVisibility(View.GONE);
                    mechanicalDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snap: snapshot.getChildren()){
                        TeacherData data  = snap.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    mechanicalDepartment.setHasFixedSize(true);
                    mechanicalDepartment.setLayoutManager(new LinearLayoutManager(hello.this));
                    adapter = new TeacherAdapter(list3 , hello.this ,"Mechanical");
                    mechanicalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hello.this,error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void civilDepartment() {
        dbref = reference.child("Civil");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if(!snapshot.exists()){
                    civilnoData.setVisibility(View.VISIBLE);
                    civilDepartment.setVisibility(View.GONE);
                }
                else{
                    civilnoData.setVisibility(View.GONE);
                    civilDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snap: snapshot.getChildren()){
                        TeacherData data  = snap.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    civilDepartment.setHasFixedSize(true);
                    civilDepartment.setLayoutManager(new LinearLayoutManager(hello.this));
                    adapter = new TeacherAdapter(list4 , hello.this ,"Civil");
                    civilDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hello.this,error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }

}