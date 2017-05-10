package com.boredream.boreweibo.activity.viewpager;

import android.support.v4.view.ViewPager;

/**
 * @author Gu
 * @version 1.0
 * @since 2017/05/10
 */

public class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;

    public ViewPagerChangeListener(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            ScrollStateHelper.getInstance().setCurFragmentIndex(mViewPager.getCurrentItem());
        }
    }
}
