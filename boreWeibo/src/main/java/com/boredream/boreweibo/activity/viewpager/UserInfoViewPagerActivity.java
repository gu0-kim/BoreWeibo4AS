package com.boredream.boreweibo.activity.viewpager;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.boredream.boreweibo.R;
import com.handmark.pulltorefresh.library.PullToRefreshViewPager;

public class UserInfoViewPagerActivity extends AppCompatActivity implements ItemPagerFragment.OnFragmentInteractionListener {


    PullToRefreshViewPager mPullToRefreshViewPager;
    FragmentPagerAdapter mAdapter;
    ImageView bgImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_view_pager);
        bgImg = (ImageView) findViewById(R.id.bgImg);
        ScrollStateHelper.getInstance().setBgImg(bgImg);
        mPullToRefreshViewPager = (PullToRefreshViewPager) findViewById(R.id.vp);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = mPullToRefreshViewPager.getRefreshableView();
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPagerChangeListener(viewPager));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.clearMap();
    }
}
