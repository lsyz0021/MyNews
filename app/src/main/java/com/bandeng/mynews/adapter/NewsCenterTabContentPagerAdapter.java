package com.bandeng.mynews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Lilu on 2017/2/26.
 */

public class NewsCenterTabContentPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public NewsCenterTabContentPagerAdapter(ArrayList<ImageView> imageViews, ArrayList<String> titles) {
        this.imageViews = imageViews;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
