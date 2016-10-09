package com.example.hante.newprojectsum.serviceactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.service.SimpleService;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ServiceActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.service_titleBar)
    TitleBar mServiceTitleBar;

    private  boolean mIfStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        mServiceTitleBar.setMyCenterTitle("Service");
        mServiceTitleBar.setMySettingText("启动");
        setSupportActionBar(mServiceTitleBar);
        if(getSupportActionBar() == null){
            return;
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mServiceTitleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mServiceTitleBar.setSettingTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iService = new Intent(getApplicationContext(), SimpleService.class);
                if(!mIfStart){
                    Toast.makeText(ServiceActivity.this, "启动Service", Toast.LENGTH_SHORT).show();
                    iService.putExtra("name","Service");
                    startService(iService);
                    mIfStart = true;
                }else  {
                    Toast.makeText(ServiceActivity.this, "停止Service", Toast.LENGTH_SHORT).show();
                    stopService(iService);
                    mIfStart = false;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
