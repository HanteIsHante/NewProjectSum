package com.example.hante.newprojectsum.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.pay.fragment.PayStyle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 弹出 支付框
 */
public class PayActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.pay_toolbar)
    TitleBar mPayToolbar;
    @Bind(R.id.buy_vip)
    Button mBuyVip;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI () {
        mPayToolbar.setMyCenterTitle("Pay");
        setSupportActionBar(mPayToolbar);
        if(getSupportActionBar() == null) {
            return;
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPayToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                finish();
            }
        });
        mBuyVip.setOnClickListener(this);
    }

    public static Intent startActivity (Context context) {
        return new Intent(context, PayActivity.class);
    }

    @Override
    public void onClick (View v) {
        if(v == mBuyVip){
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            PayStyle payStyle = new PayStyle();
            payStyle.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.payStyle);
            payStyle.show(fm, "dialog");
        }
    }
}
