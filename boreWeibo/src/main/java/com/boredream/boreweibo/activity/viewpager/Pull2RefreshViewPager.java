package com.boredream.boreweibo.activity.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshViewPager;

/**
 * @author developerGu
 * @version 1.0
 * @date 2017/5/10
 */

public class Pull2RefreshViewPager extends PullToRefreshViewPager {
    public Pull2RefreshViewPager(Context context) {
        super(context);
    }

    public Pull2RefreshViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean isReadyForPullStart() {
        return isTop();
    }

    private ListView getListView() {
        ViewPager viewPager = getRefreshableView();
        ItemPagerFragment fragment = (ItemPagerFragment) ((FragmentPagerAdapter) viewPager.getAdapter()).getItem(viewPager.getCurrentItem());
        return fragment.mListView;
    }

    private boolean isTop() {
        return getListView().getFirstVisiblePosition() == 0 && getListView().getChildAt(0).getTop() >= 0;
    }
}
