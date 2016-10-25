package com.example.hante.newprojectsum.tablelayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.tablelayout.adapter.MyFragmentPagerAdapter;
import com.example.hante.newprojectsum.tablelayout.fragment.AFragment;
import com.example.hante.newprojectsum.tablelayout.fragment.BFragment;
import com.example.hante.newprojectsum.tablelayout.fragment.CFragment;
import com.example.hante.newprojectsum.tablelayout.fragment.DFragment;
import com.example.hante.newprojectsum.tablelayout.fragment.EFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class TabLayoutActivity extends BaseActivity {

    @Bind(R.id.toolbar_tabLayout)
    TitleBar mToolbarTabLayout;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private List<Fragment> mListFragment;
    private List<String> mListTitle;
    private AFragment mAFragment;
    private BFragment mBFragment;
    private CFragment mCFragment;
    private DFragment mDFragment;
    private EFragment mEFragment;
    private MyFragmentPagerAdapter mMyFragmentAdapter;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI () {
        mToolbarTabLayout.setMyCenterTitle("TabLayout");
        getSupportActionBar();
        setSupportActionBar(mToolbarTabLayout);
        if(getSupportActionBar() == null) {
            return;
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// Show toolbar return arrow.
        mToolbarTabLayout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                finish();
            }
        });

       initTab();
    }



    private void initTab () {

        mAFragment = new AFragment();
        mBFragment = new BFragment();
        mCFragment = new CFragment();
        mDFragment = new DFragment();
        mEFragment = new EFragment();
        mListFragment = new ArrayList<>();
        mListFragment.add(mEFragment);
        mListFragment.add(mAFragment);
        mListFragment.add(mBFragment);
        mListFragment.add(mCFragment);
        mListFragment.add(mDFragment);
        // 将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        mListTitle = new ArrayList<>();
        mListTitle.add("热门免费");
        mListTitle.add("热门付费");
        mListTitle.add("创收最高");
        mListTitle.add("畅销付费新品");
        mListTitle.add("热门免费新品");
/*
    下面就来解释一下TabGravity和TabMode：

    TabGravity：放置Tab的Gravity，有GRAVITY_CENTER 和 GRAVITY_FILL两种效果。
    一个是居中，另一个是尽可能的填充（注意：GRAVITY_FILL需要和MODE_FIXED一起使用才有效果）

    TabMode：布局中Tab的行为模式（behavior mode），
     有两种值：MODE_FIXED 和 MODE_SCROLLABLE。

    MODE_FIXED：固定tabs，并同时显示所有的tabs。
    MODE_SCROLLABLE：可滚动tabs，显示一部分tabs，
   在这个模式下能包含长标签和大量的tabs，最好用于用户不需要直接比较tabs。*/

        // mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // 设置TabLayout的模式
        mTabLayout.setTabMode(MODE_SCROLLABLE);
        // mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        // 为TabLayout添加tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(4)));

        mMyFragmentAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                mListFragment,mListTitle);
        mViewPager.setAdapter(mMyFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
