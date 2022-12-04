package com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdoctruyen_cuoiki.DanhMucTruyen;
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
import java.util.List;


public class FavFolderFragment extends Fragment {

    private RecyclerView rcvFavFolder;
    private List<FavFolder> firstFolder;
    DatabaseReference databaseReference;
    FavFolderAdapter favFolderAdapter;
    TextView tvAdd;
    Context thiscontext;

    public FavFolderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firstFolder = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_fav_folder, container, false);
        thiscontext = getContext();
        tvAdd = view.findViewById(R.id.fav_folder_add);
        rcvFavFolder = view.findViewById(R.id.rcv_favFolder);
        favFolderAdapter = new FavFolderAdapter(getContext(), firstFolder, new FavFolderAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String idthumuc) {
                Intent intent = new Intent(getContext(), DanhMucTruyen.class);
                intent.putExtra("idthumuc",idthumuc);
                startActivity(intent);
            }
            @Override
            public void onLongClickItem(String idthumuc) {
                Xoa(idthumuc);
            }
        });
        //rcvNovel.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvFavFolder.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom =30;
            }
        });
        rcvFavFolder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFavFolder.setAdapter(favFolderAdapter);
        initData();

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(Gravity.CENTER);
            }
        });

        return view;
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
    public void openDialog(int gravity){
        Dialog dialog = new Dialog(thiscontext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_folder);
        dialog.setTitle("Tạo Folder");
        Window window = dialog.getWindow();

        if (window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams winLayoutAttributes = window.getAttributes();
        winLayoutAttributes.gravity = gravity;

        EditText ten = dialog.findViewById(R.id.txtAddFolder);
        TextView buttonXacNhan = dialog.findViewById(R.id.btXacNhan);
        TextView buttonHuy = dialog.findViewById(R.id.btHuy);

        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference("YeuThich");
                databaseReference.push().setValue(new AddFolder(ten.getText().toString(),""));
                dialog.dismiss();
            }
        });

        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void Xoa(String idcanxoa){
        AlertDialog.Builder alterDialog  = new AlertDialog.Builder(getContext());
        alterDialog.setTitle("Thông báo ");
        alterDialog.setIcon(R.mipmap.ic_launcher);
        alterDialog.setMessage("Bạn có muốn xóa truyện khỏi lịch sử không ?");
        alterDialog.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("YeuThich");
                ref.child(idcanxoa).removeValue();
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

class AddFolder{
    String truyen;
    String ten;
    public AddFolder(){}
    public AddFolder(String ten, String truyen) {
        this.ten = ten;
        this.truyen = truyen;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getTruyen() {
        return truyen;
    }
    public void setTruyen(String chuong) {
        this.truyen = chuong;
    }
}