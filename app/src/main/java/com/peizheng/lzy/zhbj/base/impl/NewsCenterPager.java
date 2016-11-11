package com.peizheng.lzy.zhbj.base.impl;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.peizheng.lzy.zhbj.MainActivity;
import com.peizheng.lzy.zhbj.base.BaseMenuDetailPager;
import com.peizheng.lzy.zhbj.base.BasePager;
import com.peizheng.lzy.zhbj.base.menudetail.InteractMenuDetailPager;
import com.peizheng.lzy.zhbj.base.menudetail.NewsMenuDetailPager;
import com.peizheng.lzy.zhbj.base.menudetail.PhotoMenuDetailPager;
import com.peizheng.lzy.zhbj.base.menudetail.TopicMenuDetailPager;
import com.peizheng.lzy.zhbj.domain.NewsData;
import com.peizheng.lzy.zhbj.global.GlobalConstants;
import com.peizheng.lzy.zhbj.fragment.LeftMenuFragment;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LZY on 2016/11/6.
 */

public class NewsCenterPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> menuDetailPagers;
    private NewsData mNewsData;

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("新闻");
        setSlidingMenuEnable(true);

        getDataFromServer();


    }

    private void getDataFromServer() {
        OkGo.get(GlobalConstants.CATEGORIES_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Toast.makeText(mActivity, "成功", Toast.LENGTH_SHORT).show();
                parseData(s);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Toast.makeText(mActivity, "失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private void parseData(String s) {
        Gson gson = new Gson();

        mNewsData = gson.fromJson(s, NewsData.class);
        Log.e("json", mNewsData.toString());

        MainActivity mainUi = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();

        leftMenuFragment.setMenuData(mNewsData);

        menuDetailPagers = new ArrayList<BaseMenuDetailPager>();
        menuDetailPagers.add(new NewsMenuDetailPager(mActivity,
                mNewsData.getData().get(0).getChildren()));
        menuDetailPagers.add(new TopicMenuDetailPager(mActivity));
        menuDetailPagers.add(new PhotoMenuDetailPager(mActivity));
        menuDetailPagers.add(new InteractMenuDetailPager(mActivity));

        setCurrentMenuDetailPager(0);
    }

    public void setCurrentMenuDetailPager(int position) {
        BaseMenuDetailPager pager = menuDetailPagers.get(position);
        flContent.removeAllViews();
        flContent.addView(pager.mRootView);

        tvTitle.setText(mNewsData.getData().get(position).getTitle());

        pager.initData();
    }
}
