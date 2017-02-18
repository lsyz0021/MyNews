package com.bandeng.mynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.adapter.GuidePagerAdapter;
import com.bandeng.mynews.utils.MyConstant;
import com.bandeng.mynews.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.btn_guide_open)
    Button btnGuideOpen;


    @BindView(R.id.vp_guide_viewpager)
    ViewPager mGuide_ViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        // 如果不是第一次进入就直接进入splash界面，只有在banner的最后一页点击进入才将Constant.ISFIRSTOPEN的值为true
        if (!SPUtils.getBoolean(this, MyConstant.ISFIRSTOPEN, true)) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // ViewPager的初始化
        initViewPager();
    }

    private void initViewPager() {
        int[] imgID = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        final ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int id : imgID) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(id);
            imageViews.add(imageView);
        }

        GuidePagerAdapter pagerAdapter = new GuidePagerAdapter(imageViews);
        mGuide_ViewPager.setAdapter(pagerAdapter);

        // 设置监听
        mGuide_ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                btnGuideOpen.setVisibility(position == imageViews.size() - 1 ? View.VISIBLE : View.GONE);
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
