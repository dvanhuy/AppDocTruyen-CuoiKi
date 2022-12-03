package com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyen;
import com.example.appdoctruyen_cuoiki.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChapterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChapterListFragment extends Fragment {

    ListView listviewchap;
    ArrayAdapter arrayAdapter;
    ArrayList<String> strings;
    int sochuong=1;

    public ChapterListFragment() {
        // Required empty public constructor
    }
    public ChapterListFragment(int sochuonglist) {
        sochuong=sochuonglist;
    }
    public static ChapterListFragment newInstance(String param1, String param2) {
        ChapterListFragment fragment = new ChapterListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter_list, container, false);
        strings = new ArrayList<>();
        for (int i = 1; i <= sochuong; i++) {
            strings.add("Chương "+String.valueOf(i));
        }
        listviewchap = view.findViewById(R.id.listviewchap);
        arrayAdapter =new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strings);
        listviewchap.setAdapter(arrayAdapter);
        ChiTietTruyen chiTietTruyen = (ChiTietTruyen) getActivity();
        listviewchap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chiTietTruyen.goToReadBook("chuong"+String.valueOf(i+1));
            }
        });
        return view;
    }

}