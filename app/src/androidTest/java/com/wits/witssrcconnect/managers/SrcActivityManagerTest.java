package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.utils.ServerUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static com.wits.witssrcconnect.managers.UiManager.getDateTime;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SrcActivityManagerTest {

    private Context testContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void postActivity() {
        UserManager.initUserManager(testContext);
        getInstrumentation().runOnMainSync(() -> {
            SrcActivityManager.postActivity("title", "desc", testContext);
        });
    }

    @Test
    public void fetchAllActivities() {
        getInstrumentation().runOnMainSync(() -> {
            SrcActivityManager.fetchAllActivities(testContext, new SwipeRefreshLayout(testContext));
        });
    }

    /*@Test
    public void postComment() {
        UserManager.initUserManager(testContext);
        getInstrumentation().runOnMainSync(() -> {
            ContentValues cv = new ContentValues();
            String[] dateTime = getDateTime();
            cv.put(ServerUtils.ACTION, ServerUtils.POST_COMMENT);
            cv.put(ServerUtils.ACTIVITY_ID, 1);
            cv.put(ServerUtils.STUDENT_USERNAME, UserManager.getCurrentlyLoggedInUsername());
            cv.put(ServerUtils.STUDENT_COMMENT, "comment");
            cv.put(ServerUtils.STUDENT_ANONYMITY, ServerUtils.ANONYMOUS_COMMENT_ON);
            cv.put(ServerUtils.STUDENT_DATE, dateTime[0]);
            cv.put(ServerUtils.STUDENT_TIME, dateTime[1]);
            SrcActivityManager.postComment(cv, new TextInputEditText(testContext));
        });
    }*/

    @Test
    public void populateViewWithComments() {
        getInstrumentation().runOnMainSync(() -> {
            SrcActivityManager.populateViewWithComments(new LinearLayout(testContext), 1);
        });
    }

    @Test
    public void updateActivity() {
        getInstrumentation().runOnMainSync(() -> {
            SrcActivityManager.updateActivity(1, "titleText", "activityText", testContext);
        });
    }
}