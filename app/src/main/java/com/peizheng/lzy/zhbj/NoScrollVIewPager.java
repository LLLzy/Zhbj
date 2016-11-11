package com.peizheng.lzy.zhbj;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by LZY on 2016/11/7.
 */

public class NoScrollVIewPager extends ViewPager {
    public NoScrollVIewPager(Context context) {
        super(context);
    }

    public NoScrollVIewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
