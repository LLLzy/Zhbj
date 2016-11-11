package com.peizheng.lzy.zhbj.base;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.peizheng.lzy.zhbj.MainActivity;
import com.peizheng.lzy.zhbj.R;

/**
 * Created by LZY on 2016/11/6.
 */

public class BasePager {

    public Activity mActivity;
    public View mRootView;
    public TextView tvTitle;
    public FrameLayout flContent;
    public ImageButton ibMenu;

    public BasePager(Activity activity) {
        mActivity = activity;
        initView();
    }

    public void initView() {
        mRootView = View.inflate(mActivity, R.layout.base_pager, null);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        ibMenu = (ImageButton) mRootView.findViewById(R.id.ib_menu);
        flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);

        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
    }

    private void toggleSlidingMenu() {
        MainActivity mainUi = (MainActivity) mActivity;
        mainUi.getSlidingMenu().toggle();
    }

    public void initData() {

    }

    public void setSlidingMenuEnable(boolean enable) {
        MainActivity mainUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
