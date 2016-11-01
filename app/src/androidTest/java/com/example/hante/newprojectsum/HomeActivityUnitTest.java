package com.example.hante.newprojectsum;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * 首页实现UI测试
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityUnitTest {
    /**
     * 规则
     */
    @Rule
    public IntentsTestRule<NewProjectSumHomeActivity>  mAddTaskIntentsTestRule =
            new IntentsTestRule<>(NewProjectSumHomeActivity.class);

    @Before
    public void registerResource(){

    }


    @Test
    public void textClickPerform() throws InterruptedException {
        // 点击 打开DrawerLayout
        onView(withId(R.id.drawerlayout))
            .check(matches(isClosed(Gravity.START))) // Left Drawer should be closed.
            .perform(DrawerActions.open());
        // 顶部头像点击
        onView(withId(R.id.user_photo))
                .perform(click());
        // 检验是否跳转
        onView(withId(R.id.user_titlebar))
                .check(matches(isDisplayed()));
        //返回
        Espresso.pressBack();
        Thread.sleep(1500);

         /*
         menu 获取Item  点击
         注意：ID 资源一定要唯一，否则报错
         error：
         at com.example.hante.newprojectsum.HomeActivityUnitTest.textClickPerform
         (HomeActivityUnitTest.java:70)
         */
        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START)))
                .perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view_home)).check(matches(isEnabled()))
                .perform(navigateTo(R.id.moments));
        Thread.sleep(1500);
        Espresso.pressBack();// 返回

        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START)))
                .perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view_home)).check(matches(isEnabled()))
                .perform(navigateTo(R.id.bottomSheetActivity));
        Espresso.pressBack();
        Thread.sleep(1500);

        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START))).perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view_home)).check(matches(isEnabled()))
                .perform(navigateTo(R.id.Service));
        Espresso.pressBack();
        Thread.sleep(1500);

        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START))).perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view_home)).check(matches(isEnabled()))
                .perform(navigateTo(R.id.NotifyService));
        Thread.sleep(1500);

        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START))).perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view_home)).check(matches(isEnabled()))
                .perform(navigateTo(R.id.TextInput_Layout));
        Espresso.pressBack();
        Thread.sleep(1500);

        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START))).perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view_home)).check(matches(isEnabled()))
                .perform(navigateTo(R.id.Recycler_View));
        Espresso.pressBack();
        Thread.sleep(1500);

        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START))).perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view_home)).check(matches(isEnabled()))
                .perform(navigateTo(R.id.tab_layout));
        Espresso.pressBack();
        Thread.sleep(1500);
    }

    @Test
    public void drawerMenuButton(){
         /*
          底部 1.设置 2.退出应用
          */
        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START)))
                .perform(DrawerActions.open());
        onView(withId(R.id.setting))
                .perform(click());
        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START)))
                .perform(DrawerActions.open());
        onView(withId(R.id.exit_app))
                .perform(click());
    }

    @After
    public void unRegisterResource(){

    }
}
