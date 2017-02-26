package com.bandeng.mynews.fragment;

import android.view.View;
import android.widget.TextView;

import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;
import com.bandeng.mynews.bean.NewsCenterBean;
import com.bandeng.mynews.utils.GsonUtils;
import com.bandeng.mynews.utils.MyConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Lilu on 2017/2/12.
 */

public class NewsCenterFragment extends BaseFragment implements BaseLoadNetData {

    @Override
    public void initTitle(View view) {

        setMenuIsShow(true);
        setTypeIsShow(true);
        setTvFragmentTitle("新闻中心");
    }

    @Override
    public View setFragmentContainerView() {
        TextView textView = new TextView(getMainActivity());
        textView.setText("哈哈");
        return textView;
    }

    @Override
    public void loadNetData() {
        OkGo.get(MyConstant.CATEGORIES)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        NewsCenterBean newsCenterBean = GsonUtils.json2Bean(s, NewsCenterBean.class);

                        if (newsCenterBean != null && newsCenterBean.data != null)
                            getMainActivity().setNewsCenterMenuBeanList(newsCenterBean.data);
                    }
                });
    }
}
