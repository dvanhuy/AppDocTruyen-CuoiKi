package com.example.appdoctruyen_cuoiki.LichSuTruyen.history;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.favBook;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.favBookAdapter;
import com.example.appdoctruyen_cuoiki.R;

import java.util.ArrayList;
import java.util.List;


public class History_bookFragment extends Fragment {

    View v;
    private RecyclerView rcvHis;
    private List<history> firstHis;

    public History_bookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_history_book, container, false);
        rcvHis = (RecyclerView) v.findViewById(R.id.rcv_historyBook);
        historyAdapter adapter = new historyAdapter(getContext(),firstHis);
        //rcvNovel.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvHis.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvHis.setAdapter(adapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstHis = new ArrayList<>();
        firstHis.add(new history("Boruto",R.drawable.home_list_truyen1));
        firstHis.add(new history("Tấm Cám",R.drawable.home_list_truyen5));
        firstHis.add(new history("Truyện Kiều",R.drawable.home_list_truyen6));
        firstHis.add(new history("Tình Yêu Trở Lại",R.drawable.home_list_truyen3));
    }
}