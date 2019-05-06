package com.wits.witssrcconnect.activities;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
        //onViewWithId(R.id.)
    }

}