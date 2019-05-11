package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.R;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LogInActivityTest {

    private static Context c = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<LogInActivity> activityTestRule = new ActivityTestRule<>(LogInActivity.class);

    @Test
    public void studentPressed(){
        onView(withId(R.id.appCompatImageView2)).perform(click());
    }

    @Test
    public void srcMemberPressed(){
        onView(withId(R.id.member_icon_holder)).perform(click());
    }

    @Test
    public void buttonPressedThenBackPressed(){
        try {
            runOnUiThread(() ->{
                activityTestRule.getActivity().findViewById(R.id.appCompatImageView2).performClick();
                activityTestRule.getActivity().onBackPressed();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void backPressed(){

        try {
            runOnUiThread(() -> activityTestRule.getActivity().onBackPressed());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}