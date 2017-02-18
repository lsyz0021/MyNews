package com.bandeng.mynews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Lilu on 2017/2/12.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mlistFragment;
    private String[] mTitles;
    private String TAG = "tag";

    public TabPagerAdapter(FragmentManager fm, ArrayList<Fragment> mlistFragment, String[] titles) {
        super(fm);
        this.mlistFragment = mlistFragment;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mlistFragment.get(position);
    }

    @Override
    public int getCount() {
        return mlistFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.e(TAG, "getPageTitle: "+mTitles[position]);
        return mTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
