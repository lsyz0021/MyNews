package com.bandeng.mynews.fragment;

import android.view.View;
import android.widget.TextView;

import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;


/**
 * Created by Lilu on 2017/2/12.
 */

public class HomeFragment extends BaseFragment implements BaseLoadNetData {

    @Override
    public void setContextView(View view) {

        ((TextView) view).setText("首页");

//        ImageView imageView = new ImageView(getActivity());
//        String url ="http://p.jianke.net/article/201508/20150811161504567.jpg";
//        Picasso.with(getActivity()).load(url).into(imageView);
    }

    @Override
    public void loadNetData(String url) {

    }
}
