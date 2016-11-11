package com.peizheng.lzy.zhbj.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.peizheng.lzy.zhbj.R;
import com.peizheng.lzy.zhbj.base.BasePager;
import com.peizheng.lzy.zhbj.base.impl.GovAffairsPager;
import com.peizheng.lzy.zhbj.base.impl.HomePager;
import com.peizheng.lzy.zhbj.base.impl.NewsCenterPager;
import com.peizheng.lzy.zhbj.base.impl.SettingPager;
import com.peizheng.lzy.zhbj.base.impl.SmartServicePager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends BaseFragment {

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;

    private ArrayList<BasePager> mPagers;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void initData() {
        rgGroup.check(R.id.rb_home);

        mPagers = new ArrayList<BasePager>();

      /*  for (int i = 0; i < 5; i++) {
            BasePager pager = new BasePager(mActivity);
            mPagers.add(pager);
        }*/

        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsCenterPager(mActivity));
        mPagers.add(new SmartServicePager(mActivity));
        mPagers.add(new GovAffairsPager(mActivity));
        mPagers.add(new SettingPager(mActivity));

        vpContent.setAdapter(new ContentAdapter());

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpContent.setCurrentItem(0, false);
                        break;

                    case R.id.rb_news:
                        vpContent.setCurrentItem(1, false);
                        break;

                    case R.id.rb_smart:
                        vpContent.setCurrentItem(2, false);
                        break;

                    case R.id.rb_gov:
                        vpContent.setCurrentItem(3, false);
                        break;

                    case R.id.rb_setting:
                        vpContent.setCurrentItem(4, false);
                        break;
                }
            }
        });

        mPagers.get(0).initData();

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPagers.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ContentAdapter extends PagerAdapter {

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
            BasePager pager = mPagers.get(position);
            container.addView(mPagers.get(position).mRootView);

            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) mPagers.get(1);
    }

}
