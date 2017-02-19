package com.bandeng.mynews.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bandeng.mynews.R;
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
    }

    @Override
    public String setTitleText() {
        return getMainActivity().getResources().getString(R.string.tab_text_newscenter);
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

                        Log.e("tag", "新闻中心 newsCenterBean = " + newsCenterBean);
                        getMainActivity().setNewsCenterMenuBeanList(newsCenterBean.data);
                    }
                });
    }
}
