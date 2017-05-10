package com.boredream.boreweibo.activity.horizontalscrollview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boredream.boreweibo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 * @version 1.0
 * @date 2017/5/10
 */

public class ListViewAdapter extends BaseAdapter {
    List<String> mList;
    Context mContext;

    public ListViewAdapter(Context context) {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(String.valueOf(i));
        }
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = createViewByPosition(parent, position);
        }
        ((TextView) convertView).setText(mList.get(position));
        return convertView;
    }

    private View createViewByPosition(ViewGroup parent, int position) {

        return LayoutInflater.from(mContext).inflate(R.layout.item_in_vp, parent, false);
    }
}
