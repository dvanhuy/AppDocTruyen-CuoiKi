package com.example.appdoctruyen_cuoiki.LichSuTruyen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appdoctruyen_cuoiki.Fragment3;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook.favBookFragment;
import com.example.appdoctruyen_cuoiki.LichSuTruyen.history.History_bookFragment;

public class MyViewPager2Adapter extends FragmentStateAdapter {


    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MyViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MyViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new favBookFragment();
            case 1:
                return new History_bookFragment();
            default:
                return new favBookFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
