package com.wits.witssrcconnect.activities;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.espresso.ViewInteraction;

import com.wits.witssrcconnect.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LogInActivityTest {

    @Rule
    public ActivityTestRule<LogInActivity> activityTest = new ActivityTestRule<>(LogInActivity.class);

    @Test
    public void test() {
        ViewInteraction userNameInt = onView(withId(R.id.username));
        userNameInt.check(matches(isDisplayed()));
        ViewInteraction passInt = onView(withId(R.id.pass));
        passInt.check(matches(isDisplayed()));
        ViewInteraction cc1Int = onView(withId(R.id.cc1));
        ViewInteraction cc2Int = onView(withId(R.id.cc2));
    }
}