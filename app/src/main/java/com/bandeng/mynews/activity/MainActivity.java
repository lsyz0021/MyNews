package com.bandeng.mynews.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bandeng.mynews.R;
import com.bandeng.mynews.adapter.MenuAdapter;
import com.bandeng.mynews.adapter.TabPagerAdapter;
import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;
import com.bandeng.mynews.bean.NewsCenterBean;
import com.bandeng.mynews.fragment.GovaffairsFragment;
import com.bandeng.mynews.fragment.HomeFragment;
import com.bandeng.mynews.fragment.NewsCenterFragment;
import com.bandeng.mynews.fragment.SettingFragment;
import com.bandeng.mynews.fragment.SmartServiceFragment;
import com.bandeng.mynews.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

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
    //    private TabPageIndicator tabIndicator;
    private ArrayList<Fragment> fragments;

    // 新闻中心menu bean list集合
    private List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList = new ArrayList<>();
    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initSlidingMenu();
        initRecyclerView(); // 初始化侧滑菜单内容
    }

    private void initSlidingMenu() {
        // 创建menu菜单
        slidingMenu = new SlidingMenu(this);
        // 设置侧滑方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置全屏可以划出菜单
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        // 设置侧滑菜单的宽度
//        slidingMenu.setBehindWidth(Uiutil.dip2px(this, 150));
        // 设置SlidingMenu离屏幕的偏移量
        slidingMenu.setBehindOffsetRes(R.dimen.large_150);
        // 把侧滑菜单添加到activity中
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeEnabled(true);
        slidingMenu.setFadeDegree(0.5f);
        // 设置侧滑菜单的布局
        slidingMenu.setMenu(R.layout.main_menu);

    }

    /**
     * 初始化菜单RecyclerView
     */
    private void initRecyclerView() {
        RecyclerView rv_main_menu = (RecyclerView) slidingMenu.findViewById(R.id.rv_main_menu);
        // 水平方向
        rv_main_menu.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MenuAdapter(this, newsCenterMenuBeanList);
        rv_main_menu.setAdapter(menuAdapter);
    }

    private void initView() {

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
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsCenterFragment());
        fragments.add(new SmartServiceFragment());
        fragments.add(new GovaffairsFragment());
        fragments.add(new SettingFragment());

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(this);
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

    // RadioButton选择监听器
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId) {
            case R.id.rb_tab_home:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                item = 0;
                break;
            case R.id.rb_tab_newscenter:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                item = 1;
                break;
            case R.id.rb_tab_smartservice:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                item = 2;
                break;
            case R.id.rb_tab_govaffairs:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                item = 3;
                break;
            case R.id.rb_tab_setting:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                item = 4;
                break;
        }
        // 点击不同的按钮切换ViewPager
        mViewPager.setCurrentItem(item, false);

        // 切换是联网获取数据
        BaseFragment fragment = (BaseFragment) fragments.get(item);
        if (fragment instanceof BaseLoadNetData) {
            ((BaseLoadNetData) fragment).loadNetData();
        }

    }

    public SlidingMenu getSlidingMenu() {
        return slidingMenu;
    }

    // 设置新闻中心menu bean list集合
    public void setNewsCenterMenuBeanList(List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
        menuAdapter.setList(newsCenterMenuBeanList);
    }

    @Override
    public void onBackPressed() {
        if (slidingMenu.isMenuShowing()) {
            slidingMenu.toggle();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 获取viewpager当前的fragment
     *
     * @return fragment
     */
    public BaseFragment getCurrentFragment() {

        return (BaseFragment) fragments.get(mViewPager.getCurrentItem());
    }
}
