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

    private int startX;
    private int startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                tabContentPager.stopSwitch();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                int disX = moveX - startX;
                int disY = moveY - startY;
                // 水平滑动并且向右
                if (Math.abs(disX) > Math.abs(disY) && disX > 0) {
                    // 请求外层控件不拦截
                    requestDisallowInterceptTouchEvent(true);
                }

                break;
            case MotionEvent.ACTION_UP:
                tabContentPager.startSwitch();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
