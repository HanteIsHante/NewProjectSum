package com.example.hante.newprojectsum;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.hante.newprojectsum.recyclerview.RecyclerViewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 *  RecyclerView Espresso 测试
 */
 @RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewUnitTest {

    @Rule
    public IntentsTestRule<RecyclerViewActivity> mIntentsRecyclerView =
            new IntentsTestRule<RecyclerViewActivity>(RecyclerViewActivity.class);


    /*
    *     在RecyclerView 点击指定位置的 item
    *    */
    @Test
    public void testRecycler(){
        onView(withId(R.id.Recycler_View))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

    }

    /**
     * 点击指定 item 上的 view：
     *
     * 要指定唯一
     */
    @Test
    public void testRecyclerItem(){
        onView(withId(R.id.Recycler_View))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("代码家")),click()
                ));
    }
}
