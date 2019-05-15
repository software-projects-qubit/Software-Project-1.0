package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

@RunWith(AndroidJUnit4.class)
public class UserManager2 {
    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void userLoggedOut(){
        UserManager.userLoggedOut(c);
    }

    @Test
    public void logInAsSrcMember(){
        try {
            runOnUiThread(()->{
                int user = UserUtils.SRC_MEMBER;
                ContentValues cv = new ContentValues();
                cv.put(ServerUtils.ACTION, ServerUtils.LOG_IN);
                cv.put(ServerUtils.SRC_USERNAME, "srcpresident");
                cv.put(ServerUtils.SRC_PASSWORD, "12345");
                String link = ServerUtils.SRC_MEMBER_LINK;
                UserManager.logIn(user, cv, link, c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void logInAsStudent(){
        try {
            runOnUiThread(()->{
                int user = UserUtils.STUDENT;
                ContentValues cv = new ContentValues();
                cv.put(ServerUtils.ACTION, ServerUtils.LOG_IN);
                cv.put(ServerUtils.SRC_USERNAME, "1627982");
                cv.put(ServerUtils.SRC_PASSWORD, "mulisa6854727");
                String link = ServerUtils.LOG_IN_LINK;
                UserManager.logIn(user, cv, link, c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
