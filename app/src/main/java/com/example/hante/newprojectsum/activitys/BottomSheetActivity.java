package com.example.hante.newprojectsum.activitys;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BottomSheetActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.bottomSheet_titleBar)
    TitleBar bottomSheetTitleBar;
    @Bind(R.id.bottomSheet_scrollView)
    NestedScrollView bottomSheetScrollView;

    BottomSheetBehavior mBottomSheetBehavior;
    @Bind(R.id.shareto_left_margin)
    LinearLayout sharetoLeftMargin;
    @Bind(R.id.share_to_textView)
    TextView shareToTextView;
    @Bind(R.id.share_to_textLinear)
    LinearLayout shareToTextLinear;
    @Bind(R.id.wechat_left_margin)
    LinearLayout wechatLeftMargin;
    @Bind(R.id.share_wechat)
    TextView shareWechat;
    @Bind(R.id.wechat)
    TextView wechat;
    @Bind(R.id.share_dialog_wechat_linear)
    LinearLayout shareDialogWechatLinear;
    @Bind(R.id.weibo_left_margin)
    LinearLayout weiboLeftMargin;
    @Bind(R.id.share_weibo)
    TextView shareWeibo;
    @Bind(R.id.weibo)
    TextView weibo;
    @Bind(R.id.share_dialog_weibo_linear)
    LinearLayout shareDialogWeiboLinear;
    @Bind(R.id.facebook_left_margin)
    LinearLayout facebookLeftMargin;
    @Bind(R.id.share_facebook)
    TextView shareFacebook;
    @Bind(R.id.facebook)
    TextView facebook;
    @Bind(R.id.share_dialog_facebook_linear)
    LinearLayout shareDialogFacebookLinear;
    @Bind(R.id.twitter_left_margin)
    LinearLayout twitterLeftMargin;
    @Bind(R.id.share_twitter)
    TextView shareTwitter;
    @Bind(R.id.twitter)
    TextView twitter;
    @Bind(R.id.share_dialog_twitter_linear)
    LinearLayout shareDialogTwitterLinear;
    @Bind(R.id.googleplus_left_margin)
    LinearLayout googleplusLeftMargin;
    @Bind(R.id.share_googleplus)
    TextView shareGoogleplus;
    @Bind(R.id.googleplus)
    TextView googleplus;
    @Bind(R.id.share_dialog_googleplus_linear)
    LinearLayout shareDialogGoogleplusLinear;
    @Bind(R.id.custom_left_margin)
    LinearLayout customLeftMargin;
    @Bind(R.id.share_custom)
    TextView shareCustom;
    @Bind(R.id.custom)
    TextView custom;
    @Bind(R.id.share_dialog_custom_linear)
    LinearLayout shareDialogCustomLinear;
    @Bind(R.id.share_get_coins_left_margin)
    LinearLayout shareGetCoinsLeftMargin;
    @Bind(R.id.share_dialog_share_get_nb_coins_linear)
    TextView shareDialogShareGetNbCoinsLinear;
    @Bind(R.id.get_coins_textLinear)
    LinearLayout getCoinsTextLinear;
    @Bind(R.id.below_dialog_linear)
    LinearLayout belowDialogLinear;
    @Bind(R.id.root_share_dialog)
    LinearLayout rootShareDialog;
    @Bind(R.id.activity_CoordinatorLayout)
    CoordinatorLayout activityCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);
        initUI();
        setViewTreeObeserver();
    }

    private void setViewTreeObeserver() {
        ViewTreeObserver viewTreeObserver = activityCoordinatorLayout.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        activityCoordinatorLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    int height = activityCoordinatorLayout.getHeight();// 获取的是根布局的宽高

                    double v = height * 4.76 / 100;
                    shareWechat.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) v);
                    shareWeibo.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) v);
                    shareFacebook.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) v);
                    shareTwitter.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) v);
                    shareGoogleplus.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) v);
                    shareCustom.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) v);

                    double shareItemsLinearHeightPixels = height * 8.64 / 100;
                    shareDialogWechatLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;
                    shareDialogWeiboLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;
                    shareDialogFacebookLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;
                    shareDialogTwitterLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;
                    shareDialogGoogleplusLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;
                    shareDialogCustomLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;
                    getCoinsTextLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;
                    shareToTextLinear.getLayoutParams().height = (int) shareItemsLinearHeightPixels;

                    double leftMarginPixels = height * 4.41 / 100;
                    sharetoLeftMargin.getLayoutParams().width = (int) leftMarginPixels;
                    wechatLeftMargin.getLayoutParams().width = (int) leftMarginPixels;
                    weiboLeftMargin.getLayoutParams().width = (int) leftMarginPixels;
                    facebookLeftMargin.getLayoutParams().width = (int) leftMarginPixels;
                    twitterLeftMargin.getLayoutParams().width = (int) leftMarginPixels;
                    googleplusLeftMargin.getLayoutParams().width = (int) leftMarginPixels;
                    customLeftMargin.getLayoutParams().width = (int) leftMarginPixels;
                    shareGetCoinsLeftMargin.getLayoutParams().width = (int) leftMarginPixels;

                    double shareToTextViewPixels = height * 3.53 / 100;
                    shareToTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareToTextViewPixels);
                    double shareNPCoinsPixels = height * 2.47 / 100;
                    shareDialogShareGetNbCoinsLinear.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareNPCoinsPixels);

                    double shareItemsTextsPixels = height * 3 / 100;
                    wechat.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareItemsTextsPixels);
                    weibo.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareItemsTextsPixels);
                    facebook.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareItemsTextsPixels);
                    twitter.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareItemsTextsPixels);
                    googleplus.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareItemsTextsPixels);
                    custom.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) shareItemsTextsPixels);

                    double shareDialogPixels = height * 68 / 100;
                    rootShareDialog.getLayoutParams().height = (int) shareDialogPixels;

                    double shareDialogBottomPixels = height * 2.12 / 100;
                    belowDialogLinear.getLayoutParams().height = (int) shareDialogBottomPixels;
                }
            });
        }
    }


    private void initUI() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Netpas-Android.ttf");
        shareWechat.setTypeface(typeface);
        shareWeibo.setTypeface(typeface);
        shareFacebook.setTypeface(typeface);
        shareTwitter.setTypeface(typeface);
        shareGoogleplus.setTypeface(typeface);
        shareCustom.setTypeface(typeface);
        shareDialogWechatLinear.setOnClickListener(this);
        shareDialogWeiboLinear.setOnClickListener(this);
        shareDialogFacebookLinear.setOnClickListener(this);
        shareDialogTwitterLinear.setOnClickListener(this);
        shareDialogGoogleplusLinear.setOnClickListener(this);
        shareDialogCustomLinear.setOnClickListener(this);
        bottomSheetTitleBar.setMyCenterTitle("BottomSheet");
        bottomSheetTitleBar.setMySettingText("分享");
        setSupportActionBar(bottomSheetTitleBar);
        if (getSupportActionBar() == null) {
            return;
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheetScrollView);

        bottomSheetTitleBar.setSettingTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetScrollView.setVisibility(View.VISIBLE);
                mBottomSheetBehavior.setPeekHeight(330);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        if(view == shareDialogWechatLinear){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (view == shareDialogWeiboLinear){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (view == shareDialogFacebookLinear){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (view == shareDialogFacebookLinear){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (view == shareDialogTwitterLinear){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (view == shareDialogGoogleplusLinear){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (view == shareDialogCustomLinear){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }
}
