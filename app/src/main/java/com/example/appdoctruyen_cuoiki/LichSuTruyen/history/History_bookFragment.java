package com.example.appdoctruyen_cuoiki.LichSuTruyen.history;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyen;
import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment.ChapterListFragment;
import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment.MoTaFragment;
import com.example.appdoctruyen_cuoiki.Fragment3;
import com.example.appdoctruyen_cuoiki.R;
import com.example.appdoctruyen_cuoiki.TimKiemTruyen;
import com.example.appdoctruyen_cuoiki.TimTruyenAdapter;
import com.example.appdoctruyen_cuoiki.Truyen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class History_bookFragment extends Fragment {

    private RecyclerView rcvHis;
    private ArrayList<Truyen> truyenList;
    DatabaseReference databaseReference;
    TimTruyenAdapter timTruyenAdapter;

    public History_bookFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_book, container, false);
        truyenList = new ArrayList<>();
        rcvHis = (RecyclerView) view.findViewById(R.id.rcv_historyBook);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        rcvHis.setLayoutManager(layoutManager);
        rcvHis.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom =30;
            }
        });

        timTruyenAdapter = new TimTruyenAdapter(truyenList, getContext(), new TimTruyenAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String idtruyen) {
                Intent intent = new Intent(getActivity(), ChiTietTruyen.class);
                intent.putExtra("idtruyen",idtruyen);
                startActivity(intent);
            }

            @Override
            public void onLongClickItem(String idtruyen) {
                Xoa(idtruyen);
            }
        });
        rcvHis.setAdapter(timTruyenAdapter);
        truyenList.clear();

        initHistory();
        return view;
    }

    public void initHistory(){
        databaseReference = FirebaseDatabase.getInstance().getReference("lichsu");
        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                truyenList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    getHistory(dataSnapshot.getValue().toString());
                }
            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                truyenList.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    getHistory(dataSnapshot.getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void getHistory(String idtruyen){
        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Truyen");
        databaseReference2.child(idtruyen).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    DataSnapshot dataSnapshot = task.getResult();
                    Truyen truyen = dataSnapshot.getValue(Truyen.class);
                    String soChuong = String.valueOf(dataSnapshot.child("sochuong").getValue());
                    truyen.setSoChuong(soChuong);
                    truyen.setKey(dataSnapshot.getKey());
                    truyenList.add(truyen);
                }
                timTruyenAdapter.notifyDataSetChanged();
            }
        });
    }

    private void Xoa(String idcanxoa){
        AlertDialog.Builder alterDialog  = new AlertDialog.Builder(getContext());
        alterDialog.setTitle("Thông báo ");
        alterDialog.setIcon(R.mipmap.ic_launcher);
        alterDialog.setMessage("Bạn có muốn xóa truyện khỏi lịch sử không ?");
        alterDialog.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("lichsu");
                Query query = ref.orderByValue().equalTo(idcanxoa);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            dataSnapshot.getRef().removeValue();
                        }
                        truyenList.clear();
                        initHistory();
                        timTruyenAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        alterDialog.setNegativeButton("Không", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alterDialog.show();
    }

}