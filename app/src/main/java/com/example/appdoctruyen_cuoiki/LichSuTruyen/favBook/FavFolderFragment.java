package com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdoctruyen_cuoiki.DanhMucTruyen;
import com.example.appdoctruyen_cuoiki.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FavFolderFragment extends Fragment {

    private RecyclerView rcvFavFolder;
    private List<FavFolder> firstFolder;
    DatabaseReference databaseReference;
    FavFolderAdapter favFolderAdapter;

    public FavFolderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firstFolder = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_fav_folder, container, false);
        rcvFavFolder = view.findViewById(R.id.rcv_favFolder);
        favFolderAdapter = new FavFolderAdapter(getContext(), firstFolder, new FavFolderAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String idthumuc) {
                Intent intent = new Intent(getContext(), DanhMucTruyen.class);
                intent.putExtra("idthumuc",idthumuc);
                startActivity(intent);
            }
        });
        //rcvNovel.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvFavFolder.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom =30;
            }
        });
        rcvFavFolder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFavFolder.setAdapter(favFolderAdapter);
        initData();
        return view;
    }

    public void initData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("YeuThich");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                firstFolder.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String idtruyen = String.valueOf(dataSnapshot.getKey());
                    String tentruyen = String.valueOf(dataSnapshot.child("ten").getValue());
                    FavFolder favFolder = new FavFolder(idtruyen,tentruyen);
                    firstFolder.add(favFolder);
                }
                favFolderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}