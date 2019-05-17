package com.wits.witssrcconnect.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import com.wits.witssrcconnect.R;

import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class SrcPollFragmentTest {

    @Test
    public void init(){
        try {
            runOnUiThread(()->{
                SrcPollFragment.v.findViewById(R.id.FAB_src_poll_add).performClick();
                SwipeRefreshLayout sr = SrcPollFragment.v.findViewById(R.id.src_poll_swipe_refresh_layout);
                sr.scrollTo(2, 2);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}