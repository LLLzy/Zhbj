package com.peizheng.lzy.zhbj.base.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.peizheng.lzy.zhbj.R;
import com.peizheng.lzy.zhbj.base.BaseMenuDetailPager;
import com.peizheng.lzy.zhbj.base.TabDetailPager;
import com.peizheng.lzy.zhbj.domain.NewsData;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LZY on 2016/11/8.
 */

public class NewsMenuDetailPager extends BaseMenuDetailPager {

    @BindView(R.id.vp_menu_detail)
    ViewPager vpMenuDetail;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;

    private ArrayList<TabDetailPager> mPagers;
    private List<NewsData.NewsMenuData.NewsTabData> mNewsTabData;


    public NewsMenuDetailPager(Activity activity, List<NewsData.NewsMenuData.NewsTabData> children) {
        super(activity);
        mNewsTabData = children;
    }

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.news_menu_detail, null);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void initData() {
        mPagers = new ArrayList<TabDetailPager>();

        for (int i = 0; i < mNewsTabData.size(); i++) {
            TabDetailPager tabDetailPager = new TabDetailPager(mActivity, mNewsTabData.get(i));
            mPagers.add(tabDetailPager);
        }

        vpMenuDetail.setAdapter(new MenuDetailAdapter());
        indicator.setViewPager(vpMenuDetail);
    }


    class MenuDetailAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return mNewsTabData.get(position).getTitle();
        }

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = mPagers.get(position);
            container.addView(tabDetailPager.mRootView);
            tabDetailPager.initData();

            return tabDetailPager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
