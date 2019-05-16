package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class UserManager2Test {
    private Context c = InstrumentationRegistry.getTargetContext();


    @Test
    public void userLoggedOut(){
        try {
            runOnUiThread(()->{
                UserManager.SHARED_PREFERENCES = null;
                UserManager.userLoggedOut(c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void logInAsSrcMemberSuccess(){
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                ContentValues cv = new ContentValues();
                cv.put(ServerUtils.SRC_USERNAME, anyString());
                UserManager.handleLogin(UserUtils.SRC_MEMBER, ServerUtils.SUCCESS, c, cv);
                UserManager.userLoggedOut(c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void logInAsSrcMemberFailed(){
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                ContentValues cv = new ContentValues();
                UserManager.handleLogin(UserUtils.SRC_MEMBER, ServerUtils.FAILED, c, cv);
                UserManager.userLoggedOut(c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void logInAsStudentFailed0(){
        try {
            runOnUiThread(()->{
                ContentValues cv = new ContentValues();
                UserManager.handleLogin(UserUtils.STUDENT, "{}", c, cv);
                UserManager.userLoggedOut(c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void logInAsStudentFailed1(){
        try {
            runOnUiThread(()->{
                ContentValues cv = new ContentValues();
                UserManager.handleLogin(UserUtils.STUDENT, ServerUtils.FAILED, c, cv);
                UserManager.userLoggedOut(c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void logInAsStudentSuccess(){
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                ContentValues cv = new ContentValues();
                cv.put(ServerUtils.USERNAME, anyString());
                JSONObject testJson = new JSONObject();
                try {
                    testJson.put(ServerUtils.NAME, anyString());
                    testJson.put(ServerUtils.SURNAME, anyString());
                    UserManager.handleLogin(UserUtils.STUDENT, testJson.toString(), c, cv);
                    UserManager.userLoggedOut(c);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
