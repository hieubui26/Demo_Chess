package com.mad.g1.bui_minh_hieu.demo_chess.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mad.g1.bui_minh_hieu.demo_chess.Fragment.FragmentInfo;
import com.mad.g1.bui_minh_hieu.demo_chess.Fragment.FragmentList;
import com.mad.g1.bui_minh_hieu.demo_chess.Fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentList();
//            case 1: return new FragmentInfo();
//            case 2: return new FragmentSearch();
            default: return new FragmentList();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}

