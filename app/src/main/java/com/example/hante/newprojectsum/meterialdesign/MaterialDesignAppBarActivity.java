package com.example.hante.newprojectsum.meterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.activitys.WebViewActivity;
import com.example.hante.newprojectsum.common.Constant;
import com.example.hante.newprojectsum.common.ProgressDialogFragment;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.meterialdesign.adapter.ZhihuRecyclerViewAdapter;
import com.example.hante.newprojectsum.meterialdesign.adapter.ZhihuRecyclerViewAdapterReset;
import com.example.hante.newprojectsum.meterialdesign.bean.ZhihuArt;
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

    //
    private ArrayList<ZhihuArt> mListZhihu;
    private ZhihuArt mZhihuArt;
    private ZhihuRecyclerViewAdapterReset  mZhihuReset;
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
        mZhiHuTopStory = new ArrayList<>();
        mZhiHuStory = new ArrayList<>();
        mListZhihu = new ArrayList<>();
        loadData();
    }

    private void recyclerViewItemClick () {
        mZhihuReset.setOnItemClickListener(new ZhihuRecyclerViewAdapterReset.onItemClickListener() {
            @Override
            public void onItemClick (View view, int position) {
                String id = mListZhihu.get(position).getmId();
                Log.d(TAG, "onItemClick: ID" + id);
                String title = mListZhihu.get(position).getmTitle();
                Intent intent = new Intent(getApplicationContext(),
                        WebViewActivity.class);
                String url = Constant.ARTICELITEM + id;
                intent.putExtra("url",
                        url);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }


    private void loadData () {
        setProgress(true);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Constant.ZHIHUURL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, "onSuccess: 返回请求" + response.toString());
                String backResp = response.toString();
                setProgress(false);
                try {
                    JSONObject mJsonObj =  new JSONObject(backResp);
                    String date = mJsonObj.getString("date");// 日期
                    if(date != null){
                        mToolbarMD.setMyCenterTitle(date);
                    }
                    JSONArray storiesJA = mJsonObj.getJSONArray("stories");
                    JSONArray top_storiesJA = mJsonObj.getJSONArray("top_stories");
                    for(int i = 0;i < storiesJA.length(); i ++){
                        mZhihuArt = new ZhihuArt();
                        mStoriesBean = new ZhihuArticle.StoriesBean();
                        JSONObject Sto = storiesJA.getJSONObject(i);
                        mStoriesBean.setMTitle(Sto.getString("title"));
                        mZhihuArt.setmTitle(Sto.getString("title"));
                        mZhihuArt.setmId(Sto.getString("id"));
                        JSONArray imagesJB = Sto.getJSONArray("images");
                        mList = new ArrayList<>();
                        for(int j = 0;j<imagesJB.length();j++){
                            String string = imagesJB.getString(j);
                            mZhihuArt.setmImage(string);
                            mList.add(string);
                        }
                        mStoriesBean.setMImages(mList);
                        mListZhihu.add(mZhihuArt);
                        mZhiHuStory.add(mStoriesBean);
                    }
                    for(int e = 0; e<top_storiesJA.length(); e++){
                        JSONObject jsonObjectTop = top_storiesJA.getJSONObject(e);
                        mTopStoriesBean = new ZhihuArticle.TopStoriesBean();
                        mZhihuArt = new ZhihuArt();
                        mTopStoriesBean.setMTitle(jsonObjectTop.getString("title"));
                        mTopStoriesBean.setMImage(jsonObjectTop.getString("image"));
                        mZhihuArt.setmImage(jsonObjectTop.getString("image"));
                        mZhihuArt.setmTitle(jsonObjectTop.getString("title"));
                        mZhihuArt.setmId(jsonObjectTop.getString("id"));
                        mZhiHuTopStory.add(mTopStoriesBean);
                        mListZhihu.add(mZhihuArt);
                    }
//                    mZhiHuAdapter = new ZhihuRecyclerViewAdapter(MaterialDesignAppBarActivity.this,
//                            mZhiHuStory,
//                            mZhiHuTopStory);
                    mZhihuReset = new ZhihuRecyclerViewAdapterReset(getApplicationContext(),
                            mListZhihu);
                    mMDRecyclerView.setAdapter(mZhihuReset);
                    recyclerViewItemClick();
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure (int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                setProgress(false);
            }
        });

    }
    /**
     * ProgressDialogFragment
     * @param show true/false 进度条是否显示/隐藏
     */
    private void setProgress(boolean show){
        ProgressDialogFragment  progressDialogFragment = new ProgressDialogFragment();
        if(show){
            progressDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE,R.style.loading_dialog);
            progressDialogFragment.setCancelable(false);
            progressDialogFragment.show(getSupportFragmentManager(),"Dialog");
        } else  {
            Fragment dialog = getSupportFragmentManager().findFragmentByTag("Dialog");
            if(dialog != null){
                ProgressDialogFragment pdf = (ProgressDialogFragment) dialog;
                pdf.dismiss();
            }
        }
    }
}
