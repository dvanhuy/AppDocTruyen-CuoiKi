package com.example.appdoctruyen_cuoiki;

import android.content.Context;
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

public class TimTruyenAdapter extends RecyclerView.Adapter<TimTruyenAdapter.ViewHolder>{

    ArrayList<Truyen> listTruyen;
    Context context;
    TimTruyenAdapter.IClickItemListener clickItemListener;

    public TimTruyenAdapter(ArrayList<Truyen> listTruyen, Context context, TimTruyenAdapter.IClickItemListener clickItemListener) {
        this.listTruyen = listTruyen;
        this.context = context;
        this.clickItemListener = clickItemListener;
    }
    @NonNull
    @Override
    public TimTruyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.itemtruyenngang,parent,false);
        return new TimTruyenAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimTruyenAdapter.ViewHolder holder, int position) {
        holder.tentruyen.setText(listTruyen.get(position).getTentruyen());
        holder.mota.setText(listTruyen.get(position).getMota());
        holder.sochuong.setText("Số chương : "+listTruyen.get(position).getSoChuong());
//        holder.hinhAnh.setImageResource(listTruyen.get(position).getHinhAnh());
        String idtruyen = listTruyen.get(position).getKey();
        Picasso.get().load(listTruyen.get(position).getImage()).into(holder.hinhAnh);
        holder.itemclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onClickItem(idtruyen);
            }
        });
        holder.itemclick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickItemListener.onLongClickItem(idtruyen);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tentruyen,mota,sochuong;
        ImageView hinhAnh;
        ConstraintLayout itemclick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tentruyen = itemView.findViewById(R.id.tentruyen);
            mota = itemView.findViewById(R.id.motatruyen);
            sochuong = itemView.findViewById(R.id.sochuongtxt);
            hinhAnh = itemView.findViewById(R.id.imgbiatruyen);
            itemclick = itemView.findViewById(R.id.itemtruyen);

        }
    }

    public interface IClickItemListener{
        void onClickItem(String idtruyen);
        void onLongClickItem(String idtruyen);
    }
}
