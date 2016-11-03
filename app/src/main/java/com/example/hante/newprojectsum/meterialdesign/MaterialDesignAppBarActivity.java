package com.example.hante.newprojectsum.meterialdesign;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.common.Constant;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.meterialdesign.adapter.ZhihuRecyclerViewAdapter;
import com.example.hante.newprojectsum.meterialdesign.bean.ZhihuArticle;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * MaterialDesign
 */
public class MaterialDesignAppBarActivity extends BaseActivity {

    private static final String TAG = "MaterialDesignAppBarAct";
    @Bind(R.id.toolbar_MD)
    TitleBar mToolbarMD;
    @Bind(R.id.MD_RecyclerView)
    RecyclerView mMDRecyclerView;
    @Bind(R.id.activity_material_design_app_bar)
    CoordinatorLayout mActivityMaterialDesignAppBar;
    private ZhihuRecyclerViewAdapter mZhiHuAdapter;
    private ArrayList<ZhihuArticle.TopStoriesBean> mZhiHuTopStory;
    private ArrayList<ZhihuArticle.StoriesBean> mZhiHuStory;
    private ZhihuArticle.StoriesBean mStoriesBean;
    private ZhihuArticle.TopStoriesBean mTopStoriesBean;
    private  List<String> mList;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_app_bar);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI () {
        setSupportActionBar(mToolbarMD);
        if(getSupportActionBar() == null) {
            return;
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarMD.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                finish();
            }
        });
        //如果可以确定每个Item的高度固定，选用这个属性可以提高性能
        mMDRecyclerView.setHasFixedSize(true);
        //使用默认的LayoutManager
        mMDRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mZhiHuTopStory = new ArrayList<ZhihuArticle.TopStoriesBean>();
        mZhiHuStory = new ArrayList<ZhihuArticle.StoriesBean>();

        loadData();
    }

    private void loadData () {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Constant.ZHIHUURL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, "onSuccess: 返回请求" + response.toString());
                String backResp = response.toString();
                try {
                    JSONObject mJsonObj =  new JSONObject(backResp);
                    String date = mJsonObj.getString("date");// 日期
                    JSONArray storiesJA = mJsonObj.getJSONArray("stories");
                    JSONArray top_storiesJA = mJsonObj.getJSONArray("top_stories");
                    for(int i = 0;i < storiesJA.length(); i ++){

                        mStoriesBean = new ZhihuArticle.StoriesBean();
                        JSONObject Sto = storiesJA.getJSONObject(i);
                        mStoriesBean.setMTitle(Sto.getString("title"));
                        JSONArray imagesJB = Sto.getJSONArray("images");
                        mList = new ArrayList<>();
                        for(int j = 0;j<imagesJB.length();j++){
                            String string = imagesJB.getString(j);
                            mList.add(string);
                        }
                        mStoriesBean.setMImages(mList);
                        mZhiHuStory.add(mStoriesBean);
                    }
                    for(int e = 0; e<top_storiesJA.length(); e++){
                        JSONObject jsonObjectTop = top_storiesJA.getJSONObject(e);
                        mTopStoriesBean = new ZhihuArticle.TopStoriesBean();
                        mTopStoriesBean.setMTitle(jsonObjectTop.getString("title"));
                        mTopStoriesBean.setMImage(jsonObjectTop.getString("image"));
                        mZhiHuTopStory.add(mTopStoriesBean);
                    }
                    mZhiHuAdapter = new ZhihuRecyclerViewAdapter(MaterialDesignAppBarActivity.this,
                            mZhiHuStory,
                            mZhiHuTopStory);
                    mMDRecyclerView.setAdapter(mZhiHuAdapter);
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure (int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }
}
