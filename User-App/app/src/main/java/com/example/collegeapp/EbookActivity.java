package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookrecycler;
    private DatabaseReference reference;
    private List<Ebookdata> list;
    private EbookAdapter adapter;

    LinearLayout shimmerlayout;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ebooks");

        shimmerlayout = findViewById(R.id.shimmerlayout);
        shimmerFrameLayout = findViewById(R.id.shimmerview);
        ebookrecycler = findViewById(R.id.ebookrecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");

        getData();

    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snap: snapshot.getChildren()){
                    Ebookdata data = snap.getValue(Ebookdata.class);
                    list.add(data);
                }
                adapter = new EbookAdapter(EbookActivity.this ,list);
                ebookrecycler.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                ebookrecycler.setAdapter(adapter);

                shimmerFrameLayout.stopShimmer();
                shimmerlayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbookActivity.this ,error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }
}