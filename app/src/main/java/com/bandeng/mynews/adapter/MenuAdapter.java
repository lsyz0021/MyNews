package com.bandeng.mynews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.activity.MainActivity;
import com.bandeng.mynews.base.BaseFragment;
import com.bandeng.mynews.bean.NewsCenterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lilu on 2017/2/21.
 */

public class MenuAdapter extends RecyclerView.Adapter {
    private List<NewsCenterBean.NewsCenterMenuBean> list = new ArrayList<>();
    private Context context;
    private int selectedPosition;

    public void setList(List<NewsCenterBean.NewsCenterMenuBean> list) {
        this.list = list;
        // 刷新数据
        notifyDataSetChanged();
    }

    public MenuAdapter(Context context, List<NewsCenterBean.NewsCenterMenuBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_menu_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final NewsCenterBean.NewsCenterMenuBean menuBean = list.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvMainMenuText.setText(menuBean.title);
        // 如果点击当前条目就变成红色
        if (selectedPosition == position) {
            viewHolder.tv_main_menu_img.setImageResource(R.drawable.menu_arr_select);
            viewHolder.tvMainMenuText.setTextColor(Color.RED);
        } else {
            viewHolder.tv_main_menu_img.setImageResource(R.drawable.menu_arr_normal);
            viewHolder.tvMainMenuText.setTextColor(Color.WHITE);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击条目，刷新数据
                if (selectedPosition != position) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                    BaseFragment currentFragment = ((MainActivity) context).getCurrentFragment();
                    currentFragment.setTvFragmentTitle(menuBean.title);
                    ((MainActivity) context).getSlidingMenu().toggle();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_main_menu_text)
        TextView tvMainMenuText;
        @BindView(R.id.tv_main_menu_img)
        ImageView tv_main_menu_img;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
