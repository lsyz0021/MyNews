package com.bandeng.mynews.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bandeng.mynews.R;
import com.bandeng.mynews.adapter.TabPagerAdapter;
import com.bandeng.mynews.fragment.GovaffairsFragment;
import com.bandeng.mynews.fragment.HomeFragment;
import com.bandeng.mynews.fragment.NewsCenterFragment;
import com.bandeng.mynews.fragment.SettingFragment;
import com.bandeng.mynews.fragment.SmartServiceFragment;
import com.bandeng.mynews.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import okhttp3.Response;

import static android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity implements OnPageChangeListener, OnCheckedChangeListener {

    private NoScrollViewPager mViewPager;
    private RadioButton rb_tab_home;
    private RadioButton rb_tab_newscenter;
    private RadioButton rb_tab_smartservice;
    private RadioButton rb_tab_govaffairs;
    private RadioButton rb_tab_setting;
    private SlidingMenu slidingMenu;
    private RadioGroup rg_radio_group;
    private TabPageIndicator tabIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        // 创建menu菜单
        slidingMenu = new SlidingMenu(this);
        // 设置侧滑方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置全屏可以划出菜单
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置侧滑菜单的宽度
        slidingMenu.setBehindWidth(300);
        // 把侧滑菜单添加到activity中
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置侧滑菜单的布局
        slidingMenu.setMenu(R.layout.main_menu);
    }


    private void initView() {

        tabIndicator = (TabPageIndicator) findViewById(R.id.tab_indicator);
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_ViewPager);
        rg_radio_group = (RadioGroup) findViewById(R.id.rg_radio_group);
        rb_tab_home = (RadioButton) findViewById(R.id.rb_tab_home);
        rb_tab_newscenter = (RadioButton) findViewById(R.id.rb_tab_newscenter);
        rb_tab_smartservice = (RadioButton) findViewById(R.id.rb_tab_smartservice);
        rb_tab_govaffairs = (RadioButton) findViewById(R.id.rb_tab_govaffairs);
        rb_tab_setting = (RadioButton) findViewById(R.id.rb_tab_setting);

        rg_radio_group.setOnCheckedChangeListener(this);
    }

    private void initData() {
        String[] tab_title = {"新闻", "推荐", "视频", "北京", "直播"};
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsCenterFragment());
        fragments.add(new SmartServiceFragment());
        fragments.add(new GovaffairsFragment());
        fragments.add(new SettingFragment());

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), fragments, tab_title);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(this);

        tabIndicator.setViewPager(mViewPager);

        OkGo.get("https://www.baidu.com/s?wd=s")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Log.e("tag", "onSuccess: = " + response.code());
                        Log.e("tag", "onSuccess: = " + response.message());
                        Log.e("tag", "onSuccess: = " + response.body());
                    }
                });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rb_tab_home.setChecked(true);
                break;
            case 1:
                rb_tab_newscenter.setChecked(true);
                break;
            case 2:
                rb_tab_smartservice.setChecked(true);
                break;
            case 3:
                rb_tab_govaffairs.setChecked(true);
                break;
            case 4:
                rb_tab_setting.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_tab_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_tab_newscenter:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_tab_smartservice:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_tab_govaffairs:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.rb_tab_setting:
                mViewPager.setCurrentItem(4);
                break;
        }
    }
}
