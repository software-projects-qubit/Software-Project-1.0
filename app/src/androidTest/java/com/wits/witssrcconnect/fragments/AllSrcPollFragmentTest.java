package com.wits.witssrcconnect.fragments;

import org.json.JSONArray;
import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class AllSrcPollFragmentTest {

    @Test
    public void init() {
        try {
            runOnUiThread(()->{
                JSONArray polls = new JSONArray();
                AllSrcPollFragment.init(polls);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}