package com.wits.witssrcconnect.fragments;

import org.json.JSONArray;
import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class MySrcPollFragmentTest {

    @Test
    public void init() {
        try {
            runOnUiThread(()->{
                JSONArray myPolls = new JSONArray();
                MySrcPollFragment.init(myPolls);
                MySrcPollFragment.v = null;
                MySrcPollFragment.init(myPolls);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}