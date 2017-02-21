package com.bandeng.mynews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lilu on 2017/2/15.
 */

public abstract class BaseFragment extends Fragment {
    @BindView(R.id.img_fragment_menu)
    ImageButton imgFragmentMenu;
    @BindView(R.id.tv_fragment_title)
    TextView tvFragmentTitle;
    @BindView(R.id.img_fragment_type)
    ImageButton imgFragmentType;
    @BindView(R.id.fl_fragment_container)
    FrameLayout flFragmentContainer;

    private MainActivity mMainActivity;

    public void setTvFragmentTitle(String title) {
        this.tvFragmentTitle.setText(title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainActivity = (MainActivity) getActivity();

        initTitle(view);
        tvFragmentTitle.setText(setTitleText());
        fragmentContainerAddView(setFragmentContainerView());
    }

    /**
     * 设置菜单按钮是否显示
     *
     * @param isShow
     */
    protected void setMenuIsShow(boolean isShow) {
        imgFragmentMenu.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置类型按钮是否显示
     *
     * @param isShow
     */
    protected void setTypeIsShow(boolean isShow) {
        imgFragmentType.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    protected void fragmentContainerAddView(View view) {
        flFragmentContainer.removeAllViews();
        flFragmentContainer.addView(view);
    }

    /**
     * 初始化title
     */
    public abstract void initTitle(View view);

    /**
     * 设置标题
     */
    public abstract String setTitleText();

    /**
     * 设置fragment的内容
     */
    public abstract View setFragmentContainerView();


    @OnClick({R.id.img_fragment_menu, R.id.img_fragment_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_fragment_menu:
                // SlidingMenu 快关切换
                mMainActivity.getSlidingMenu().toggle();
                break;
            case R.id.img_fragment_type:
                break;
        }
    }

    public MainActivity getMainActivity() {
        return mMainActivity;
    }

}
