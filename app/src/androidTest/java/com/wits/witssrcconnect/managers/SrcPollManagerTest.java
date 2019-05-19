package com.wits.witssrcconnect.managers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(AndroidJUnit4.class)
public class SrcPollManagerTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void handlePostPollFeedBack(){
        getInstrumentation().runOnMainSync(()->{
            SrcPollManager.handlePostPollFeedBack(ServerUtils.SUCCESS, c);
            SrcPollManager.handlePostPollFeedBack(ServerUtils.FAILED, c);
        });
    }

    @Test
    public void handleFetchAllPollsFeedBack() {
        getInstrumentation().runOnMainSync(()->{
            UserManager.initUserManager(c);
            SwipeRefreshLayout sr = new SwipeRefreshLayout(c);
            SrcPollManager.handleFetchAllPollsFeedBack("", c, sr);
            SrcPollManager.handleFetchAllPollsFeedBack("[]", c, sr);
            SrcPollManager.handleFetchAllPollsFeedBack("something", c, sr);
            UserManager.setUserLoggedIn(anyInt(), "srcpresident");
            JSONArray polls = new JSONArray();
            JSONObject obj1 = new JSONObject();
            JSONObject obj2 = new JSONObject();
            try {
                obj1.put(ServerUtils.SRC_USERNAME, "srcpresident");
                obj2.put(ServerUtils.SRC_USERNAME, "other");
                polls.put(obj1);
                polls.put(obj2);
                SrcPollManager.handleFetchAllPollsFeedBack(polls.toString(), c, null);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ////////////////////////////////////////////////////////////////////////////////////
        });
    }
}