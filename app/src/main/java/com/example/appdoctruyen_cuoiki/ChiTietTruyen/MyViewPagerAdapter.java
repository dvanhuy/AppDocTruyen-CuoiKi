package com.example.appdoctruyen_cuoiki.ChiTietTruyen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment.ChapterListFragment;
import com.example.appdoctruyen_cuoiki.ChiTietTruyen.ChiTietTruyenFragment.MoTaFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MyViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MyViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MoTaFragment();
            case 1:
                return new ChapterListFragment();
            default:
                return new MoTaFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
