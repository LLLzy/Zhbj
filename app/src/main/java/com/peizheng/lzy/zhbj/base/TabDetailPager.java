package com.peizheng.lzy.zhbj.base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.peizheng.lzy.zhbj.base.BaseMenuDetailPager;
import com.peizheng.lzy.zhbj.domain.NewsData;

/**
 * Created by LZY on 2016/11/9.
 */

public class TabDetailPager extends BaseMenuDetailPager {

    private TextView textView;
    private NewsData.NewsMenuData.NewsTabData mTabData;

    public TabDetailPager(Activity activity, NewsData.NewsMenuData.NewsTabData newsTabData) {
        super(activity);
        mTabData = newsTabData;
    }

    @Override
    public View initViews() {
        textView = new TextView(mActivity);
        textView.setText("页签详情页");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }

    @Override
    public void initData() {
        textView.setText(mTabData.getTitle());
    }
}
