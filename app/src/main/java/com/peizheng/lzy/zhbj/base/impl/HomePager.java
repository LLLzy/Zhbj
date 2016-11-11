package com.peizheng.lzy.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.peizheng.lzy.zhbj.base.BasePager;

/**
 * Created by LZY on 2016/11/6.
 */

public class HomePager extends BasePager {

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("智慧北京");
        ibMenu.setVisibility(View.GONE);
        setSlidingMenuEnable(false);

        TextView textView = new TextView(mActivity);
        textView.setText("首页");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);

        flContent.addView(textView);
    }

}
