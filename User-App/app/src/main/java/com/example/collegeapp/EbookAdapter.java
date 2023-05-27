package com.example.collegeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewholder> {

    private Context context;
    private List<Ebookdata> list;

    public EbookAdapter(Context context, List<Ebookdata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent,false);
        return new EbookViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewholder holder, final int position) {

        holder.ebookname.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(context , PdfviewerActivity.class);
              intent.putExtra("pdfurl" ,list.get(position).getPdfurl());
              context.startActivity(intent);
            }
        });

        holder.ebookdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPdfurl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class EbookViewholder extends RecyclerView.ViewHolder {

        private ImageView ebookdownload;
        private TextView ebookname;


        public EbookViewholder(@NonNull View itemView) {
            super(itemView);

            ebookname = itemView.findViewById(R.id.ebookname);
            ebookdownload = itemView.findViewById(R.id.ebookdownload);

        }
    }
}
