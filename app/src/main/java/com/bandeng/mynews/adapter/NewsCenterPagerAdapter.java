package com.bandeng.mynews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bandeng.mynews.bean.NewsCenterBean;

import java.util.List;

/**
 * Created by Lilu on 2017/2/26.
 */

public class NewsCenterPagerAdapter extends PagerAdapter {
    private List<View> viewPagerList;
    private List<NewsCenterBean.NewsCenterMenuBean> viewPagerListTitle;

    public NewsCenterPagerAdapter(List<View> viewPagerList
            , List<NewsCenterBean.NewsCenterMenuBean> viewPagerListTitle) {
        this.viewPagerList = viewPagerList;
        this.viewPagerListTitle = viewPagerListTitle;
    }

    @Override

    public int getCount() {
        return viewPagerList != null ? viewPagerList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewPagerList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewPagerListTitle.get(0).children.get(position).title;
    }
}
