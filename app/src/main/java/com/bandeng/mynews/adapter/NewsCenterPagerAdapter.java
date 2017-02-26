package com.bandeng.mynews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bandeng.mynews.base.NewsCenterTabContentPager;
import com.bandeng.mynews.bean.NewsCenterBean;
import com.bandeng.mynews.utils.MyConstant;

import java.util.List;

/**
 * Created by Lilu on 2017/2/26.
 */

public class NewsCenterPagerAdapter extends PagerAdapter {
    private List<NewsCenterTabContentPager> viewPagerList;
    private List<NewsCenterBean.NewsCenterMenuBean> viewPagerListTitle;

    public NewsCenterPagerAdapter(List<NewsCenterTabContentPager> viewPagerList
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
        View view = viewPagerList.get(position).getView();
        container.addView(view);
        // 加载tab对应的content数据
        NewsCenterTabContentPager tabContentPager = viewPagerList.get(position);
        String url = MyConstant.HOST + viewPagerListTitle.get(0).children.get(position).url;
        tabContentPager.loadData(url);
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
