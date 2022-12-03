package com.example.appdoctruyen_cuoiki.LichSuTruyen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.history;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.historyAdapter;
import com.example.appdoctruyen_cuoiki.R;

import java.util.ArrayList;
import java.util.List;


public class FavFolderFragment extends Fragment {

    private RecyclerView rcvFavFolder;
    private List<favFolder> firstFolder;

    public FavFolderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_folder, container, false);
        rcvFavFolder = view.findViewById(R.id.rcv_favFolder);
        favFolderAdapter adapter = new favFolderAdapter(getContext(),firstFolder);
        //rcvNovel.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvFavFolder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFavFolder.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstFolder = new ArrayList<>();
        firstFolder.add(new favFolder("Truyện hay"));
        firstFolder.add(new favFolder("Truyện mới đọc"));
        firstFolder.add(new favFolder("Truyện dở"));
        firstFolder.add(new favFolder("Truyện ..."));
    }
}