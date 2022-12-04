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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.FavFolder;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.FavFolderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SaveBookMark extends AppCompatActivity {

    RecyclerView recylerViewthumuc;
    private List<FavFolder> firstFolder;
    DatabaseReference databaseReference;
    FavFolderAdapter favFolderAdapter;
    String idtruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_book_mark);

        recylerViewthumuc = findViewById(R.id.recylerViewthumuc);
        Intent intent = getIntent();
        idtruyen = intent.getStringExtra("idtruyen");
        Log.d("TAG", idtruyen);

        firstFolder = new ArrayList<>();
        favFolderAdapter = new FavFolderAdapter(this, firstFolder, new FavFolderAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String idthumuc) {
                addDataToDatabase(idthumuc);
            }

            @Override
            public void onLongClickItem(String idthumuc) {

            }
        });

        recylerViewthumuc.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom =30;
            }
        });
        recylerViewthumuc.setLayoutManager(new LinearLayoutManager(this));
        recylerViewthumuc.setAdapter(favFolderAdapter);
        initData();

        ImageView imageViewback = findViewById(R.id.imageViewback);
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    public void addDataToDatabase(String idfolder){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("YeuThich");
        ref.child(idfolder).child("truyen").orderByValue().equalTo(idtruyen).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Toast.makeText(SaveBookMark.this, "Truyện đã có sẵn trong thư mục", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                    finish();
                }
                else {
                    snapshot.getRef().push().setValue(idtruyen);
                    setResult(RESULT_OK);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}