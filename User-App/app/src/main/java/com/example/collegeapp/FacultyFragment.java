package com.example.collegeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView csDepartment  , electricalDepartment,mechanicalDepartment ,civilDepartment;
    private LinearLayout csnoData , electricalnoData , mechanicalnoData , civilnoData;

    private List<TeacherData> list1 , list2 , list3 , list4;
    private TeacherAdapter adapter;

    private DatabaseReference reference , dbref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_faculty, container, false);



        csDepartment = view.findViewById(R.id.csDepartment);
        electricalDepartment = view.findViewById(R.id.electricalDepartment);
        mechanicalDepartment = view.findViewById(R.id.mechanicalDepartment);
        civilDepartment = view.findViewById(R.id.civilDepartment);

        csnoData = view.findViewById(R.id.csnodata);
        electricalnoData = view.findViewById(R.id.electricalnodata);
        mechanicalnoData = view.findViewById(R.id.mechanicalnodata);
        civilnoData = view.findViewById(R.id.civilnodata);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartment();
        electricalDepartment();
        mechanicalDepartment();
        civilDepartment();


        return view;
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
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1 , getContext());
                    csDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage() ,Toast.LENGTH_SHORT).show();
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
                    electricalDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2 , getContext() );
                    electricalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage() ,Toast.LENGTH_SHORT).show();
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
                    mechanicalDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3 , getContext());
                    mechanicalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage() ,Toast.LENGTH_SHORT).show();
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
                    civilDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4 , getContext());
                    civilDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }

}