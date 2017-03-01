package com.bandeng.mynews.base;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.adapter.NewsCenterTabContentPagerAdapter;
import com.bandeng.mynews.bean.NewsCenterTabContentBean;
import com.bandeng.mynews.utils.GsonUtils;
import com.bandeng.mynews.utils.MyConstant;
import com.bandeng.mynews.utils.Uiutil;
import com.bandeng.mynews.view.SwitchImageViewPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Lilu on 2017/2/26.
 */

public class NewsCenterTabContentPager {
    @BindView(R.id.vp_news_tab_content_viewpager)
    SwitchImageViewPager vpViewpager;
    @BindView(R.id.tv_news_tab_content)
    TextView tvNewsTabContent;
    @BindView(R.id.ll_news_tab_content)
    LinearLayout llNewsTabContent;
    private Context context;
    private View view;
    private NewsCenterTabContentBean tabContentBean;
    // 自动轮播
    private Handler mhandler = new Handler();
    private ArrayList<ImageView> imageViews;

    /**
     * 开始切换
     */
    public void startSwitch() {

        mhandler.postDelayed(new MyRunnable(), 3000);
    }

    /**
     * 停止切换
     */
    public void stopSwitch() {
        mhandler.removeCallbacksAndMessages(null);
    }

    // 自动轮播
    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            int currentItem = vpViewpager.getCurrentItem();

            if (imageViews != null) {
                if (currentItem == imageViews.size() - 1) {
                    currentItem = 0;
                } else {
                    currentItem++;
                }
                vpViewpager.setCurrentItem(currentItem);
                mhandler.postDelayed(new MyRunnable(), 3000);
            }
        }
    }

    public NewsCenterTabContentPager(Context context) {
        this.context = context;
        view = initView();
    }

    private View initView() {

        View view = LayoutInflater.from(context).inflate(R.layout.newscentercontent_tab_pager, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public View getView() {
        return view;
    }

    /**
     * 网络请求数据
     *
     * @param url
     */
    public void loadData(String url) {
        OkGo.get(url).tag(this).execute(new StringCallback() {
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!TextUtils.isEmpty(s)) {
                    processData(s);
                }
            }
        });
    }

    /**
     * 处理加载完的数据
     */
    private void processData(String json) {
        tabContentBean = GsonUtils.json2Bean(json, NewsCenterTabContentBean.class);
        imageViews = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        // 给ViewPager设置数据
        final List<NewsCenterTabContentBean.DataBean.TopnewsBean> topnews = tabContentBean.data.topnews;
        for (int i = 0; i < topnews.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            NewsCenterTabContentBean.DataBean.TopnewsBean topnewsBean = topnews.get(i);
            // 由于外网服务器不可用，我们变成本地数据
            String[] split = topnewsBean.topimage.split(":8080");
            String url = MyConstant.BASEURL + split[1];
            Picasso.with(context).load(url).into(imageView);
            imageViews.add(imageView);
        }
        String title = topnews.get(0).title;
        tvNewsTabContent.setText(title);
        titles.add(title);
        // 初始化圆点
        initPoint(topnews);

        NewsCenterTabContentPagerAdapter pagerAdapter = new NewsCenterTabContentPagerAdapter(imageViews, titles);
        vpViewpager.setAdapter(pagerAdapter);
        vpViewpager.setTabContentPager(this);
        vpViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // 文字随着图片的切换而切换
                tvNewsTabContent.setText(topnews.get(position).title);

                // 小圆点随着滚动
                int childCount = llNewsTabContent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = llNewsTabContent.getChildAt(i);
                    if (position == i) {
                        childAt.setBackgroundResource(R.drawable.guide_red_point_shape);
                    } else {
                        childAt.setBackgroundResource(R.drawable.guide_gray_point_shape);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 初始化圆点
     *
     * @param topnews
     */
    private void initPoint(List<NewsCenterTabContentBean.DataBean.TopnewsBean> topnews) {
        llNewsTabContent.removeAllViews();
        for (int i = 0; i < topnews.size(); i++) {
            View view = new View(context);
            view.setBackgroundResource(R.drawable.guide_gray_point_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Uiutil.dip2px(context, 8)
                    , Uiutil.dip2px(context, 8));
            params.rightMargin = Uiutil.dip2px(context, 16);
            view.setLayoutParams(params);
            llNewsTabContent.addView(view);
        }
        llNewsTabContent.getChildAt(0).setBackgroundResource(R.drawable.guide_red_point_shape);
    }
}
