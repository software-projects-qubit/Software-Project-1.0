package com.wits.witssrcconnect.activities;

import android.support.test.rule.ActivityTestRule;

import com.wits.witssrcconnect.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LogInActivityTest {

    @Rule
    public ActivityTestRule<LogInActivity> logInActivityActivityTestRule =
            new ActivityTestRule<>(LogInActivity.class);
    private LogInActivity logInActivity = null;

    @Before
    public void setUp() throws Exception{
        logInActivity = logInActivityActivityTestRule.getActivity();
    }

    @Test
    public void pressStudent(){
        onView(withId(R.id.appCompatImageView2)).perform(click());
    }

}