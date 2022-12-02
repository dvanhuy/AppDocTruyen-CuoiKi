package com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.history;
import com.example.appdoctruyen_cuoiki.R;

import java.util.ArrayList;
import java.util.List;

public class favBookFragment extends Fragment {

    private RecyclerView rcvFav;
    private List<favBook> firstFavBook;

    public favBookFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_book, container, false);
        rcvFav = (RecyclerView) view.findViewById(R.id.rcv_favBook);
        favBookAdapter adapter = new favBookAdapter(getContext(),firstFavBook);
        //rcvNovel.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFav.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            firstFavBook = new ArrayList<>();
            firstFavBook.add(new favBook("Boruto",R.drawable.home_list_truyen1));
            firstFavBook.add(new favBook("Tàn Chi Lệnh",R.drawable.home_list_truyen2));
            firstFavBook.add(new favBook("Tình Yêu Trở Lại",R.drawable.home_list_truyen1));
            firstFavBook.add(new favBook("Vương quốc bí ẩn",R.drawable.home_list_truyen1));
    }
}