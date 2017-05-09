package com.boredream.boreweibo.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boredream.boreweibo.BaseActivity;
import com.boredream.boreweibo.R;

import java.util.ArrayList;
import java.util.List;

public class UserInfoPullToScrollviewActivity extends BaseActivity {

    ViewPager vp;

    List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_pull_to_scrollview);
        vp = (ViewPager) findViewById(R.id.vp);
        mList = new ArrayList<>();
        mList.add("1111");
        mList.add("2222");
        mList.add("3333");
        mList.add("4444");
        vp.setAdapter(new PAdapter());
    }

    class PAdapter extends PagerAdapter {

        public PAdapter() {
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(UserInfoPullToScrollviewActivity.this).inflate(R.layout.vp_item_simple, container, false);
            TextView textView = (TextView) view.findViewById(R.id.tv);
            textView.setText(mList.get(position) + " ");
            Log.w("tag", "instantiateItem");
            container.addView(view);
            return view;
        }

    }
}
