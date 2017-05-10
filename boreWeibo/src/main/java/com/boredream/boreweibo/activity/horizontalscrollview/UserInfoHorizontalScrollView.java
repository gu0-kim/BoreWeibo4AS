package com.boredream.boreweibo.activity.horizontalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.boredream.boreweibo.R;


/**
 * @author gu
 * @since 2017.05.10
 */

public class UserInfoHorizontalScrollView extends HorizontalScrollView {
    public UserInfoHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    public UserInfoHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.horizontalscroll_item, this, false);
        addView(linearLayout);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LinearLayout linearLayout = (LinearLayout) getChildAt(0);
        screenwidth = MeasureSpec.getSize(widthMeasureSpec);
        linearLayout.measure(MeasureSpec.makeMeasureSpec(3 * screenwidth, MeasureSpec.EXACTLY), heightMeasureSpec);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            linearLayout.getChildAt(i).measure(MeasureSpec.makeMeasureSpec(screenwidth, MeasureSpec.EXACTLY), heightMeasureSpec);
        }
    }

    private int screenwidth;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                smoothScrollByAcitonUp();
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void smoothScrollByAcitonUp() {
        post(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(getCurrentPagerIndex() * screenwidth, 0);
            }
        });
    }


    public int getCurrentPagerIndex() {
        int curscrollx = getScrollX();
        return (int) Math.rint(1f * curscrollx / screenwidth);
    }
}
