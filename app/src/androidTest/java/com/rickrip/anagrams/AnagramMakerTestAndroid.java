package com.rickrip.anagrams;

import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;

import org.hamcrest.Matcher;
import org.jetbrains.annotations.Contract;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.android.material.tabs.TabLayout;
import com.squareup.spoon.SpoonRule;

import java.io.File;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

public class AnagramMakerTestAndroid {

    @Rule
    public final SpoonRule spoon = new SpoonRule();

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    String defaultPatternStr = "0123456789`~!@#$%^*()_-+=>|,.>\\/?':;{[]}№abcC";
    //0123456789`~!@#$%^*()_-+=>|,.>\/?':;{[]}№abcC

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void spoonTest() {

        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity>() {

            @Override
            public void perform(MainActivity activity) {
                //  spoon.screenshot(activity, "baseIgnorCharsTest");
            }
        });

    }

    @Test
    public void test(){

        onView(withId(R.id.tv_label_help))
                .check(matches(withText(R.string.tv_label_help_text_one)));

        onView(withId(R.id.et_main_one)).perform(typeText(" hello  world #1  "));
        onView(withId(R.id.et_main_one))
                .check(matches(withText(" hello  world #1  ")));

        onView(withId(R.id.btn_convert)).perform(click());
        onView(withId(R.id.tv_label_out))
                .check(matches(withText("olleh dlrow #1 ")));

        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1));

        onView(isRoot()).perform(waitFor(2000));

        onView(withId(R.id.et_main_two)).perform(typeText(" abcC 123"));
        onView(withId(R.id.et_main_two))
                .check(matches(withText(String.valueOf(defaultPatternStr))));

        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0));

        onView(withId(R.id.tv_label_help2))
                .check(matches(withText(R.string.tv_label_help_text_two)));

        onView(withId(R.id.et_main_one)).perform(ViewActions.clearText());
        onView(withId(R.id.et_main_one))
                .check(matches(withText("")));

        onView(withId(R.id.et_main_one)).perform(typeText("abQWc eCrTy"));
        onView(withId(R.id.et_main_one))
                .check(matches(withText("abQWc eCrTy")));

        onView(withId(R.id.btn_convert)).perform(click());
        onView(withId(R.id.tv_label_out))
                .check(matches(withText("abWQc yCTre ")));

    }

    @NonNull
    private static ViewAction selectTabAtPosition(final int position) {

        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
            }

            @Override
            public String getDescription() {
                return "with tab at index" + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof TabLayout) {
                    TabLayout tabLayout = (TabLayout) view;
                    TabLayout.Tab tab = tabLayout.getTabAt(position);

                    if (tab != null) {
                        tab.select();
                    }
                }
            }
        };
    }

    @NonNull
    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return ViewMatchers.isRoot();
            }

            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }

            @Override public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }




}