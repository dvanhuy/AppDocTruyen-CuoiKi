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

public class TruyenDeCuRecycleAdapter extends RecyclerView.Adapter<TruyenDeCuRecycleAdapter.ViewHolder>{

    ArrayList<Truyen> listTruyen;
    Context context;

    public TruyenDeCuRecycleAdapter(ArrayList<Truyen> listTruyen, Context context) {
        this.listTruyen = listTruyen;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.line_decu,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ten.setText(listTruyen.get(position).getTen());
        holder.soChap.setText("Chương " +listTruyen.get(position).getSoChap());
        holder.hinhAnh.setImageResource(listTruyen.get(position).getHinhAnh());
    }

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ten,soChap;
        ImageView hinhAnh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.txtTenTacPham);
            soChap = itemView.findViewById(R.id.txtSoChap);
            hinhAnh = itemView.findViewById(R.id.imageViewLineDeCu);
        }
    }
}
