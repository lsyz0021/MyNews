package com.bandeng.mynews.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;
import com.bandeng.mynews.utils.MyConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Lilu on 2017/2/12.
 */

public class HomeFragment extends BaseFragment implements BaseLoadNetData {

    @Override
    public void initTitle(View view) {
        setMenuIsShow(false);
        setTypeIsShow(false);
        Log.e("tag", "home");
    }

    @Override
    public String setTitleText() {
        return getMainActivity().getResources().getString(R.string.tab_text_home);
    }

    @Override
    public View setFragmentContainerView() {
        TextView textView = new TextView(getMainActivity());
        textView.setText("哈哈");
        return textView;
    }

    @Override
    public void loadNetData() {

    }
}
