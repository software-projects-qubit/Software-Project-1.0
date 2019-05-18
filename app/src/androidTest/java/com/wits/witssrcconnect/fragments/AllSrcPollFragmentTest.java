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
public class AllSrcPollFragmentTest {

    private Context c = getTargetContext();
    @Test
    public void init() {
        getInstrumentation().runOnMainSync(()->{
            JSONArray polls = new JSONArray();
            AllSrcPollFragment.v = View.inflate(c, R.layout.fragment_src_activity_view, null);
            AllSrcPollFragment.init(polls);
            AllSrcPollFragment.v = null;
            AllSrcPollFragment.init(polls);
        });
    }
}