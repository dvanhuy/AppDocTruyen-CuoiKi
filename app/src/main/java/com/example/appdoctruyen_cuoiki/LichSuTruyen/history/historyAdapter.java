package com.example.appdoctruyen_cuoiki.LichSuTruyen.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.favBook;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.favBookAdapter;
import com.example.appdoctruyen_cuoiki.R;

import java.util.List;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.historyViewHolder> {

    Context mContext;
    List<history> mListHistory;

    public historyAdapter(Context mContext, List<history> mListHistory) {
        this.mContext = mContext;
        this.mListHistory = mListHistory;
    }

    @NonNull
    @Override
    public historyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history,parent,false);
        historyViewHolder novelViewHolder = new historyViewHolder(view);

        return novelViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull historyViewHolder holder, int position) {
        history novel = mListHistory.get(position);
        if (novel == null){
            return;
        }

        holder.hinhAnh.setImageResource(novel.getHinhAnh());
        holder.ten.setText(novel.getTen());
    }

    @Override
    public int getItemCount() {
        if (mListHistory != null){
            return mListHistory.size();
        }
        return 0;
    }


    public class historyViewHolder extends RecyclerView.ViewHolder{

        TextView ten;
        ImageView hinhAnh;
        public historyViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_history);
            hinhAnh = itemView.findViewById(R.id.img_history);
        }
    }
}
