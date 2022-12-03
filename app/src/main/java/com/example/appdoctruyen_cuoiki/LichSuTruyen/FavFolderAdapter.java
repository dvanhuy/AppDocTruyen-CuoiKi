package com.example.appdoctruyen_cuoiki.LichSuTruyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_cuoiki.R;

import java.util.List;

public class FavFolderAdapter extends RecyclerView.Adapter<FavFolderAdapter.favFolderViewHolder> {

    Context mContext;
    List<FavFolder> mlistFavFolder;

    public FavFolderAdapter(Context mContext, List<FavFolder> mlistFavFolder) {
        this.mContext = mContext;
        this.mlistFavFolder = mlistFavFolder;
    }

    @NonNull
    @Override
    public favFolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_favfolder,parent,false);
        FavFolderAdapter.favFolderViewHolder favFolderViewHolder = new FavFolderAdapter.favFolderViewHolder(view);

        return favFolderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull favFolderViewHolder holder, int position) {
        FavFolder folder = mlistFavFolder.get(position);
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
