package com.example.hante.newprojectsum.pay.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hante.newprojectsum.R;

/**
 * 选择支付方式后显示支付详情页面
 */

public class PayDetail extends DialogFragment{


    @Override
    public void onStart () {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();//设置弹出框宽屏显示，适应屏幕宽度
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        if(getDialog().getWindow() != null){
            getDialog().getWindow().setLayout(dm.widthPixels,getDialog().getWindow().getAttributes().height);
        }
        // 移动弹出菜单到底部
        WindowManager.LayoutParams wlp = getDialog().getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        getDialog().getWindow().setAttributes(wlp);
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        View inflate = inflater.inflate(R.layout.pay_detail, container, false);
        Bundle arguments = getArguments();
        int payStyle = arguments.getInt("payStyle");
        
        Toast.makeText(getActivity(), "支付方式 " + R.style.payStyle, Toast.LENGTH_SHORT).show();
        return inflate;
    }
}
