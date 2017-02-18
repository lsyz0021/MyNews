package com.bandeng.mynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bandeng.mynews.R;
import com.bandeng.mynews.adapter.GuidePagerAdapter;
import com.bandeng.mynews.utils.MyConstant;
import com.bandeng.mynews.utils.SPUtils;
import com.bandeng.mynews.utils.Uiutil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.btn_guide_open)
    Button btnGuideOpen;
    @BindView(R.id.vp_guide_viewpager)
    ViewPager mGuide_ViewPager;

    int[] imgID = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    @BindView(R.id.ll_container_gray_point)
    LinearLayout mContainerGrayPoint;
    @BindView(R.id.img_guide_red_point)
    View imgGuideRedPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        // 如果不是第一次进入就直接进入splash界面，只有在banner的最后一页点击进入才将Constant.ISFIRSTOPEN的值为false
        if (!SPUtils.getBoolean(this, MyConstant.ISFIRSTOPEN, true)) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
        // 创建小红点
        createGrayPoint();

    }

    /**
     * 创建小灰色点
     */
    private void createGrayPoint() {
        for (int id : imgID) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.guide_gray_point_shape);
            // 直径为10dp是圆
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Uiutil.dip2px(this, 10), Uiutil.dip2px(this, 10));
            // 圆点的距离为20dp
            layoutParams.rightMargin = Uiutil.dip2px(this, 20);
            mContainerGrayPoint.addView(view, layoutParams);
        }
        // 通过视图树监听获取 两个圆点间的width值
        imgGuideRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = mContainerGrayPoint.getChildAt(1).getLeft() - mContainerGrayPoint.getChildAt(0).getLeft();
                imgGuideRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // ViewPager的初始化
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        final ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int id : imgID) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(id);
            imageViews.add(imageView);
        }

        final GuidePagerAdapter pagerAdapter = new GuidePagerAdapter(imageViews);
        mGuide_ViewPager.setAdapter(pagerAdapter);

        // 设置监听
        mGuide_ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int width = 0;

            //position : 条目的位置 positionOffset : 移动的偏移的百分比 positionOffsetPixels : 移动的偏移的像素
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 获取两个圆点间的距离
                if (width == 0) {
                    width = (mContainerGrayPoint.getChildAt(1).getLeft() - mContainerGrayPoint.getChildAt(0).getLeft());
                }
                // 红色圆点的移动方法1（设置红色圆点移动的参数）
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imgGuideRedPoint.getLayoutParams();
                layoutParams.leftMargin = (int) (width * position + width * positionOffset);
                imgGuideRedPoint.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                // 判断按钮是否显示
                btnGuideOpen.setVisibility(position == imageViews.size() - 1 ? View.VISIBLE : View.GONE);
                // 红色圆点的移动方法2（ 选中后灰色圆点变成红色）
                for (int i = 0; i <= mContainerGrayPoint.getChildCount() - 1; i++)
                    mContainerGrayPoint.getChildAt(i).setBackgroundResource(position == i ?
                            R.drawable.guide_red_point_shape : R.drawable.guide_gray_point_shape);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @OnClick(R.id.btn_guide_open)
    public void onClick() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
        // 记录已经按过按钮
        SPUtils.putBoolean(this, MyConstant.ISFIRSTOPEN, false);
    }

    @Override
    public void onBackPressed() {
    }
}
