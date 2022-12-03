package com.example.appdoctruyen_cuoiki.LichSuTruyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.history;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.historyAdapter;
import com.example.appdoctruyen_cuoiki.R;

import java.util.List;

public class favFolderAdapter extends RecyclerView.Adapter<favFolderAdapter.favFolderViewHolder> {

    Context mContext;
    List<favFolder> mlistFavFolder;

    public favFolderAdapter(Context mContext, List<favFolder> mlistFavFolder) {
        this.mContext = mContext;
        this.mlistFavFolder = mlistFavFolder;
    }

    @NonNull
    @Override
    public favFolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_favfolder,parent,false);
        favFolderAdapter.favFolderViewHolder favFolderViewHolder = new favFolderAdapter.favFolderViewHolder(view);

        return favFolderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull favFolderViewHolder holder, int position) {
        favFolder folder = mlistFavFolder.get(position);
        if (folder == null){
            return;
        }

        holder.tenFolder.setText(folder.getTenFolder());
    }

    @Override
    public int getItemCount() {
        if (mlistFavFolder != null){
            return mlistFavFolder.size();
        }
        return 0;
    }

    public class favFolderViewHolder extends RecyclerView.ViewHolder{
        TextView tenFolder;
        public favFolderViewHolder(@NonNull View itemView) {
            super(itemView);
            tenFolder = itemView.findViewById(R.id.tv_favFolder);
        }
    }
}
