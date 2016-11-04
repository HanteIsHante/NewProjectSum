package com.example.hante.newprojectsum.okhttpactivity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.common.Constant;
import com.example.hante.newprojectsum.common.ProgressDialogFragment;
import com.example.hante.newprojectsum.okhttpactivity.model.ZhiHu;
import com.example.hante.newprojectsum.okhttpactivity.model.ZhiHuInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends BaseActivity {

    private static final String TAG = "OkHttpActivity";
    @Bind(R.id.okhttp_recycler)
    RecyclerView mOkhttpRecycler;
    private OkHttpRecyclerAdapter mOkHttpAdapter;
    private Gson mGson;
    private ZhiHu mZhiHu;
    private ZhiHuInfo mZhiHuInfo;
    private ArrayList<ZhiHuInfo> mListZhiHuInfo;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
        setProgress(true);
        initUI();
        loadUrlData();
    }

    private void loadUrlData () {
        OkHttpClient mOkHttp = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.ZHIHUUrl)
                .get()
                .build();
        mOkHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure (Call call, IOException e) {
                Log.d(TAG, "onFailure: 失败");
                setProgress(false);
            }

            @Override
            public void onResponse (Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: Success" + json);
                ZhiHu zhiHu = mGson.fromJson(json, ZhiHu.class);
                Log.d(TAG, "onResponse: 知乎知乎 " + zhiHu.getMStories().toString());
                List<ZhiHu.StoriesBean> mStories = zhiHu.getMStories();
                List<ZhiHu.TopStoriesBean> mTop_stories = zhiHu.getMTop_stories();
                for(int i = 0;i<mStories.size();i++){
                    mZhiHuInfo = new ZhiHuInfo();
                    int mId = mStories.get(i).getMId();
                    mZhiHuInfo.setmId(String.valueOf(mId));
                    List<String> mImages = mStories.get(i).getMImages();
                    for(int m=0;m<mImages.size();m++){
                        mZhiHuInfo.setmImg(mImages.get(m));
                    }
                    String mTitle = mStories.get(i).getMTitle();
                    mZhiHuInfo.setmTitle(mTitle);
                    mListZhiHuInfo.add(mZhiHuInfo);
                }
                for(int t = 0 ;t < mTop_stories.size() ; t++){
                    mZhiHuInfo = new ZhiHuInfo();
                    int mId = mTop_stories.get(t).getMId();
                    mZhiHuInfo.setmId(String.valueOf(mId));
                    String mImage = mTop_stories.get(t).getMImage();
                    mZhiHuInfo.setmImg(mImage);
                    String mTitle = mTop_stories.get(t).getMTitle();
                    mZhiHuInfo.setmTitle(mTitle);
                    mListZhiHuInfo.add(mZhiHuInfo);
                }
                setProgress(false);
                inputUIData();
            }
        });
    }
    private void inputUIData () {
        runOnUiThread(new Runnable() {
            @Override
            public void run () {
                mOkHttpAdapter = new OkHttpRecyclerAdapter(mListZhiHuInfo,getApplicationContext());
                mOkhttpRecycler.setAdapter(mOkHttpAdapter);
            }
        });
    }

    private void initUI () {
        mOkhttpRecycler.setHasFixedSize(true);
        mOkhttpRecycler.setLayoutManager(new LinearLayoutManager(this));
        mGson = new Gson();
        mZhiHu = new ZhiHu();
        mListZhiHuInfo = new ArrayList<>();
    }

    /**
     * ProgressDialogFragment
     * @param show true/false 进度条是否显示/隐藏
     */
    private  void setProgress (boolean show){
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
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
