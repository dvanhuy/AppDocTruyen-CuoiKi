package com.example.appdoctruyen_cuoiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.History_bookFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class TimKiemTruyen extends AppCompatActivity {
    String tentruyen;
    DatabaseReference databaseReference;
    ArrayList<Truyen> truyenArrayList;
    RecyclerView recylerViewTimkiem;
    TimTruyenAdapter timTruyenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_truyen);

        Intent intent = getIntent();
        tentruyen = intent.getStringExtra("tentimkiem");
        truyenArrayList = new ArrayList<>();
        recylerViewTimkiem = findViewById(R.id.recylerViewTimkiem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recylerViewTimkiem.setLayoutManager(layoutManager);
        recylerViewTimkiem.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom =30;
            }
        });
        timTruyenAdapter = new TimTruyenAdapter(truyenArrayList, this, new TimTruyenAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String idtruyen) {
                Log.d("TAG", "adasd");
            }
        });
        recylerViewTimkiem.setAdapter(timTruyenAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Truyen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                truyenArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String tentruyendow = String.valueOf(dataSnapshot.child("tentruyen").getValue());
                    Boolean resutl = tentruyendow.toLowerCase().contains(tentruyen.toLowerCase());
                    if (resutl){
                        Truyen truyen = dataSnapshot.getValue(Truyen.class);
                        String soChuong = String.valueOf(dataSnapshot.child("sochuong").getValue());
                        Log.d("TAG",truyen.getTentruyen() );
                        truyen.setSoChuong(soChuong);
                        truyen.setKey(dataSnapshot.getKey());
                        truyenArrayList.add(truyen);
                    }
                }
                timTruyenAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}