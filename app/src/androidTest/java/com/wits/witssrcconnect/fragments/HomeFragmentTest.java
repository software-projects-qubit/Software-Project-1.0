package com.wits.witssrcconnect.fragments;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void populateHomePage() {
        try {
            runOnUiThread(()->{
                LinearLayout l = new LinearLayout(c);
                l.setOrientation(LinearLayout.VERTICAL);
                SwipeRefreshLayout r = new SwipeRefreshLayout(c);

                HomeFragment.populateHomePage(l, r);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}