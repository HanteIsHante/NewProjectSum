package com.example.hante.newprojectsum;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.hante.newprojectsum.textinputactivity.TextInputLayoutActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * TextInput Espresso 测试
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TextInputActivityUnitTest {

    private String mPhone = "13120102016";
    private String mPass = "123456";
    private String mEmail = "13051552016@163.com";
    @Rule
    public IntentsTestRule<TextInputLayoutActivity> mTextInputLayActivity =
                new IntentsTestRule<TextInputLayoutActivity>(TextInputLayoutActivity.class);


    @Test
    public void testInputEdit(){
//        该控件输入 文字,检查是否匹配
        onView(withId(R.id.et_phone)).perform(typeText(mPhone), ViewActions.closeSoftKeyboard())
                 .check(matches(withText(mPhone)));
        onView(withId(R.id.et_password)).perform(typeText(mPass), ViewActions.closeSoftKeyboard())
                .check(matches(withText(mPass)));
        onView(withId(R.id.et_email)).perform(typeText(mEmail),ViewActions.closeSoftKeyboard())
                .check(matches(withText(mEmail)));
        // 点击click
        onView(withId(R.id.toolbar_textInputLayout)).perform(click());
    }
}
