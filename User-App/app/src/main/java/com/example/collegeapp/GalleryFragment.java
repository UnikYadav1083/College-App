package com.example.collegeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

   RecyclerView convoRecycler , otherRecycler;
   GalleryAdapter adapter;
   DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);

        convoRecycler = view.findViewById(R.id.convoRecycler);
        otherRecycler = view.findViewById(R.id.otherRecycler);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getconvoImage();
        getotherImage();

        return view;
    }


    private void getotherImage() {
        reference.child("Others Events").addValueEventListener(new ValueEventListener() {

            List<String> lists = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap :snapshot.getChildren()){
                    String data = String.valueOf(snap.getValue());
                    lists.add(data);
                }

                adapter =new GalleryAdapter(getContext() , lists);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                otherRecycler.setLayoutManager(manager);
                otherRecycler.setHasFixedSize(true);
                otherRecycler.setLayoutManager(new GridLayoutManager(getContext() ,3));
                otherRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getconvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {

            List<String> lists = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap :snapshot.getChildren()){
                    String data = String.valueOf(snap.getValue());
                    lists.add(data);
                }

                adapter = new GalleryAdapter(getContext() , lists);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                convoRecycler.setLayoutManager(manager);
                convoRecycler.setHasFixedSize(true);

                convoRecycler.setLayoutManager(new GridLayoutManager(getContext() ,3));
                convoRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}