package com.wits.witssrcconnect.fragments;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.wits.witssrcconnect.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SrcMemberActivitiesFragmentTest {

    @Test
    public void buttonClicksTest(){
        try {
            runOnUiThread(()->{
                SrcMemberActivitiesFragment.v.findViewById(R.id.FAB_src_activity_add).performClick();
                SwipeRefreshLayout sr = SrcMemberActivitiesFragment.v.findViewById(R.id.src_activities_swipe_refresh_layout);
                sr.scrollTo(2, 2);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}