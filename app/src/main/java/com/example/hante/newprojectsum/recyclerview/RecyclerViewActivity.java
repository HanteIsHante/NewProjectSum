package com.example.hante.newprojectsum.recyclerview;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.bean.PrettyGirl;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.shareelements.ElementsActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class RecyclerViewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "RecyclerViewActivity";
    @Bind(R.id.Recycler_View)
    android.support.v7.widget.RecyclerView RecyclerView;
    @Bind(R.id.SwipeRefresh_Layout)
    android.support.v4.widget.SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerView_toolBar)
    TitleBar mRecyclerViewToolBar;
    private ArrayList<PrettyGirl> mArrayList;
    private PrettyGirl mPrettyGirl;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private int lastVisibleItem;

    private String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        initUI();
        newAnimationDesign();
    }
    private void initUI() {
        mArrayList = new ArrayList<>();
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
        // 分割线
        RecyclerView.addItemDecoration(new
                DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL_LIST));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run () {
                mSwipeRefreshLayout.setRefreshing(true);
                getDataByURL();
            }
        });
         // 滚动到某一位置 加载更多
        RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged (RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem +2>=
                        new LinearLayoutManager(getApplicationContext()).getItemCount()) {
                    getURLData(url,++page);
                }

            }

            @Override
            public void onScrolled (RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取加载的最后一个可见视图在适配器的位置。
                int lastVisibleItemPosition =
                        new LinearLayoutManager(getApplicationContext()).findLastVisibleItemPosition();
                lastVisibleItem = lastVisibleItemPosition;
            }
        });
    }

    /**
     * 获取远程数据
     */
    private void getDataByURL () {
        getURLData(url,page);
    }

    private void adapterDate () {
        if(page == 1){  // 加载更多后 执行刷新，否则会回到首条数据位置
            mRecyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),mArrayList);
            RecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerViewAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
        mRecyclerViewAdapter.SetOnItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick (View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击 " + position, Toast.LENGTH_SHORT)
                        .show();
                String url = mArrayList.get(position).getmImg();
                Intent iShareElements = new Intent(getApplicationContext(), ElementsActivity.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    View shareView = view;
                    String transitionName = getString(R.string.share_elements);
                    iShareElements.putExtra("url",url);
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(RecyclerViewActivity.this, shareView,
                            transitionName);
                    startActivity(iShareElements,activityOptions.toBundle());
                }

            }
        });
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        if(mArrayList != null){
            mRecyclerViewAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }else {
            mSwipeRefreshLayout.setRefreshing(true);
            getDataByURL();
        }
    }

    /**
     * 获取数据
     */
    private void getURLData (String url,int page) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url + page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                String getData = response.toString();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(getData);
                    String results = jsonObject.getString("results");
                    Log.d(TAG, "onSuccess: " + results);
                    JSONArray jsonArray =  new JSONArray(results);
                    Log.d(TAG, "onSuccess: 数据长度" + jsonArray.length());
                    for(int i =0;i<jsonArray.length();i++){
                        mPrettyGirl = new PrettyGirl();// 一定要放到这否则会只显示最后一条数据
                        JSONObject getJson = jsonArray.getJSONObject(i);
                        String url = getJson.getString("url");
                        mPrettyGirl.setmImg(url);
                        Log.d(TAG, "onSuccess: 图片链接" + url);
                        String who = getJson.getString("who");
                        mPrettyGirl.setmWho(who);
                        String publishedAt = getJson.getString("publishedAt");
                        mPrettyGirl.setmPublished(publishedAt);
                        mArrayList.add(mPrettyGirl);
                    }
                    adapterDate();
                } catch(JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure (int statusCode, Header[] headers, Throwable throwable, JSONObject
                    errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(RecyclerViewActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 页面退出动画
     */
    private void newAnimationDesign () {
        Fade fade = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fade = new Fade();
            fade.setDuration(300);
            getWindow().setExitTransition(fade);
        }


    }

}
