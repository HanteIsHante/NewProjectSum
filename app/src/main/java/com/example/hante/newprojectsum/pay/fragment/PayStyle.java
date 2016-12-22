package com.example.hante.newprojectsum.pay.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.itemdevider.LinearSpacesItemDecoration;
import com.example.hante.newprojectsum.pay.adapter.BuyAdapter;
import com.example.hante.newprojectsum.pay.bean.BuyStyle;
import com.example.hante.newprojectsum.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 弹出支付方式选择框列表
 */

public class PayStyle extends DialogFragment implements View.OnClickListener {


    @Bind(R.id.clear_icon)
    ImageView mClearIcon;
    @Bind(R.id.RView_payStyle)
    RecyclerView mRViewPayStyle;
    private BuyAdapter mBuyAdapter;
    private List<BuyStyle> list = null;
    private BuyStyle buyStyle = null;
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
//            getDialog().getWindow().setWindowAnimations(R.style.payStyle);
        }
        View inflate = inflater.inflate(R.layout.pay_style, container, false);
        ButterKnife.bind(this, inflate);
        initUi();
        loadData();
        return inflate;
    }

    private void loadData () {
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            buyStyle   = new BuyStyle();
            buyStyle.setId_code(i);
            list.add(buyStyle);
        }
        mBuyAdapter = new BuyAdapter(list, getActivity());
        mRViewPayStyle.setHasFixedSize(true);
        mRViewPayStyle.setLayoutManager(new LinearLayoutManager(getActivity()));
        int left = Utils.dpToPx(2);
        int top = Utils.dpToPx(2);
        mRViewPayStyle.addItemDecoration(new LinearSpacesItemDecoration(left, top, 0));
        mRViewPayStyle.addItemDecoration(new LinearSpacesItemDecoration(left, top,
                getResources().getColor(R.color.header_green_bar_color)));
        mRViewPayStyle.setItemAnimator(new DefaultItemAnimator());
        mRViewPayStyle.setAdapter(mBuyAdapter);
        mBuyAdapter.setOnItemClickListener(new BuyAdapter.onItemClickListener() {
            @Override
            public void onItemClick (View view, int position) {
                Toast.makeText(getContext(), "点击位置 :" + position + " " ,
                        Toast.LENGTH_SHORT).show();
                int id_code = list.get(position).getId_code();
                PayDetail payDetail = new PayDetail();
                payDetail.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.payStyle);
                Bundle bundle = new Bundle();
                bundle.putInt("payStyle", id_code);
                payDetail.setArguments(bundle);
                payDetail.show(getFragmentManager(), "Detail");
                dismiss();
            }
        });
    }

    private void initUi () {
        mClearIcon.setOnClickListener(this);
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick (View v) {
        if(v == mClearIcon){
            dismiss();
        }
    }
}
