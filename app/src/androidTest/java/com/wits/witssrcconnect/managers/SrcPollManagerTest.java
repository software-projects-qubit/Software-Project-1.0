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
    public void handlePostPollFeedBackSuccess(){
        try {
            runOnUiThread(()-> SrcPollManager.handlePostPollFeedBack(ServerUtils.SUCCESS, c));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    @Test
    public void handlePostPollFeedBackFailed() {
        try {
            runOnUiThread(()-> SrcPollManager.handlePostPollFeedBack(ServerUtils.FAILED, c));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void handleFetchAllPollsFeedBack() {
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                SwipeRefreshLayout sr = new SwipeRefreshLayout(c);
                SrcPollManager.handleFetchAllPollsFeedBack("", c, sr);
                SrcPollManager.handleFetchAllPollsFeedBack("[]", c, sr);
                ////////////////////////////////////////////////////////////////////////////////////
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}