package com.wits.witssrcconnect.fragments;

import org.json.JSONArray;
import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class MySrcActivitiesFragmentTest {

    @Test
    public void init() {
        try {
            runOnUiThread(()->{
                JSONArray testJsonArray = new JSONArray();
                MySrcActivitiesFragment.init(testJsonArray);
                MySrcActivitiesFragment.v = null;
                MySrcActivitiesFragment.init(testJsonArray);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}