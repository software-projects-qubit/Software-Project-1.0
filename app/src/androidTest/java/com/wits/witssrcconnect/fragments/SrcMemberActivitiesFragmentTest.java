package com.wits.witssrcconnect.fragments;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SrcMemberActivitiesFragmentTest {

    private static Context c = InstrumentationRegistry.getTargetContext();
    private static SrcMemberActivitiesFragment srcMemberActivitiesFragment;

    @BeforeClass
    public static void setUp(){
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, "srcpresident");
        srcMemberActivitiesFragment = new SrcMemberActivitiesFragment();
    }

    @Test
    public void addActivityTest(){

    }
}