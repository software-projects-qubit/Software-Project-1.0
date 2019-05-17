package com.wits.witssrcconnect.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import com.wits.witssrcconnect.R;

import org.junit.Test;

import static org.junit.Assert.*;

public class SrcMemberActivitiesFragmentTest {

    @Test
    public void buttonClicksTest(){
        SrcMemberActivitiesFragment.v.findViewById(R.id.FAB_src_activity_add).performClick();
        SwipeRefreshLayout sr = SrcMemberActivitiesFragment.v.findViewById(R.id.src_activities_swipe_refresh_layout);
        sr.scrollTo(2, 2);
    }
}