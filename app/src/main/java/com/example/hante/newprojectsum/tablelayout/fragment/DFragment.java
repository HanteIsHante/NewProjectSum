package com.example.hante.newprojectsum.tablelayout.fragment;

import android.os.Bundle;

import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.tablelayout.LazyFragment;

/**
 * Created by handan on 2016/10/25.
 */

public class DFragment extends LazyFragment {

    public static final String INTENT_INT_INDEX="index";
    public static DFragment newInstance(int tabIndex,boolean isLazyLoad) {

        Bundle args = new Bundle();
        args.putInt(INTENT_INT_INDEX, tabIndex);
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        DFragment fragment = new DFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_d);

    }
}
