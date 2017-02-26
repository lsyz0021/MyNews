package com.bandeng.mynews.fragment;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bandeng.mynews.R;
import com.bandeng.mynews.adapter.NewsCenterPagerAdapter;
import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;
import com.bandeng.mynews.base.NewsCenterTabContentPager;
import com.bandeng.mynews.bean.NewsCenterBean;
import com.bandeng.mynews.utils.GsonUtils;
import com.bandeng.mynews.utils.MyConstant;
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
        ImageButton img_newcenter_left = (ImageButton) view.findViewById(R.id.img_newcenter_left);
        viewPager = (ViewPager) view.findViewById(R.id.newscentercontent_viewpager);

        initViewPager();
        // 设置indicate 的标题切换到下一个
        img_newcenter_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem != newsCenterBean.data.get(0).children.size()) {
                    viewPager.setCurrentItem(++currentItem);
                }

            }
        });
        return view;
    }

    private void initViewPager() {
        ArrayList<NewsCenterTabContentPager> viewPagerList = new ArrayList<>();
        ArrayList<NewsCenterBean.NewsCenterMenuBean> viewPagerListTitle = new ArrayList<>();

        for (NewsCenterBean.NewsCenterMenuBean.NewsCenterNewsTabBean tabBean
                : newsCenterBean.data.get(0).children) {

            NewsCenterTabContentPager contentPager = new NewsCenterTabContentPager(getContext());


            viewPagerList.add(contentPager);
        }
        // 设置 Indicator的标题
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
     * 将tab对应的内容json转化成对应的数据模型
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
