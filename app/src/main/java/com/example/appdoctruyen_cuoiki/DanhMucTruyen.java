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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhMucTruyen extends AppCompatActivity {

    private RecyclerView recylerViewYeuThich;
    private ArrayList<Truyen> truyenList;
    DatabaseReference databaseReference;
    TimTruyenAdapter timTruyenAdapter;
    EditText tenthumuc;

    String idthumuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_truyen);
        Intent intent = getIntent();
        idthumuc = intent.getStringExtra("idthumuc");

        ImageView imageViewback = findViewById(R.id.imageViewback);
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tenthumuc = findViewById(R.id.tenthumuc);
        recylerViewYeuThich = findViewById(R.id.recylerViewYeuThich);

        LinearLayoutManager layoutManager = new LinearLayoutManager(DanhMucTruyen.this, LinearLayoutManager.VERTICAL,false);
        recylerViewYeuThich.setLayoutManager(layoutManager);
        recylerViewYeuThich.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom =30;
            }
        });

        truyenList = new ArrayList<>();
        timTruyenAdapter = new TimTruyenAdapter(truyenList, this, new TimTruyenAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String idtruyen) {
                Intent intent = new Intent(DanhMucTruyen.this, ChiTietTruyen.class);
                intent.putExtra("idtruyen",idtruyen);
                startActivity(intent);
            }

            @Override
            public void onLongClickItem(String idtruyen) {

            }
        });
        recylerViewYeuThich.setAdapter(timTruyenAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("YeuThich");
        databaseReference.child(idthumuc).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tenthumuc.setText(snapshot.child("ten").getValue().toString());
                truyenList.clear();
                for (DataSnapshot dataSnapshot : snapshot.child("truyen").getChildren()) {
                    getFav(dataSnapshot.getValue().toString());
                    Log.d("TAG",dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DanhMucTruyen.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getFav(String idtruyen){
        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Truyen");
        databaseReference2.child(idtruyen).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    DataSnapshot dataSnapshot = task.getResult();
                    Truyen truyen = dataSnapshot.getValue(Truyen.class);
                    String soChuong = String.valueOf(dataSnapshot.child("sochuong").getValue());
                    truyen.setSoChuong(soChuong);
                    Log.d("ád", truyen.getTentruyen());
                    truyen.setKey(dataSnapshot.getKey());
                    truyenList.add(truyen);
                }
                timTruyenAdapter.notifyDataSetChanged();
            }
        });
    }
}