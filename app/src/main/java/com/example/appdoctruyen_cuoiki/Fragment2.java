package com.example.appdoctruyen_cuoiki;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    private RecyclerView recyclerView;
    Context thiscontext;

    public Fragment2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();;
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
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView = view.findViewById(R.id.recylerViewKhamPha);
        initDataRecycleKhamPha();

        EditText editTextSearch = view.findViewById(R.id.editTextSearch);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchTruyen(v.getText().toString());
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public void initDataRecycleKhamPha(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view); // item position
                int spanCount = 2;
                int spacing = 30;//spacing between views in grid
                int column = position % spanCount;
                Boolean includeEdge= false;
                if (includeEdge) {
                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (position >= spanCount) {
                        outRect.top = spacing; // item top
                    }
                }
            }
        });
        ArrayList<TheLoaiTruyen> dataTheLoai = new ArrayList<>();
        dataTheLoai.add(new TheLoaiTruyen("Tiên Hiệp","tienhiep", R.drawable.khampha_ic_sword));
        dataTheLoai.add(new TheLoaiTruyen("Huyền Huyễn","huyenhuyen", R.drawable.khampha_ic_rong));
        dataTheLoai.add(new TheLoaiTruyen("Lịch Sử","lichsu", R.drawable.khampha_ic_book));
        dataTheLoai.add(new TheLoaiTruyen("Võng Du","vongdu", R.drawable.khampha_ic_game));
        dataTheLoai.add(new TheLoaiTruyen("Đô Thị","dothi", R.drawable.khampha_ic_city));
        dataTheLoai.add(new TheLoaiTruyen("Đồng Nhân","dongnhan", R.drawable.khampha_ic_dongnhan));
        dataTheLoai.add(new TheLoaiTruyen("Trinh Thám","trinhtham", R.drawable.khampha_ic_thamtu));
        dataTheLoai.add(new TheLoaiTruyen("Hệ Thống","hethong", R.drawable.khampha_ic_hethong));
        dataTheLoai.add(new TheLoaiTruyen("Linh Dị","linhdi", R.drawable.khampha_ic_linhdi));
        dataTheLoai.add(new TheLoaiTruyen("Cổ Đại","codai", R.drawable.khampha_ic_codai));
        dataTheLoai.add(new TheLoaiTruyen("Dị Giới","digioi", R.drawable.khampha_ic_digioi));

        TheLoaiTruyenRecycleAdapter theLoaiAdapter = new TheLoaiTruyenRecycleAdapter(dataTheLoai, thiscontext, new TheLoaiTruyenRecycleAdapter.IClickItemListener() {
            @Override
            public void onClickItem(String theloaitruyen) {
                searchCategory(theloaitruyen);
            }
        });
        recyclerView.setAdapter(theLoaiAdapter);
    }

    public void searchTruyen(String id){
        Intent intent = new Intent(getActivity(),TimKiemTruyen.class);
        intent.putExtra("tentimkiem",id);
        startActivity(intent);
    }

    public void searchCategory(String theloai){
        Intent intent = new Intent(getActivity(),TimKiemTruyen.class);
        intent.putExtra("theloaitimkiem",theloai);
        startActivity(intent);
    }
}