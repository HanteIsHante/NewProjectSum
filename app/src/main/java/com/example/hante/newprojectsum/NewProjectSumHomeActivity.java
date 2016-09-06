package com.example.hante.newprojectsum;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hante.newprojectsum.util.GlideCircleTransform;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewProjectSumHomeActivity extends BaseActivity {

    @Bind(R.id.design_navigation_view)
    NavigationView designNavigationView;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    private Context mContext;
    private  ImageView viewById;
    private ImageView backgroundImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_sum_home);
        ButterKnife.bind(this);
        mContext = this;
        navViewtoTop();
        initUI();
        setData();
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
            getWindow().setEnterTransition(transition);
        }
    }

    private void setData() {
        String url = "http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg";
        Glide.with(mContext).load(url).transform(new GlideCircleTransform(mContext)).into(viewById);
    }

    private void navViewtoTop() {
//        当系统版本小于5.0时，进行如下设置：
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            drawerlayout.setFitsSystemWindows(true);
            drawerlayout.setClipToPadding(false);
        }
    }

    private void initUI() {
        // 得到 顶部头像 Image
        View headerView = designNavigationView.getHeaderView(0);
         backgroundImg = (ImageView) headerView.findViewById(R.id.nav_head_background_img);
         viewById = (ImageView) headerView.findViewById(R.id.user_photo);
        designNavigationView.setItemIconTintList(null);// 图片本身颜色，不是统一颜色
        MenuItem menuItem = designNavigationView.getMenu().findItem(R.id.moments);
//        menuItem.setVisible(false);//true  显示 false 隐藏
        removeNavigationViewScroller(designNavigationView);  //去掉侧边滚动条
        clickOutNavigationViewClose();// 点击侧边框外部，关闭NavigationView
    }

    private void clickOutNavigationViewClose() {
        designNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.qq:
                        Toast.makeText(NewProjectSumHomeActivity.this, "QQ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.qzone:
                        Toast.makeText(NewProjectSumHomeActivity.this, "QZone", Toast.LENGTH_SHORT).show();
                        break;
                }
                item.setCheckable(false);
                drawerlayout.closeDrawers();
                return true;
            }
        });
    }

    private void removeNavigationViewScroller(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView childAt = (NavigationMenuView) navigationView.getChildAt(0);
            if (childAt != null) {
                childAt.setVerticalScrollBarEnabled(false);
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
                return;
            }
    }
}
