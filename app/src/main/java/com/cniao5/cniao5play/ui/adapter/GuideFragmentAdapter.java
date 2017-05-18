package com.cniao5.cniao5play.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgz_e on 2017/5/6.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    public void setFragments(List<Fragment> fragments) {
        if (fragments == null) {
            mFragments = new ArrayList<>();
        } else {
            mFragments = fragments;
        }
    }

    List<Fragment> mFragments;

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
