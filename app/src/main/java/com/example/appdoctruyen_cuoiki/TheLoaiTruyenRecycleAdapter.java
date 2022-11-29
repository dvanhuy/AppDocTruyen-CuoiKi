package com.example.appdoctruyen_cuoiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TheLoaiTruyenRecycleAdapter extends RecyclerView.Adapter<TheLoaiTruyenRecycleAdapter.ViewHolder> {
    @NonNull
    ArrayList<TheLoaiTruyen> listTheLoai;
    Context context;

    public TheLoaiTruyenRecycleAdapter(@NonNull ArrayList<TheLoaiTruyen> listTheLoai, Context context) {
        this.listTheLoai = listTheLoai;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.line_khampha, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ten.setText(listTheLoai.get(position).getTenTheLoai());
        holder.hinhAnh.setImageResource(listTheLoai.get(position).getHinhAnh());
    }

    @Override
    public int getItemCount() {
        return listTheLoai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView ten;
        ImageView hinhAnh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.txtTenTheLoai_KhamPha);
            hinhAnh = itemView.findViewById(R.id.imageViewLineKhamPha);
        }
    }
}
