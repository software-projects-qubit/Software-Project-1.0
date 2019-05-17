package com.wits.witssrcconnect.fragments;

import org.json.JSONArray;
import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class StudentViewSrcActivitiesFragmentTest {

    @Test
    public void init() {
        try {
            runOnUiThread(()->{
                JSONArray activities = new JSONArray();
                StudentViewSrcActivitiesFragment.init(activities);
                StudentViewSrcActivitiesFragment.v = null;
                StudentViewSrcActivitiesFragment.init(activities);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}