package com.peizheng.lzy.zhbj.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peizheng.lzy.zhbj.MainActivity;
import com.peizheng.lzy.zhbj.R;
import com.peizheng.lzy.zhbj.base.impl.NewsCenterPager;
import com.peizheng.lzy.zhbj.domain.NewsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftMenuFragment extends BaseFragment {


    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    private List<NewsData.NewsMenuData> mMenuList;
    private int mCurrentPos;
    private MenuAdapter mMenuAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    public void setMenuData(NewsData data) {
        mMenuList = data.getData();
        rvLeft.setLayoutManager(new LinearLayoutManager(mActivity));

        mMenuAdapter = new MenuAdapter();
        rvLeft.setAdapter(mMenuAdapter);

    }

    class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_recycler_menu,
                    parent, false));

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.tvTitle.setText(mMenuList.get(position).getTitle());
            if (mCurrentPos == position) {
                holder.tvTitle.setEnabled(true);
            } else {
                holder.tvTitle.setEnabled(false);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentPos = position;
                    mMenuAdapter.notifyDataSetChanged();

                    setCurrentMenuDetailPager(position);

                    toggleSlidingMenu();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMenuList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_title)
            TextView tvTitle;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    private void toggleSlidingMenu() {
        MainActivity mainUi = (MainActivity) mActivity;
        mainUi.getSlidingMenu().toggle();
    }

    private void setCurrentMenuDetailPager(int position) {
        MainActivity mainUi = (MainActivity) mActivity;
        ContentFragment contentFragment = mainUi.getContentFragment();
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
        newsCenterPager.setCurrentMenuDetailPager(position);
    }


}
