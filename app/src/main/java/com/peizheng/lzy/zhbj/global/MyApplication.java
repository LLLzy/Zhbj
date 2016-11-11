package com.peizheng.lzy.zhbj.global;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by LZY on 2016/11/7.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        OkGo.init(this);
    }
}
