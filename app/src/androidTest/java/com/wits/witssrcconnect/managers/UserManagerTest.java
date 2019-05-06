package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class UserManagerTest {

    private static Context testContext = InstrumentationRegistry.getTargetContext();

    @BeforeClass
    public static void initUserManagerVar() {
        UserManager.initUserManager(testContext);
    }

    @Test
    public void initUserManager() {
        UserManager.initUserManager(testContext);
        assertNotNull(UserManager.SHARED_PREFERENCES);
    }

    /*@Test
    public void setUserLoggedIn() {
        int i = anyInt();
        UserManager.setUserLoggedIn(i, anyString());
        assertEquals(UserManager.getLoggedInUserType(), i);
    }*/

    @Test
    public void getCurrentlyLoggedInUsername() {
        String userName = anyString();
        UserManager.setUserLoggedIn(anyInt(), userName);
        assertEquals(userName, UserManager.getCurrentlyLoggedInUsername());
    }

    @Test
    public void getLoggedInUserTypeName() {
        UserManager.setUserLoggedIn(UserUtils.STUDENT, anyString());
        assertEquals(UserManager.getLoggedInUserTypeName(testContext),
                testContext.getResources().getString(R.string.student));
    }

    @Test
    public void getCurrentlyLoggedInStatus() {
        UserManager.setUserLoggedIn(anyInt(), anyString());
        assertTrue(UserManager.getCurrentlyLoggedInStatus());
    }

    @Test
    public void getLoggedInUserType() {
        int userType = anyInt();
        UserManager.setUserLoggedIn(userType, anyString());
        assertEquals(UserManager.getLoggedInUserType(), userType);
    }

    @Test
    public void getUserNameSurname() {
        String name = anyString();
        String surname = anyString();
        String nameSurname = String.format("%s %s", name, surname);
        UserManager.setUserNameSurname(name, surname);
        assertEquals(nameSurname, UserManager.getUserNameSurname());
    }

    @Test
    public void login() {
        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.USERNAME, "1627982");
        cv.put(ServerUtils.PASSWORD, "mulisa6854727");

        getInstrumentation().runOnMainSync(() -> {
            UserManager.logIn(UserUtils.STUDENT, cv, ServerUtils.LOG_IN_LINK, testContext);
            assertEquals(UserManager.getLoggedInUserType(), UserUtils.STUDENT);
        });
    }

    @Test
    public void userLoggedOut() {
        UserManager.userLoggedOut(testContext);
        String currentlyLoggedInUsername = UserManager.getCurrentlyLoggedInUsername();
        String userTypeName = UserManager.getLoggedInUserTypeName(testContext);
        String userNameSurname = UserManager.getUserNameSurname();
        boolean currentlyLoggedInStatus = UserManager.getCurrentlyLoggedInStatus();
        int loggedInUserType = UserManager.getLoggedInUserType();

        assertTrue(currentlyLoggedInUsername.equals("")
                && userTypeName.equals("")
                && userNameSurname.equals("")
                && !currentlyLoggedInStatus
                && loggedInUserType == UserUtils.DEFAULT_USER);
    }
}