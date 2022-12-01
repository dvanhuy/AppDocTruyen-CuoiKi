package com.example.appdoctruyen_cuoiki;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ViewFlipper viewFlipper;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context thiscontext;
    DatabaseReference database;
    RecyclerView recyclerView;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
        // Inflate the layout for this fragment
        thiscontext = getContext();
        View view = inflater.inflate(R.layout.fragment_1,container,false);
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(thiscontext,layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        database = FirebaseDatabase.getInstance().getReference("Truyen");
        ArrayList<Truyen> dataTruyen = new ArrayList<>();
        TruyenDeCuRecycleAdapter truyenAdapter = new TruyenDeCuRecycleAdapter(dataTruyen,thiscontext);
        recyclerView.setAdapter(truyenAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Truyen truyen = dataSnapshot.getValue(Truyen.class);
                    String soChuong = String.valueOf(dataSnapshot.child("sochuong").getValue());
                    truyen.setSoChuong(soChuong);
                    dataTruyen.add(truyen);
                }
                truyenAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}