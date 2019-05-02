package com.wits.witssrcconnect.managers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserManagerTest {


    private static Context c = InstrumentationRegistry.getTargetContext();//Mockito.mock(Context.class);

    @BeforeClass
    public static void initUserManagerVar(){
        UserManager.initUserManager(c);
    }

    @Test
    public void initUserManager() {
        UserManager.initUserManager(c);
        assertNotNull(UserManager.SHARED_PREFERENCES);
    }

    @Test
    public void setUserLoggedIn() {

    }

    @Test
    public void getCurrentlyLoggedInUsername() {

    }

    @Test
    public void getLoggedInUserTypeName() {
    }

    @Test
    public void getCurrentlyLoggedInStatus() {
    }

    @Test
    public void getLoggedInUserType() {
    }

    @Test
    public void getUserNameSurname() {
    }

    @Test
    public void logIn() {
    }

    @Test
    public void userLoggedOut() {
    }
}