package com.example.collegeapp;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class BranchAdapter extends PagerAdapter {

    private Context context;
    private List<BranchModel> list;

    public BranchAdapter(Context context, List<BranchModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.branch_item_layout ,container ,false);
        ImageView bricon;
        TextView brdesc , brtitle;

        bricon =view.findViewById(R.id.br_icon);

        brtitle = view.findViewById(R.id.br_title);

        brdesc = view.findViewById(R.id.br_description);


        bricon.setImageResource(list.get(position).getImg());
        brtitle.setText(list.get(position).getTitle());
        brdesc.setText(list.get(position).getDescription());

        container.addView(view , 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
