package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;

import com.wits.witssrcconnect.utils.ServerUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SrcPollManagerTest {

    private Context testContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void postPoll() {
        UserManager.initUserManager(testContext);
        getInstrumentation().runOnMainSync(() -> {
            ContentValues cv = new ContentValues();
            cv.put(ServerUtils.ACTION, ServerUtils.POST_POLL);
            cv.put(ServerUtils.SRC_USERNAME, UserManager.getCurrentlyLoggedInUsername());
            cv.put(ServerUtils.POLL_TITLE, "title");
            cv.put(ServerUtils.POLL_DESC, "desc");
            cv.put(ServerUtils.POLL_CHOICE, "1");
            cv.put(ServerUtils.POLL_TYPE, 1);
            cv.put(ServerUtils.POLL_DATE, "01/01/2019");
            cv.put(ServerUtils.POLL_TIME, "00:00");

            SrcPollManager.postPoll(cv, testContext);
        });
    }

    /*@Test
    public void fetchAllPolls() {
        getInstrumentation().runOnMainSync(() -> {
            SrcPollManager.fetchAllPolls(testContext, null //, new SwipeRefreshLayout(testContext)
            );
        });
    }*/
}