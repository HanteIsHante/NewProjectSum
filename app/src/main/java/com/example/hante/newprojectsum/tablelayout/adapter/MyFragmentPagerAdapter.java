package com.example.hante.newprojectsum.tablelayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by handan on 2016/10/25.
 */

public class MyFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    private List<Fragment> mListFragment;
    private List<String>  mListTitle;

    public MyFragmentPagerAdapter (FragmentManager fm, List<Fragment> mListFragment, List<String> mListTitle) {
        super(fm);
        this.mListFragment = mListFragment;
        this.mListTitle = mListTitle;
    }

    @Override
    public Fragment getItem (int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount () {
        return mListTitle.size();
    }
    // 此方法用来显示Tab上的名字
    @Override
    public CharSequence getPageTitle (int position) {
        return mListTitle.get(position % mListTitle.size());
    }
}
