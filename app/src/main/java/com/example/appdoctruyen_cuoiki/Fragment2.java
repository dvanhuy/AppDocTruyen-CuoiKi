package com.example.appdoctruyen_cuoiki;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    Context thiscontext;

    public Fragment2() {
        // Required empty public constructor
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
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView = view.findViewById(R.id.recylerViewKhamPha);
        initDataRecycleKhamPha();
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
        dataTheLoai.add(new TheLoaiTruyen("Tiên Hiệp", R.drawable.khampha_ic_sword));
        dataTheLoai.add(new TheLoaiTruyen("Huyền Huyễn", R.drawable.khampha_ic_rong));
        dataTheLoai.add(new TheLoaiTruyen("Lịch Sử", R.drawable.khampha_ic_book));
        dataTheLoai.add(new TheLoaiTruyen("Võng Du", R.drawable.khampha_ic_game));
        dataTheLoai.add(new TheLoaiTruyen("Đô Thị", R.drawable.khampha_ic_city));
        dataTheLoai.add(new TheLoaiTruyen("Đồng Nhân", R.drawable.khampha_ic_dongnhan));
        dataTheLoai.add(new TheLoaiTruyen("Trinh Thám", R.drawable.khampha_ic_thamtu));
        dataTheLoai.add(new TheLoaiTruyen("Hệ Thống", R.drawable.khampha_ic_hethong));
        dataTheLoai.add(new TheLoaiTruyen("Linh Dị", R.drawable.khampha_ic_linhdi));
        dataTheLoai.add(new TheLoaiTruyen("Cổ Đại", R.drawable.khampha_ic_codai));
        dataTheLoai.add(new TheLoaiTruyen("Dị Giới", R.drawable.khampha_ic_digioi));

        TheLoaiTruyenRecycleAdapter theLoaiAdapter = new TheLoaiTruyenRecycleAdapter(dataTheLoai,thiscontext);
        recyclerView.setAdapter(theLoaiAdapter);
    }
}