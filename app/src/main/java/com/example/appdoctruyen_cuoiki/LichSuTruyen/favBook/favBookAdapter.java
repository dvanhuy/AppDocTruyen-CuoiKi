package com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_cuoiki.Fragment3;
import com.example.appdoctruyen_cuoiki.R;

import java.util.List;

public class favBookAdapter extends RecyclerView.Adapter<favBookAdapter.favBookViewHolder> {

    Context mContext;
    List<favBook> mListFavBook;

    public favBookAdapter(Context mContext, List<favBook> mListFavBook) {
        this.mContext = mContext;
        this.mListFavBook = mListFavBook;
    }

    @NonNull
    @Override
    public favBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_favbook,parent,false);
        favBookViewHolder novelViewHolder = new favBookViewHolder(v);

        return novelViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull favBookViewHolder holder, int position) {
        favBook novel = mListFavBook.get(position);
        if (novel == null){
            return;
        }

        holder.hinhAnh.setImageResource(novel.getHinhAnh());
        holder.ten.setText(novel.getTen());
    }

    @Override
    public int getItemCount() {
        if (mListFavBook != null){
            return mListFavBook.size();
        }
        return 0;
    }


    public class favBookViewHolder extends RecyclerView.ViewHolder{

    TextView ten;
    ImageView hinhAnh;
    public favBookViewHolder(@NonNull View itemView) {
        super(itemView);
        ten = itemView.findViewById(R.id.tv_favbook);
        hinhAnh = itemView.findViewById(R.id.img_favbook);
    }
}
}
