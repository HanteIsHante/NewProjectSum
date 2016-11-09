package com.example.hante.newprojectsum.tablelayout.fragment;

import android.os.Bundle;
import android.util.Log;

import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.bean.douban.DouBanMovies;
import com.example.hante.newprojectsum.common.Constant;
import com.example.hante.newprojectsum.net.retrofit.IRetrofit;
import com.example.hante.newprojectsum.tablelayout.LazyFragment;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  实现懒加载
 */

public class BFragment extends LazyFragment {

    private static final String TAG = "BFragment";
    public static final String INTENT_INT_INDEX="index";
    private IRetrofit mIRetrofit;
    private Call<DouBanMovies> mCall;
    public static BFragment newInstance(int tabIndex,boolean isLazyLoad) {

        Bundle args = new Bundle();
        args.putInt(INTENT_INT_INDEX, tabIndex);
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        BFragment fragment = new BFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_b);

        initRetrofit();
        LoadData();
    }

    private void LoadData () {
        Log.d(TAG, "LoadData: 加载数据");
        mCall = mIRetrofit.getDouBanMovies(0,10);
        mCall.enqueue(new retrofit2.Callback<DouBanMovies>() {
            @Override
            public void onResponse (Call<DouBanMovies> call, Response<DouBanMovies> response) {
                if(response.isSuccessful()){
                    DouBanMovies bodyDouBan = response.body();
                    Log.d(TAG, "onResponse: 豆瓣返回结果" + bodyDouBan.getMTitle());
                } else {
                    Log.d(TAG, "onResponse: 豆瓣返回结果 失败");
                }

            }

            @Override
            public void onFailure (Call<DouBanMovies> call, Throwable t) {
                Log.d(TAG, "onFailure: 失败" + t);
            }
        });
    }

    private void initRetrofit () {
        Retrofit mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.DOUBAN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mIRetrofit = mRetrofit.create(IRetrofit.class);
        Log.d(TAG, "initRetrofit: 初始化");
    }


}
