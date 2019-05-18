package com.wits.witssrcconnect.fragments;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MySrcActivitiesFragmentTest {

    private Context c = getTargetContext();
    @Test
    public void init() {
        getInstrumentation().runOnMainSync(()->{
            JSONArray testJsonArray = new JSONArray();
            MySrcActivitiesFragment.init(testJsonArray);
            MySrcActivitiesFragment.v = null;
            MySrcActivitiesFragment.init(testJsonArray);
        });
    }
}