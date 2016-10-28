package com.example.hante.newprojectsum;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hante.newprojectsum.activitys.BottomSheetActivity;
import com.example.hante.newprojectsum.activitys.UserInfomationActivity;
import com.example.hante.newprojectsum.activitys.WebViewActivity;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.recyclerview.RecyclerViewActivity;
import com.example.hante.newprojectsum.service.ForegroundNotificationService;
import com.example.hante.newprojectsum.serviceactivity.ServiceActivity;
import com.example.hante.newprojectsum.tablelayout.TabLayoutActivity;
import com.example.hante.newprojectsum.textinputactivity.TextInputLayoutActivity;
import com.example.hante.newprojectsum.util.GlideCircleTransform;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewProjectSumHomeActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.design_navigation_view)
    NavigationView designNavigationView;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @Bind(R.id.toolbar)
    TitleBar toolbar;
    @Bind(R.id.exit_app)
    Button mExitApp;

    private Context mContext;
    private ImageView viewById;
    private ImageView backgroundImg;// 用户背景
    private ActionBarDrawerToggle drawerToggle;

    private boolean ifNotify = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_sum_home);
        ButterKnife.bind(this);
        mContext = this;
        navViewToTop();
        initUI();
        setData();
        newAnimationDesign();
    }


    private void setData () {
        String url = "http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg";
        Glide.with(mContext).load(url).transform(new GlideCircleTransform(mContext)).into(viewById);
    }

    private void navViewToTop () {
//        当系统版本小于5.0时，进行如下设置：
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawerlayout.setFitsSystemWindows(true);
            drawerlayout.setClipToPadding(false);
        }
    }

    private void initUI () {
        // 得到 顶部头像 Image
        View headerView = designNavigationView.getHeaderView(0);
        backgroundImg = (ImageView) headerView.findViewById(R.id.nav_head_background_img);
        viewById = (ImageView) headerView.findViewById(R.id.user_photo);
        viewById.setOnClickListener(this);
        designNavigationView.setItemIconTintList(null);// 图片本身颜色，不是统一颜色
        MenuItem menuItem = designNavigationView.getMenu().findItem(R.id.moments);
//        menuItem.setVisible(false);//true  显示 false 隐藏
        removeNavigationViewScroller(designNavigationView);  //去掉侧边滚动条
        clickOutNavigationViewClose();// 点击侧边框外部，关闭NavigationView
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 禁止显示项目名
        toolbar.setMyCenterTitle(R.string.app);
        drawerToggle = setDrawerToggle();
        drawerlayout.addDrawerListener(drawerToggle);
        mExitApp.setOnClickListener(this);
    }

    private ActionBarDrawerToggle setDrawerToggle () {
        return new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
    }

    @Override
    protected void onPostCreate (@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged (Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clickOutNavigationViewClose () {
        designNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item) {

                switch(item.getItemId()) {
                    case R.id.moments:
                        Intent intent = new Intent(getApplicationContext(),
                                WebViewActivity.class);
                        intent.putExtra("url",
                                "http://www.jianshu.com/users/7203ddebbbbb/latest_articles");
                        intent.putExtra("title", "简书");
                        startActivity(intent);
                        break;
                    case R.id.wechat:
                        Intent iBottomSheet = new Intent(getApplicationContext(),
                                BottomSheetActivity.class);
                        startActivity(iBottomSheet);
                        break;
                    case R.id.Service:
                        Intent iService = new Intent(getApplicationContext(),
                                ServiceActivity.class);
                        startActivity(iService);
                        break;
                    case R.id.NotifyService:
                        Intent iNotifyService = new Intent(getApplicationContext(),
                                ForegroundNotificationService.class);
                        if(!ifNotify) {
                            iNotifyService.putExtra("ifClose", 0);
                            iNotifyService.putExtra("title", "HanTe");
                            iNotifyService.putExtra("content", "This Is First Notification");
                            startService(iNotifyService);
                            ifNotify = true;
                        } else {
                            iNotifyService.putExtra("ifClose", 1);
                            startService(iNotifyService);
                            ifNotify = false;
                        }
                        break;
                    case R.id.TextInput_Layout:
                        Intent iTextInputLayout = new Intent(getApplicationContext(),
                                TextInputLayoutActivity.class);
                        startActivity(iTextInputLayout);
                        break;
                    case R.id.Recycler_View:
                        Intent iRecyclerView = new Intent(getApplicationContext(),
                                RecyclerViewActivity.class);
                        startActivity(iRecyclerView);
                        break;
                    case R.id.tab_layout:
                        Intent iTab = new Intent(getApplicationContext(),
                                TabLayoutActivity.class);
                        startActivity(iTab);
                }
                item.setCheckable(false);
                drawerlayout.closeDrawers();
                return true;
            }
        });
    }

    private void removeNavigationViewScroller (NavigationView navigationView) {
        if(navigationView != null) {
            NavigationMenuView childAt = (NavigationMenuView) navigationView.getChildAt(0);
            if(childAt != null) {
                childAt.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    public void onBackPressed () {
        if(drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            return;
        }
    }

    private void newAnimationDesign () {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = (Fade) TransitionInflater.from(NewProjectSumHomeActivity.this).inflateTransition
                    (R.transition.fade);
            getWindow().setEnterTransition(fade);
            Fade explode = new Fade();
            explode.setDuration(1000);
            getWindow().setReturnTransition(explode);
        }
    }

    @Override
    public void onClick (View view) {
        if(view == viewById) {
            Intent t = new Intent(getApplicationContext(), UserInfomationActivity.class);
            startActivity(t);
        } else if(view == mExitApp){
            finish();
            Toast.makeText(mContext, "退出应用", Toast.LENGTH_SHORT).show();
        }
    }
}
