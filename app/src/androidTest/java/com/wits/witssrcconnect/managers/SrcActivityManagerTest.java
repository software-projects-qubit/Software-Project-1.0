package com.wits.witssrcconnect.managers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;

import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
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
}