package com.example.hante.newprojectsum;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.hante.newprojectsum.activitys.WebViewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;

/**
 * WebView Espresso测试
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class WebViewUnitTest {


    @Rule
    public IntentsTestRule<WebViewActivity> mWebViewTestRule =
            new IntentsTestRule<WebViewActivity>(WebViewActivity.class,false,false){
                @Override
                protected void afterActivityFinished () {
                    super.afterActivityFinished();
                    //Javascript能通过Espresso web 操作webview:
//                    afterActivityLaunched();
                    // Enable JS!
                    onWebView().forceJavascriptEnabled();
                }
            };



    @Test
    public void testWebView(){
        //开启WebActivity
        mWebViewTestRule.launchActivity(new Intent());

        onWebView() .withElement(findElement(Locator.LINK_TEXT,"_blank"))
                .perform(webClick());//执行点击动作

    }

}
