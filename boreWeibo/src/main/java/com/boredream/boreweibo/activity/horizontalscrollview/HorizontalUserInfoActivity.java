package com.boredream.boreweibo.activity.horizontalscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ListView;

import com.boredream.boreweibo.R;

public class HorizontalUserInfoActivity extends AppCompatActivity {

    ListView lv1, lv2, lv3;
    Pull2RefreshHorizontalScrollView ptr_hsv;
    ImageView bgImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_user_info);
        ptr_hsv = (Pull2RefreshHorizontalScrollView) findViewById(R.id.ptr_hsv);
        bgImg = (ImageView) findViewById(R.id.bgImg);
        ptr_hsv.setImgBg(bgImg);
        lv1 = initListView(R.id.lv1);
        lv2 = initListView(R.id.lv2);
        lv3 = initListView(R.id.lv3);
    }

    private ListView initListView(int id) {
        ListView lv = (ListView) findViewById(id);
        lv.setAdapter(new ListViewAdapter(this));
        lv.addHeaderView(LayoutInflater.from(this).inflate(R.layout.top_header_in_vp, lv, false));
        return lv;
    }
}
