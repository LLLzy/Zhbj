package com.peizheng.lzy.zhbj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.peizheng.lzy.zhbj.fragment.ContentFragment;
import com.peizheng.lzy.zhbj.fragment.LeftMenuFragment;


public class MainActivity extends SlidingFragmentActivity {
    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private static final String FRAGMENT_CONTENT = "fragment_content";
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.sliding_left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setBehindOffset(200);

        initFragment();
    }

    void initFragment() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), FRAGMENT_LEFT_MENU);
        fragmentTransaction.replace(R.id.fl_content, new ContentFragment(), FRAGMENT_CONTENT);

        fragmentTransaction.commit();
    }

    public LeftMenuFragment getLeftMenuFragment() {
        LeftMenuFragment fragment = (LeftMenuFragment) fragmentManager.findFragmentByTag(FRAGMENT_LEFT_MENU);

        return fragment;
    }

    public ContentFragment getContentFragment() {
        ContentFragment fragment = (ContentFragment) fragmentManager.findFragmentByTag(FRAGMENT_CONTENT);

        return fragment;
    }
}
