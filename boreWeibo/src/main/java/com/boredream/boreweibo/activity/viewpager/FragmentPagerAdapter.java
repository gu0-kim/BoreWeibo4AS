package com.boredream.boreweibo.activity.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2017/5/10
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    Map<Integer, Fragment> map;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        map = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = map.get(position);
        if (fragment == null) {
            fragment = ItemPagerFragment.newInstance(position, null);
            map.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void clearMap() {
        if (map != null && !map.isEmpty())
            map.clear();
    }
}
