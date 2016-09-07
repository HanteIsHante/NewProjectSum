package com.example.hante.newprojectsum.activitys;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserInfomationActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.user_titlebar)
    TitleBar userTitlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infomation);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        userTitlebar.setMyCenterTitle("用户信息");
        userTitlebar.setMySettingText("编辑");
        setSupportActionBar(userTitlebar);
        // 做出Navigationview 为返回
        if(getSupportActionBar() == null){
            return;
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userTitlebar.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view == userTitlebar){

        }
    }
}
