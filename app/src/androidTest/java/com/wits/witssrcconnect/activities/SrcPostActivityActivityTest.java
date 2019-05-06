package com.wits.witssrcconnect.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SrcPostActivityActivityTest {

    @Rule
    public ActivityTestRule<SrcPostActivityActivity> srcPostActivityActivityTestRule =
            new ActivityTestRule<>(SrcPostActivityActivity.class);

    private SrcPostActivityActivity srcPostActivityActivity = null;

    @Before
    public void setUp() throws Exception{
        srcPostActivityActivity = srcPostActivityActivityTestRule.getActivity();
    }

    @Test
    public void onPostActivityTest(){
        onView(withId(R.id.src_post_activity)).perform(click());
        assertEquals("", Objects.requireNonNull(srcPostActivityActivity.title.getText()).toString());
    }

    @Test
    public void onPostActivityPressed(){
        onView(withId(R.id.src_activity_input)).perform(typeText("sample"));
        assertEquals("", Objects.requireNonNull(srcPostActivityActivity.activity.getText()).toString());
    }

}