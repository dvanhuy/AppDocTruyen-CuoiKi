package com.example.appdoctruyen_cuoiki;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.favBook;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.favBookAdapter;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.history;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.historyAdapter;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rcvFav, rcvHis;
    private List<favBook> firstFavBook;
    private List<history> firstHis;

    public Fragment3() {
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        rcvFav = view.findViewById(R.id.rcv_favorBook);
        favBookAdapter favBookAdapter = new favBookAdapter(getContext(),firstFavBook);
        rcvFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFav.setAdapter(favBookAdapter);

        rcvHis = view.findViewById(R.id.rcv_HistoryBook);
        historyAdapter historydapter = new historyAdapter(getContext(),firstHis);
        //historyAdapter.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvHis.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvHis.setAdapter(historydapter);

        initData();
        return view;
    }

    public void initData() {

        firstFavBook = new ArrayList<>();
        firstFavBook.add(new favBook("Boruto",R.drawable.home_list_truyen1));
        firstFavBook.add(new favBook("Tàn Chi Lệnh",R.drawable.home_list_truyen2));
        firstFavBook.add(new favBook("Tình Yêu Trở Lại",R.drawable.home_list_truyen3));
        firstFavBook.add(new favBook("Vương quốc bí ẩn",R.drawable.home_list_truyen4));

        firstHis = new ArrayList<>();
        firstHis.add(new history("Boruto",R.drawable.home_list_truyen1));
        firstHis.add(new history("Tấm Cám",R.drawable.home_list_truyen5));
        firstHis.add(new history("Truyện Kiều",R.drawable.home_list_truyen6));
        firstHis.add(new history("Tình Yêu Trở Lại",R.drawable.home_list_truyen3));
    }
}