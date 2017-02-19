package com.bandeng.mynews.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.base.BaseLoadNetData;


/**
 * Created by Lilu on 2017/2/12.
 */

public class NewsCenterFragment extends BaseFragment implements BaseLoadNetData {

    @Override
    public void initTitle(View view) {

        setMenuIsShow(true);
        setTypeIsShow(true);
        Log.e("tag", "News");
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

    }
}
