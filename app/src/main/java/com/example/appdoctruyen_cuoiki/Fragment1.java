package com.example.appdoctruyen_cuoiki;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ViewFlipper viewFlipper;
    // TODO: Rename and change types of parameters
    Context thiscontext;
    DatabaseReference database;
    RecyclerView recyclerView;
    AutoCompleteTextView txtSearch;
    Button btTiepTuc;
    public Fragment1() {
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
        thiscontext = getContext();
        View view = inflater.inflate(R.layout.fragment_1,container,false);

        txtSearch = view.findViewById(R.id.searchBook_Home);
        btTiepTuc = view.findViewById(R.id.btTiepTucHarry);

        btTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChiTietTruyen.class);
                intent.putExtra("idtruyen", "Truyen1");
                startActivity(intent);
            }
        });

        viewFlipper = view.findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval(3000);//3s
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(thiscontext, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(thiscontext,android.R.anim.slide_out_right);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        initDataRecycle();

        return view;
    }

    public void initDataRecycle(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(thiscontext, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.right =30;
            }
        });
        database = FirebaseDatabase.getInstance().getReference("Truyen");
        ArrayList<Truyen> dataTruyen = new ArrayList<>();
        Home home = (Home) getActivity();
        TruyenDeCuRecycleAdapter truyenAdapter = new TruyenDeCuRecycleAdapter(dataTruyen, thiscontext, new TruyenDeCuRecycleAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String idtruyen) {
                home.goToDetailTruyen(idtruyen);
            }
        });
        recyclerView.setAdapter(truyenAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataTruyen.clear();
                ArrayList<String> listTenTruyenSearch = new ArrayList<>(); //List truyện hiển thị phần search
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Truyen truyen = dataSnapshot.getValue(Truyen.class);
                    String soChuong = String.valueOf(dataSnapshot.child("sochuong").getValue());
                    truyen.setSoChuong(soChuong);
                    truyen.setKey(dataSnapshot.getKey());
                    dataTruyen.add(truyen);
                    listTenTruyenSearch.add(truyen.getTentruyen());
                }
                truyenAdapter.notifyDataSetChanged();
                ArrayAdapter searchAdapter = new ArrayAdapter(thiscontext, android.R.layout.simple_list_item_1, listTenTruyenSearch); //Adapter Search
                txtSearch.setAdapter(searchAdapter);
                txtSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtSearch.setText("");
                    }
                });
                txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String tenTr = adapterView.getItemAtPosition(i).toString();
                        String idTr = "";
                        for (Truyen truyen : dataTruyen){
                            if (truyen.getTentruyen().equals(tenTr)){
                                idTr = truyen.getKey();
                            }
                        }
                        Intent intent = new Intent(getActivity(), ChiTietTruyen.class);
                        intent.putExtra("idtruyen",idTr);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}