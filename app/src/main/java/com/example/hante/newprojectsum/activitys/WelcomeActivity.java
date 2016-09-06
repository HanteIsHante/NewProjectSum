package com.example.hante.newprojectsum.activitys;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.NewProjectSumHomeActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.util.SharedPreferenceUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.welcome_)
    LinearLayout welcome;
    private AlphaAnimation mHideAnimation = null;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        boolean isFirstOpen = SharedPreferenceUtil.getBoolean(WelcomeActivity.this, SharedPreferenceUtil.First_Open, true);
        if (isFirstOpen) {
            Intent intent = new Intent(WelcomeActivity.this,ViewPagerActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        // 进入的一个页面渐隐
        handler = new Handler();
        startopenAnimation();
        Log.d("", "onCreate: ++++++++++++");
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
            getWindow().setExitTransition(transition);// 退出时
        }
    }

    private void startopenAnimation() {
        setHideAnimation(welcome, 4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, NewProjectSumHomeActivity.class);
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
                    startActivity(i,ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this).toBundle());
                }
                startActivity(i);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_leave);
                finish();
            }
        },2500);
    }

    /**
     * View渐隐动画效果
     */
    private void setHideAnimation(View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }
        if (null != mHideAnimation) {
            mHideAnimation.cancel();
        }
        mHideAnimation = new AlphaAnimation(1.0f, 0.4f);
        mHideAnimation.setDuration(duration);
        mHideAnimation.setFillAfter(true);
        view.startAnimation(mHideAnimation);
    }
    /**
     * 屏蔽物理返回按钮
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
