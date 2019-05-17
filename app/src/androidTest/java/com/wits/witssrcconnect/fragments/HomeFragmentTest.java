package com.wits.witssrcconnect.fragments;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

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

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(ServerUtils.MISSION, anyString());
                    jsonObject.put(ServerUtils.VISION, anyString());
                    jsonObject.put(ServerUtils.CONTACT_US, anyString());
                    HomeFragment.addViewsToLayout(jsonObject, l, r);
                    r = null;
                    HomeFragment.addViewsToLayout(jsonObject, l, r);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}