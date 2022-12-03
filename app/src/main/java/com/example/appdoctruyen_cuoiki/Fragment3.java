package com.example.appdoctruyen_cuoiki;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.FavFolderFragment;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.History_bookFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    private TabLayout tabLayout2;
    private ViewPager2 viewPager2_frag3;
    History_bookFragment history_bookFragment = new History_bookFragment();
    FavFolderFragment favFolderFragment = new FavFolderFragment();
    public Fragment3() {
    }

    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        tabLayout2 = view.findViewById(R.id.tabLayout_frag3);
        viewPager2_frag3 = view.findViewById(R.id.tabViewPager2);
        initTabLayoutData();
        return view;
    }

    public void initTabLayoutData(){
        viewPager2_frag3.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case 0:
                        return history_bookFragment;
                    case 1:
                        return favFolderFragment;
                    default:
                        return history_bookFragment;
                }
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });

        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2_frag3.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2_frag3.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout2.getTabAt(position).select();
            }
        });

        viewPager2_frag3.setSaveEnabled(false);
    }
}