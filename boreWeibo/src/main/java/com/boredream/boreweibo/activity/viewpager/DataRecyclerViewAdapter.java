package com.boredream.boreweibo.activity.viewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredream.boreweibo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2017/5/10
 */

public class DataRecyclerViewAdapter extends RecyclerView.Adapter {
    List<String> mList;
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private Context mContext;

    public DataRecyclerViewAdapter(Context context) {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(String.valueOf(i));
        }
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType == HEADER ? new Holder(LayoutInflater.from(mContext).inflate(R.layout.top_header_in_vp, parent, false)) : new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_in_vp, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            ((TextView) holder.itemView).setText(mList.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    private class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
