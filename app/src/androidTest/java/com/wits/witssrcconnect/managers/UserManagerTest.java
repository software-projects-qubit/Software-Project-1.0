package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class UserManagerTest {

    private static Context c = InstrumentationRegistry.getTargetContext();

    @BeforeClass
    public static void setUp(){
        UserManager.initUserManager(c);
    }

    @Test
    public void initUserManager() {
        UserManager.initUserManager(c);
        assertNotNull(UserManager.SHARED_PREFERENCES);
    }

    @Test
    public void setUserLoggedIn() {
        UserManager.setUserLoggedIn(UserUtils.STUDENT, anyString());
        assertEquals(UserUtils.STUDENT, UserManager.getLoggedInUserType());
    }

    @Test
    public void getCurrentlyLoggedInUsername() {
        String name = anyString();
        UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, name);
        assertEquals(name, UserManager.getCurrentlyLoggedInUsername());
    }

    @Test
    public void getLoggedInUserTypeName() {
        UserManager.userLoggedOut(c);
        assertEquals("", UserManager.getLoggedInUserTypeName(c));
    }

    @Test
    public void getLoggedInUserTypeNameStudent() {
        UserManager.setUserLoggedIn(UserUtils.STUDENT, anyString());
        assertEquals(c.getResources().getString(R.string.student), UserManager.getLoggedInUserTypeName(c));
    }

    @Test
    public void getLoggedInUserTypeNameMember() {
        UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, anyString());
        assertEquals(c.getResources().getString(R.string.src_member), UserManager.getLoggedInUserTypeName(c));
    }

    @Test
    public void getCurrentlyLoggedInStatus() {
        UserManager.userLoggedOut(c);
        assertFalse(UserManager.getCurrentlyLoggedInStatus());
    }

    @Test
    public void getLoggedInUserType() {
        UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, anyString());
        assertEquals(UserUtils.SRC_MEMBER, UserManager.getLoggedInUserType());
    }

    @Test
    public void setUserNameSurname() {
        String n = anyString();
        String s = anyString();
        UserManager.setUserNameSurname(n, s);
        String ns = UserManager.getUserNameSurname();
        assertTrue(!TextUtils.isEmpty(ns));
    }

    @Test
    public void getUserNameSurname() {
        assertEquals("", UserManager.getUserNameSurname());
    }

    @Test
    public void logInCorrectCredentialsStudent(){
        getInstrumentation().runOnMainSync(() -> {
            ContentValues cv = new ContentValues();
            cv.put(ServerUtils.USERNAME, "1627982");
            cv.put(ServerUtils.PASSWORD, "mulisa6854727");
            UserManager.logIn(UserUtils.STUDENT, cv, ServerUtils.LOG_IN_LINK, c);
        });
    }

    @Test
    public void logInCorrectCredentialsSRCMember(){
        getInstrumentation().runOnMainSync(() -> {
            ContentValues cv = new ContentValues();
            cv.put(ServerUtils.ACTION, ServerUtils.LOG_IN);
            cv.put(ServerUtils.SRC_USERNAME, "srcpresident");
            cv.put(ServerUtils.SRC_PASSWORD, "12345");
            UserManager.logIn(UserUtils.SRC_MEMBER, cv, ServerUtils.SRC_MEMBER_LINK, c);
        });
    }

    @Test
    public void logInInCorrect() {
        getInstrumentation().runOnMainSync(()->{
            UserManager.logIn(UserUtils.DEFAULT_USER, new ContentValues(), "", c);
        });
    }

    @Test
    public void userLoggedOut() {
        UserManager.userLoggedOut(c);
        assertEquals("", UserManager.getUserNameSurname());
    }
}