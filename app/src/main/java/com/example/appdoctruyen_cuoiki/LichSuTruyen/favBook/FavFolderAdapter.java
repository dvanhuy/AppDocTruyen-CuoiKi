package com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_cuoiki.R;

import java.util.List;

public class FavFolderAdapter extends RecyclerView.Adapter<FavFolderAdapter.FavFolderViewHolder> {

    Context mContext;
    List<FavFolder> mlistFavFolder;
    IClickItemListener clickItemListener;

    public FavFolderAdapter(Context mContext, List<FavFolder> mlistFavFolder,IClickItemListener clickItemListener) {
        this.mContext = mContext;
        this.mlistFavFolder = mlistFavFolder;
        this.clickItemListener = clickItemListener;
    }

    @NonNull
    @Override
    public FavFolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_favfolder,parent,false);
        FavFolderAdapter.FavFolderViewHolder favFolderViewHolder = new FavFolderAdapter.FavFolderViewHolder(view);

        return favFolderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavFolderViewHolder holder, int position) {
        FavFolder folder = mlistFavFolder.get(position);
        if (folder == null){
            return;
        }

        holder.tenFolder.setText(folder.getTenFolder());
        String idadata = mlistFavFolder.get(position).getIdFolder();
        holder.folderlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onClickItem(idadata);
            }
        });
        holder.folderlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickItemListener.onLongClickItem(idadata);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mlistFavFolder != null){
            return mlistFavFolder.size();
        }
        return 0;
    }

    public class FavFolderViewHolder extends RecyclerView.ViewHolder{
        TextView tenFolder;
        RelativeLayout folderlayout;
        public FavFolderViewHolder(@NonNull View itemView) {
            super(itemView);
            tenFolder = itemView.findViewById(R.id.tv_favFolder);
            folderlayout = itemView.findViewById(R.id.folder_layout);
        }
    }

    public interface IClickItemListener{
        void onClickItem(String idthumuc);
        void onLongClickItem(String idthumuc);
    }
}
