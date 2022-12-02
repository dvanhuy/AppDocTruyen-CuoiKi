package com.example.appdoctruyen_cuoiki;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TruyenDeCuRecycleAdapter extends RecyclerView.Adapter<TruyenDeCuRecycleAdapter.ViewHolder>{

    ArrayList<Truyen> listTruyen;
    Context context;
    IClickItemListener clickItemListener;

    public TruyenDeCuRecycleAdapter(ArrayList<Truyen> listTruyen, Context context,IClickItemListener clickItemListener) {
        this.listTruyen = listTruyen;
        this.context = context;
        this.clickItemListener = clickItemListener;
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
        holder.ten.setText(listTruyen.get(position).getTentruyen());
        holder.soChap.setText("Chương " + listTruyen.get(position).getSoChuong());
//        holder.hinhAnh.setImageResource(listTruyen.get(position).getHinhAnh());
        String idtruyen = listTruyen.get(position).getKey();
        Picasso.get().load(listTruyen.get(position).getImage()).into(holder.hinhAnh);
        holder.itemclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onClickItem(idtruyen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ten,soChap;
        ImageView hinhAnh;
        ConstraintLayout itemclick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.txtTenTacPham);
            soChap = itemView.findViewById(R.id.txtSoChap);
            hinhAnh = itemView.findViewById(R.id.imageViewLineDeCu);
            itemclick = itemView.findViewById(R.id.itemclick);
        }
    }

    public interface IClickItemListener{
        void onClickItem(String idtruyen);
    }
}
