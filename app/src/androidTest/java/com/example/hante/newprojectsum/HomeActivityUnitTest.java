package com.example.hante.newprojectsum;

import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

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
        // 顶部点击
        onView(withId(R.id.user_photo))
                .perform(click());
        // 检验是否跳转
        onView(withId(R.id.user_titlebar))
                .check(matches(isDisplayed()));
        //返回
        pressBack();
        Thread.sleep(1500);

        // menu 获取Item  点击
        onView(withId(R.id.drawerlayout))
                .check(matches(isClosed(Gravity.START)))
                .perform(DrawerActions.open());
        onView(withId(R.id.design_navigation_view))
                .perform(navigateTo(R.id.moments));
        Thread.sleep(1500);



    }


    @After
    public void unRegisterResource(){

    }

    public static ViewAction navigateTo(final int menuItemId) {

        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                NavigationView navigationView = (NavigationView) view;
                Menu menu = navigationView.getMenu();
                if (null == menu.findItem(menuItemId)) {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(new RuntimeException(getErrorMessage(menu, view)))
                            .build();
                }
                menu.performIdentifierAction(menuItemId, 0);
                uiController.loopMainThreadUntilIdle();
            }

            private String getErrorMessage(Menu menu, View view) {
                String NEW_LINE = System.getProperty("line.separator");
                StringBuilder errorMessage = new StringBuilder("Menu item was not found, "
                        + "available menu items:")
                        .append(NEW_LINE);
                for (int position = 0; position < menu.size(); position++) {
                    errorMessage.append("[MenuItem] position=")
                            .append(position);
                    MenuItem menuItem = menu.getItem(position);
                    if (menuItem != null) {
                        CharSequence itemTitle = menuItem.getTitle();
                        if (itemTitle != null) {
                            errorMessage.append(", title=")
                                    .append(itemTitle);
                        }
                        if (view.getResources() != null) {
                            int itemId = menuItem.getItemId();
                            try {
                                errorMessage.append(", id=");
                                String menuItemResourceName = view.getResources()
                                        .getResourceName(itemId);
                                errorMessage.append(menuItemResourceName);
                            } catch (Resources.NotFoundException nfe) {
                                errorMessage.append("not found");
                            }
                        }
                        errorMessage.append(NEW_LINE);
                    }
                }
                return errorMessage.toString();
            }

            @Override
            public String getDescription() {
                return "click on menu item with id";
            }

            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(NavigationView.class),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        isDisplayingAtLeast(90)
                );
            }
        };

    }
}
