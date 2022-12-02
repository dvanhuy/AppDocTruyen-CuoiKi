package com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appdoctruyen_cuoiki.R;

public class MoTaFragment extends Fragment {

    String gioithieu;
    TextView noidungmota;

    public MoTaFragment() {
    }

    public MoTaFragment(String gioithieuu) {
        this.gioithieu = gioithieuu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mo_ta, container, false);
        noidungmota = view.findViewById(R.id.noidungmota);
        try {
            noidungmota.setText(gioithieu);
        }
        catch (Exception e)
        {
            noidungmota.setText("lỗi");
        }
        return view;
    }
}