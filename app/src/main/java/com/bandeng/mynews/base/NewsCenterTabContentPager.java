package com.bandeng.mynews.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.bean.NewsCenterTabContentBean;
import com.bandeng.mynews.utils.GsonUtils;
import com.bandeng.mynews.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Lilu on 2017/2/26.
 */

public class NewsCenterTabContentPager {
    @BindView(R.id.vp_news_tab_content_viewpager)
    ViewPager vpViewpager;
    @BindView(R.id.tv_news_tab_content)
    TextView tvNewsTabContent;
    @BindView(R.id.ll_news_tab_content)
    LinearLayout llNewsTabContent;
    private Context context;
    private View view;
    private NewsCenterTabContentBean tabContentBean;

    public NewsCenterTabContentPager(Context context) {
        this.context = context;
        view = initView();
    }

    private View initView() {

        View view = LayoutInflater.from(context).inflate(R.layout.newscentercontent_tab_pager, null);
        ButterKnife.bind(view);
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
        LogUtils.e("tag", tabContentBean.toString());
    }
}
