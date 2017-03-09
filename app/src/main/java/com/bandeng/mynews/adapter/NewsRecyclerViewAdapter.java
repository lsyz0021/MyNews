package com.bandeng.mynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandeng.mynews.R;
import com.bandeng.mynews.bean.NewsCenterTabContentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lilu on 2017/3/9.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<NewsCenterTabContentBean.DataBean.NewsBean> newsBeanList;

    public NewsRecyclerViewAdapter(Context context, List<NewsCenterTabContentBean.DataBean.NewsBean> newsBeanList) {
        this.context = context;
        this.newsBeanList = newsBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsCenterTabContentBean.DataBean.NewsBean newsBean = newsBeanList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        Picasso.with(context).load(newsBean.listimage).into(viewHolder.ivIcon);
        viewHolder.tvTitle.setText(newsBean.title);
        viewHolder.tvTime.setText(newsBean.pubdate);
    }

    @Override
    public int getItemCount() {
        return newsBeanList != null ? newsBeanList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
