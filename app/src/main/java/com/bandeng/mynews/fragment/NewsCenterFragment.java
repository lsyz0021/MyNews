package com.bandeng.mynews.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.adapter.NewsCenterPagerAdapter;
import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;
import com.bandeng.mynews.bean.NewsCenterBean;
import com.bandeng.mynews.utils.GsonUtils;
import com.bandeng.mynews.utils.MyConstant;
import com.bandeng.mynews.utils.Uiutil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Lilu on 2017/2/12.
 */

public class NewsCenterFragment extends BaseFragment implements BaseLoadNetData {

    private TabPageIndicator tabPageIndicator;
    private ImageButton img_newcenter_left;
    private ViewPager viewPager;
    private NewsCenterBean newsCenterBean = new NewsCenterBean();

    @Override
    public void initTitle(View view) {

        setMenuIsShow(true);
        setTypeIsShow(true);
        setTvFragmentTitle("新闻");
    }

    @Override
    public View setFragmentContainerView() {

        View view = LayoutInflater.from(getMainActivity()).inflate(R.layout.newscenter_content, (ViewGroup) getView(), false);
        tabPageIndicator = (TabPageIndicator) view.findViewById(R.id.newscenter_tabIndictor);
        img_newcenter_left = (ImageButton) view.findViewById(R.id.img_newcenter_left);
        viewPager = (ViewPager) view.findViewById(R.id.newscentercontent_viewpager);

        initViewPager();
        return view;
    }

    private void initViewPager() {
        ArrayList<View> viewPagerList = new ArrayList<>();
        ArrayList<NewsCenterBean.NewsCenterMenuBean> viewPagerListTitle = new ArrayList<>();

        for (NewsCenterBean.NewsCenterMenuBean.NewsCenterNewsTabBean tabBean
                : newsCenterBean.data.get(0).children) {
            TextView textView = new TextView(getMainActivity());
            textView.setText(tabBean.title);
            textView.setTextColor(Color.RED);
            textView.setTextSize(Uiutil.dip2px(getMainActivity(), 16));
            textView.setGravity(Gravity.CENTER);
            viewPagerList.add(textView);
        }
        viewPagerListTitle.add(newsCenterBean.data.get(0));

        NewsCenterPagerAdapter pagerAdapter = new NewsCenterPagerAdapter(viewPagerList, viewPagerListTitle);
        viewPager.setAdapter(pagerAdapter);
        tabPageIndicator.setViewPager(viewPager);
    }

    @Override
    public void loadNetData() {
        OkGo.get(MyConstant.CATEGORIES)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        System.out.println("s = " + s);
                        if (!TextUtils.isEmpty(s))
                            processData(s);
                    }
                });
    }

    /**
     * 将json转化成对应的数据模型
     */
    private void processData(String s) {

        newsCenterBean = GsonUtils.json2Bean(s, NewsCenterBean.class);
        // 将数据传递给Activity
        if (newsCenterBean != null && newsCenterBean.data != null) {
            getMainActivity().setNewsCenterMenuBeanList(newsCenterBean.data);
        }
        // 创建新闻中心的布局
        View view = setFragmentContainerView();


        fragmentContainerAddView(view);

    }
}
