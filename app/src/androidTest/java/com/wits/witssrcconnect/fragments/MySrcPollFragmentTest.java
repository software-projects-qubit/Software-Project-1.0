package com.wits.witssrcconnect.fragments;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.wits.witssrcconnect.R;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MySrcPollFragmentTest {

    private Context c = getTargetContext();

    @Test
    public void init() {
        getInstrumentation().runOnMainSync(()->{
            JSONArray myPolls = new JSONArray();
            MySrcPollFragment.v = View.inflate(c, R.layout.fragment_src_activity_view, null);
            MySrcPollFragment.init(myPolls);
            MySrcPollFragment.v = null;
            MySrcPollFragment.init(myPolls);
        });
    }
}