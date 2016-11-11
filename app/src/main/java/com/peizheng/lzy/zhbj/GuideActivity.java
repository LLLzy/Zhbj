package com.peizheng.lzy.zhbj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.peizheng.lzy.zhbj.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.btn_start)
    Button btnStart;

    private static final int[] mImageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2,
            R.drawable.guide_3};
    @BindView(R.id.ll_point_group)
    LinearLayout llPointGroup;
    @BindView(R.id.iv_red_point)
    ImageView ivRedPoint;
    private List<ImageView> mImageViews;
    private int mPointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initData();
        initUI();
    }

    private void initUI() {
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.addOnPageChangeListener(new GuidePageListener());
        btnStart.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        mImageViews = new ArrayList<ImageView>();

        for (int i = 0; i < mImageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mImageIds[i]);
            mImageViews.add(imageView);
        }

        for (int i = 0; i < mImageIds.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);

            if (i > 0) {
                layoutParams.leftMargin = 10;
            }
            point.setLayoutParams(layoutParams);
            llPointGroup.addView(point);
        }

        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPointWidth = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
            }
        });
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViews.get(position));

            return mImageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    class GuidePageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float length = positionOffset * mPointWidth + position * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
            params.leftMargin = (int) length;
            ivRedPoint.setLayoutParams(params);

        }

        @Override
        public void onPageSelected(int position) {
            if (position == mImageViews.size() - 1) {
                btnStart.setVisibility(View.VISIBLE);
            } else {
                btnStart.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    @OnClick(R.id.btn_start)
    public void onClick() {
        startActivity(new Intent(this, MainActivity.class));
        PrefUtils.putBoolean(this, "is_user_guide_showed", true);

        finish();
    }
}
