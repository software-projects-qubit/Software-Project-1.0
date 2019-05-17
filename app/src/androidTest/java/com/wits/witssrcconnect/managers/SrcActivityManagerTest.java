package com.wits.witssrcconnect.managers;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class SrcActivityManagerTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void fetchAllActivitiesSrcMember(){
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, anyString());
                SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(c);
                SrcActivityManager.fetchAllActivities(c, swipeRefreshLayout);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void fetchAllActivitiesStudent(){
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                UserManager.setUserLoggedIn(UserUtils.STUDENT, anyString());
                SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(c);
                SrcActivityManager.fetchAllActivities(c, swipeRefreshLayout);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void handlePostActivityFeedback() {
        try {
            runOnUiThread(()->{
                SrcActivityManager.handlePostActivityFeedback(ServerUtils.FAILED, c);
                SrcActivityManager.handlePostActivityFeedback(ServerUtils.SUCCESS, c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void handleFetchAllActivitiesFeedback() {
        try {
            runOnUiThread(()->{
                SwipeRefreshLayout sr = new SwipeRefreshLayout(c);
                SrcActivityManager.handleFetchAllActivitiesFeedback("", sr, c);
                SrcActivityManager.handleFetchAllActivitiesFeedback("[]", sr, c);
                SrcActivityManager.handleFetchAllActivitiesFeedback("exeception", sr, c);

                try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj1 = new JSONObject();
                    obj1.put(ServerUtils.SRC_USERNAME, "srcpresident");
                    JSONObject obj2 = new JSONObject();
                    obj2.put(ServerUtils.SRC_USERNAME, "anyone");
                    jsonArray.put(obj1).put(obj2);
                    UserManager.initUserManager(c);
                    UserManager.setUserLoggedIn(UserUtils.STUDENT, anyString());
                    SrcActivityManager.handleFetchAllActivitiesFeedback(jsonArray.toString(), sr, c);
                    UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, "srcpresident");
                    SrcActivityManager.handleFetchAllActivitiesFeedback(jsonArray.toString(), sr, c);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void handleCommentFeedback() {
        try {
            runOnUiThread(()->{
                TextInputEditText comment = new TextInputEditText(c);
                SrcActivityManager.handleCommentFeedback(ServerUtils.FAILED, comment);
                SrcActivityManager.handleCommentFeedback(ServerUtils.SUCCESS, comment);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void handleUpdateActivity() {
        try {
            runOnUiThread(()->{
                SrcActivityManager.handleUpdateActivity(ServerUtils.SUCCESS, c);
                SrcActivityManager.handleUpdateActivity(ServerUtils.FAILED, c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void handleCommentFeedback1() {
        try {
            runOnUiThread(()->{
                try {
                    LinearLayout l = new LinearLayout(c);
                    l.setOrientation(LinearLayout.VERTICAL);
                    SrcActivityManager.handlePopulateViewWithCommentsFeedback(l, "", c);
                    SrcActivityManager.handlePopulateViewWithCommentsFeedback(l, "[]", c);
                    SrcActivityManager.handlePopulateViewWithCommentsFeedback(l, "exeception", c);
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj1 = new JSONObject();
                    JSONObject obj2 = new JSONObject();

                    obj1.put(ServerUtils.STUDENT_ANONYMITY, ServerUtils.ANONYMOUS_COMMENT_ON);
                    obj2.put(ServerUtils.STUDENT_ANONYMITY, ServerUtils.ANONYMOUS_COMMENT_OFF);
                    obj1.put(ServerUtils.STUDENT_DATE, anyString());
                    obj1.put(ServerUtils.STUDENT_TIME, anyString());
                    obj2.put(ServerUtils.STUDENT_DATE, anyString());
                    obj2.put(ServerUtils.STUDENT_TIME, anyString());
                    obj1.put(ServerUtils.STUDENT_COMMENT, anyString());
                    obj2.put(ServerUtils.STUDENT_COMMENT, anyString());

                    jsonArray.put(obj1);
                    jsonArray.put(obj2);
                    SrcActivityManager.handlePopulateViewWithCommentsFeedback(l, jsonArray.toString(), c);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void populateViewWithComments() {
        try {
            runOnUiThread(()->{
                LinearLayout l = new LinearLayout(c);
                SrcActivityManager.populateViewWithComments(l, anyInt());
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}