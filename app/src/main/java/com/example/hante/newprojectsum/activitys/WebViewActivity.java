package com.example.hante.newprojectsum.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.LoadingWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.loading_webview)
    LoadingWebView loadingWebview;

    private String mTitle = "";
    private String mUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        initUI();
        initData();
    }

    private void initData() {
        mTitle = getIntent().getStringExtra("title");
        mUrl = getIntent().getStringExtra("url");
        tvTitle.setText(mTitle);
        loadingWebview.loadMessageUrl(mUrl);
    }

    private void initUI() {
        loadingWebview.addProgressBar();
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == ivBack){
                if(loadingWebview.canGoBack()){
                    loadingWebview.goBack();
                } else {
                    finish();
                }
        }
    }

    @Override
    public void onBackPressed() {
        if(loadingWebview.canGoBack()){
            loadingWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingWebview.destroyWebView();
    }
}
