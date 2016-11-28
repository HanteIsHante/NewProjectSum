package com.example.hante.newprojectsum.tablelayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.common.Constant;
import com.example.hante.newprojectsum.meterialdesign.bean.ZhihuArt;
import com.example.hante.newprojectsum.net.retrofit.IRetrofit;
import com.example.hante.newprojectsum.okhttpactivity.model.ZhiHu;
import com.example.hante.newprojectsum.tablelayout.LazyFragment;
import com.example.hante.newprojectsum.tablelayout.adapter.RecyclerVIewFragA;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 实现懒加载   使用 Retrofit  加载数据
 */

public class AFragment extends LazyFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "AFragment";
    public static final String INTENT_INT_INDEX="index";
    private IRetrofit mIRetrofit;
    private Call<ZhiHu> mCall;
    private List<ZhihuArt> mListZhiHu;
    private ZhihuArt mZhiHu;
    private RecyclerVIewFragA mAadapter;
    private SampleCallBack mCallBack;
    private Context mContext;
    private RecyclerView mRecyclerViewA;
    private SwipeRefreshLayout mRefreshLayout;
    public static AFragment newInstance(int tabIndex,boolean isLazyLoad) {

        Bundle args = new Bundle();
        args.putInt(INTENT_INT_INDEX, tabIndex);
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        AFragment fragment = new AFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_a);
        initUI();
        mContext = getApplicationContext();
        mCallBack = new SampleCallBack();
        initRetrofit();
        loadData();
    }

    private void initUI () {
        mRecyclerViewA = (RecyclerView)findViewById(R.id.fraA_recyclerView);
        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.Refresh_Layout);
        mRecyclerViewA.setHasFixedSize(true);
        mRecyclerViewA.setLayoutManager(new LinearLayoutManager(getActivity()));// 必设
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(this);
        mListZhiHu = new ArrayList<>();
    }

    private void loadData () {
        if(mListZhiHu.size() > 0 ){
            mListZhiHu.clear();
        }
        mCall = mIRetrofit.getTodayNews();
        mCall.enqueue(mCallBack);
    }

    /**
     * 初始化Retrofit实例，并创建接口类。
     * 注意：IRetrofit 不需要我们去实现，直接Retrofit=类有create方法生成。
     */
    private void initRetrofit () {
        //创建Retrofit的实例，把Gson转换器设置下
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)//设置API的基础地址
                .addConverterFactory(GsonConverterFactory.create())//设置后才才支持json字符串转化为Bean
                .build();

        //使用Retrofit的create方法传入创建接口实例
        mIRetrofit = retrofit.create(IRetrofit.class);
    }

    @Override
    public void onRefresh () {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run () {
                mRefreshLayout.isRefreshing();
                loadData();
            }
        });
    }

    /**
     * 回调实现类
     */
    private class SampleCallBack implements Callback<ZhiHu> {
        @Override
        public void onResponse (Call<ZhiHu> call, Response<ZhiHu> response) {
            if(response.isSuccessful()){
                // 直接拿到javabean
                ZhiHu body = response.body();
                Log.d(TAG, "onResponse: 返回结果" + body);
                getDetailInfo(body);
                if(mRefreshLayout.isRefreshing()){
                    mRefreshLayout.setRefreshing(false);
                }
                // 这里可直接进行UI操作，OKHttp 则不可以
            } else {
                if(mRefreshLayout.isRefreshing()){
                    mRefreshLayout.setRefreshing(false);
                }
                //请求失败。如果代码执行到这里来说明是有跟后台握手的，
                // 是后台处理有问题，如404（没有资源）,500（后台报错了）
                Toast.makeText(mContext, "Data Null", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure (Call<ZhiHu> call, Throwable t) {
            //请求失败。如，没有声明网络权限、没有网络、
            // 或者是Retrofit 异常内部处理异常（如Gson解析失败）也是会到这里
        }
    }

    /**
     * 将获取的 数据进行分解
     * @param body 获取到的实体类
     */
    private void getDetailInfo (ZhiHu body) {
        List<ZhiHu.StoriesBean> mStories = body.getMStories();
        for(int i = 0; i < mStories.size(); i++) {
            mZhiHu = new ZhihuArt();
            ZhiHu.StoriesBean storiesBean = mStories.get(i);
            List<String> mImages = storiesBean.getMImages();
            mZhiHu.setmTitle(storiesBean.getMTitle());
            for(int i1 = 0; i1 < mImages.size(); i1++) {
                String s = mImages.get(i1);
                mZhiHu.setmImage(s);
            }
            if(mZhiHu != null) {
                mListZhiHu.add(mZhiHu);
            }
        }
        List<ZhiHu.TopStoriesBean> mTop_stories = body.getMTop_stories();
        for(int i = 0; i < mTop_stories.size(); i++) {
            mZhiHu = new ZhihuArt();
            ZhiHu.TopStoriesBean topStoriesBean = mTop_stories.get(i);
            mZhiHu.setmImage(topStoriesBean.getMImage());
            mZhiHu.setmTitle(topStoriesBean.getMTitle());
            if(mZhiHu != null) {
                mListZhiHu.add(mZhiHu);
            }
        }
        if(mListZhiHu != null) {
            mAadapter = new RecyclerVIewFragA(getApplicationContext(), mListZhiHu);
            mRecyclerViewA.setAdapter(mAadapter);
        } else {
            Toast.makeText(mContext, "刷新失败", Toast.LENGTH_SHORT).show();
        }
    }
}
