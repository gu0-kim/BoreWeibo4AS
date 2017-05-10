package com.boredream.boreweibo.activity.horizontalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.boredream.boreweibo.R;

/**
 * Created by gu on 2017/5/10.
 */

public class NestedListView extends ListView {
    private int headerHeight;
    private ListViewAdapter mAdapter;

    public NestedListView(Context context) {
        super(context);
    }

    public NestedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        headerHeight = getResources().getDimensionPixelSize(R.dimen.header_layout_height);
    }

    public void setAdapter(ListAdapter adapter) {
        mAdapter = (ListViewAdapter) adapter;
        super.setAdapter(adapter);
    }

    public ListViewAdapter getSelfAdapter() {
        return mAdapter;
    }

    public int getNestedScrollY() {
        View c = getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = getFirstVisiblePosition();
        int top = c.getTop();
        if (firstVisiblePosition == 0) {
            return -top;
        }
        return headerHeight - top + (firstVisiblePosition - 1) * c.getHeight();
    }
}
