package com.peizheng.lzy.zhbj.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by LZY on 2016/11/8.
 */

public abstract class BaseMenuDetailPager {
    public Activity mActivity;
    public View mRootView;

    public BaseMenuDetailPager(Activity activity) {
        mActivity = activity;
        mRootView = initViews();
    }

    public abstract View initViews();

    public void initData(){};
}
