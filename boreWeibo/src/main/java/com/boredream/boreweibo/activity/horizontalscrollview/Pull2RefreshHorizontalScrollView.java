package com.boredream.boreweibo.activity.horizontalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.boredream.boreweibo.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by gu on 2017/5/10.
 */

public class Pull2RefreshHorizontalScrollView extends PullToRefreshBase<UserInfoHorizontalScrollView> {
    private ListView lv1, lv2, lv3;
    private ImageView imgBg;

    public Pull2RefreshHorizontalScrollView(Context context) {
        super(context);
    }

    public Pull2RefreshHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);
        lv1.setOnScrollListener(new onScrollListener());
        lv2.setOnScrollListener(new onScrollListener());
        lv3.setOnScrollListener(new onScrollListener());
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected UserInfoHorizontalScrollView createRefreshableView(Context context, AttributeSet attrs) {
        return new UserInfoHorizontalScrollView(context, attrs);
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        int index = getRefreshableView().getCurrentPagerIndex();
        Log.e("tag", "isReadyForPullStart: index =" + index);
        ListView lv = getCurrentListView(index);
        return isTop(lv);
    }

    public void setImgBg(ImageView imgBg) {
        this.imgBg = imgBg;
    }

    private ListView getCurrentListView(int pageIndex) {
        return pageIndex == 0 ? lv1 : (pageIndex == 1 ? lv2 : lv3);
    }

    private boolean isTop(ListView lv) {
        int firstVisibleIndex = lv.getFirstVisiblePosition();
        if (firstVisibleIndex == 0) {
            View view = lv.getChildAt(0);
            return view.getTop() == 0;
        }
        return false;
    }

    private class onScrollListener implements AbsListView.OnScrollListener {
        private int mLastScrollY;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (view != getScrollingListView()) {
                Log.e("tag", "onScroll: 从动Listview,不移动imgBg，index=" + getRefreshableView().getCurrentPagerIndex());
                return;
            }
            int curScrollY = ((NestedListView) view).getNestedScrollY();
            if (isScrollDown(curScrollY)) {
                int firstVisiblePos = view.getFirstVisiblePosition();
                if (firstVisiblePos == 0) {
                    View firstVisibleView = view.getChildAt(firstVisibleItem);
                    int firstViewBottom = firstVisibleView.getBottom();
                    if (firstViewBottom <= imgBg.getBottom()) {
                        /*
                        向下滚动，Listview底部高于imgbg，imgbg需要同步滚动
                         */
                        imgBg.layout(0, firstVisibleView.getTop(), view.getWidth(), firstVisibleView.getBottom());
                        brotherScroll(view, firstVisibleView.getTop());
                    }
                }
            }
        }

        private boolean isScrollUp(int curScrollY) {
            return mLastScrollY > curScrollY;
        }

        private boolean isScrollDown(int curScrollY) {
            return mLastScrollY < curScrollY;
        }

        private ListView getScrollingListView() {
            int index = getRefreshableView().getCurrentPagerIndex();
            return index == 0 ? lv1 : (index == 1 ? lv2 : lv3);
        }

        private void brotherScroll(View self, int scrollY) {
            notEqualScroll(lv1, self, scrollY);
            notEqualScroll(lv2, self, scrollY);
            notEqualScroll(lv3, self, scrollY);
        }

        private void notEqualScroll(View brother, View self, int scrollY) {
            if (brother != self) {
                brother.scrollTo(0, -scrollY);
//                ((NestedListView) brother).getSelfAdapter().notifyDataSetChanged();
            }
        }
    }

}
