package com.bandeng.mynews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bandeng.mynews.base.NewsCenterTabContentPager;

/**
 * Created by Lilu on 2017/3/1.
 */

public class SwitchImageViewPager extends ViewPager {
    private NewsCenterTabContentPager tabContentPager;

    public void setTabContentPager(NewsCenterTabContentPager tabContentPager) {
        this.tabContentPager = tabContentPager;
    }

    public SwitchImageViewPager(Context context) {
        super(context);
    }

    public SwitchImageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                tabContentPager.stopSwitch();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                tabContentPager.startSwitch();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
