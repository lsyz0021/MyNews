package com.bandeng.mynews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Lilu on 2017/2/18.
 */

public class GuidePagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> lists = new ArrayList<>();

    public GuidePagerAdapter(ArrayList<ImageView> lists) {
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = lists.get(position);
        //1、将当前视图添加到container中
        container.addView(imageView);
        //2、返回当前View
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
