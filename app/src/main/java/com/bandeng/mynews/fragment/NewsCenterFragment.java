package com.bandeng.mynews.fragment;

import android.view.View;
import android.widget.TextView;

import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;


/**
 * Created by Lilu on 2017/2/12.
 */

public class NewsCenterFragment extends BaseFragment implements BaseLoadNetData {

    @Override
    public void setContextView(View view) {

        ((TextView) view).setText("新闻中心");

    }

    @Override
    public void loadNetData(String url) {

    }
}
