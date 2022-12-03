package com.example.appdoctruyen_cuoiki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TheLoaiTruyenRecycleAdapter extends RecyclerView.Adapter<TheLoaiTruyenRecycleAdapter.ViewHolder> {
    @NonNull
    ArrayList<TheLoaiTruyen> listTheLoai;
    Context context;
    IClickItemListener clickItemListener;

    public TheLoaiTruyenRecycleAdapter(@NonNull ArrayList<TheLoaiTruyen> listTheLoai, Context context, IClickItemListener clickItemListener) {
        this.listTheLoai = listTheLoai;
        this.context = context;
        this.clickItemListener = clickItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.line_khampha, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ten.setText(listTheLoai.get(position).getTenTheLoai());
        holder.hinhAnh.setImageResource(listTheLoai.get(position).getHinhAnh());
        String theloai =  listTheLoai.get(position).getMatheloai();
        holder.theloaiitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onClickItem(theloai);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTheLoai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView ten;
        ImageView hinhAnh;
        ConstraintLayout theloaiitem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.txtTenTheLoai_KhamPha);
            hinhAnh = itemView.findViewById(R.id.imageViewLineKhamPha);
            theloaiitem = itemView.findViewById(R.id.theloaiitem);
        }
    }

    public interface IClickItemListener{
        void onClickItem(String idtheloai);
    }
}
