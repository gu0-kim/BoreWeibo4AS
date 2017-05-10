package com.boredream.boreweibo.activity.viewpager;

import android.widget.ImageView;

/**
 * @author Gu
 * @version 1.0
 * @since 2017/5/10
 */

public class ScrollStateHelper {


    private int curFragmentIndex;
    private ImageView bgImg;

    private ScrollStateHelper() {
    }

    private static ScrollStateHelper instance;

    public static ScrollStateHelper getInstance() {
        if (instance == null) {
            instance = new ScrollStateHelper();
        }
        return instance;
    }

    public int getCurFragmentIndex() {
        return curFragmentIndex;
    }

    public void setCurFragmentIndex(int curFragmentIndex) {
        this.curFragmentIndex = curFragmentIndex;
    }

    public void setBgImg(ImageView img) {
        this.bgImg = img;
    }

    public ImageView getBgImg() {
        return bgImg;
    }
}
