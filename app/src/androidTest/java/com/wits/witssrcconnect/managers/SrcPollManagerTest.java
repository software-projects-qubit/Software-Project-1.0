package com.wits.witssrcconnect.managers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.v4.widget.SwipeRefreshLayout;

import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;

public class SrcPollManagerTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void handlePostPollFeedBack() {
        try {
            runOnUiThread(()->{
                SrcPollManager.handlePostPollFeedBack(ServerUtils.SUCCESS, c);
                SrcPollManager.handlePostPollFeedBack(ServerUtils.FAILED, c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void handleFetchAllPollsFeedBack() {
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                UserManager.setUserLoggedIn(anyInt(), "srcpresident");
                try {
                    JSONArray polls = new JSONArray();
                    JSONObject poll0 = new JSONObject();
                    JSONObject poll1 = new JSONObject();
                    poll0.put(ServerUtils.SRC_USERNAME, "srcpresident");
                    poll1.put(ServerUtils.SRC_USERNAME, "some user");
                    polls.put(poll0);
                    polls.put(poll1);
                    SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(c);
                    SrcPollManager.handleFetchAllPollsFeedBack("", c, swipeRefreshLayout);
                    SrcPollManager.handleFetchAllPollsFeedBack("[]", c, swipeRefreshLayout);
                    SrcPollManager.handleFetchAllPollsFeedBack("json error", c, swipeRefreshLayout);
                    SrcPollManager.handleFetchAllPollsFeedBack(polls.toString(), c, swipeRefreshLayout);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}