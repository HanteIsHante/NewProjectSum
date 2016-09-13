package com.example.hante.newprojectsum.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends BaseActivity {

    @Bind(R.id.Recycler_View)
    android.support.v7.widget.RecyclerView RecyclerView;
    @Bind(R.id.SwipeRefresh_Layout)
    android.support.v4.widget.SwipeRefreshLayout SwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        //如果可以确定每个Item的高度固定，选用这个属性可以提高性能
        RecyclerView.setHasFixedSize(true);
        //使用默认的LayoutManager
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.setItemAnimator(new DefaultItemAnimator());



    }
}
