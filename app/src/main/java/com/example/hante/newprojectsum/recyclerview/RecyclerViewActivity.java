package com.example.hante.newprojectsum.recyclerview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.Recycler_View)
    android.support.v7.widget.RecyclerView RecyclerView;
    @Bind(R.id.SwipeRefresh_Layout)
    android.support.v4.widget.SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerView_toolBar)
    TitleBar mRecyclerViewToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        mRecyclerViewToolBar.setMyCenterTitle("RecyclerView");
        setSupportActionBar(mRecyclerViewToolBar);
        getSupportActionBar();
        if(getSupportActionBar() == null){
            return;
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerViewToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(this);
        // 下拉刷新颜色
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.header_green_bar_color),
                getResources().getColor(R.color.header_red_bar_color),
                getResources().getColor(R.color.blue_item_pressed),
                getResources().getColor(R.color.account_yellow_icon_color));
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources()
                .getColor(R.color.initial_wizard_countries_light_text));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        //如果可以确定每个Item的高度固定，选用这个属性可以提高性能
        RecyclerView.setHasFixedSize(true);
        //使用默认的LayoutManager
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    /**
     * 刷新
     */
    @Override
    public void onRefresh() {

    }
}
