package com.wits.witssrcconnect.activities;

import android.support.test.rule.ActivityTestRule;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.utils.UserUtils;

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
        assertEquals(UserUtils.STUDENT, logInActivity.user);
    }

    @Test
    public void pressSRCMember(){
        onView(withId(R.id.member_icon_holder)).perform(click());
        assertEquals(UserUtils.SRC_MEMBER, logInActivity.user);
    }

    @Test
    public void pressAnyThenStudent(){
        onView(withId(R.id.appCompatImageView2)).perform(click());
        logInActivity.onBackPressed();
        assertEquals(UserUtils.DEFAULT_USER, logInActivity.user);
    }
}