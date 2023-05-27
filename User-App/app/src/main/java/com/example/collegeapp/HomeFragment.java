package com.example.collegeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageView = view.findViewById(R.id.map);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });


        viewPager = view.findViewById(R.id.viewpager);

        Adapter adapter = new Adapter(getContext());
        viewPager.setAdapter(adapter);

        return view;
    }

    private void openMap() {
         Uri  uri =Uri.parse("geo:0, 0?q=IIITV-ICD");
        Intent intent =new Intent(Intent.ACTION_VIEW , uri);

        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}
