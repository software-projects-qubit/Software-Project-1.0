package com.wits.witssrcconnect.managers;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.activities.LogInActivity;
import com.wits.witssrcconnect.activities.SrcMemberActivity;
import com.wits.witssrcconnect.activities.SrcPostActivityActivity;
import com.wits.witssrcconnect.activities.SrcPostPollActivity;
import com.wits.witssrcconnect.activities.StudentActivity;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class LifeTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void startLoginActivityFirstTime(){
        UserManager.initUserManager(c);
        UserManager.userLoggedOut(c);
        getInstrumentation().runOnMainSync(() ->{
            Intent i = new Intent(c, LogInActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }

    @Test
    public void startLoginActivity(){
        int user = UserUtils.STUDENT;
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(user, "1627982");
        getInstrumentation().runOnMainSync(() ->{
            Intent i = new Intent(c, LogInActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }

    @Test
    public void startSrcMemberActivity(){
        int user = UserUtils.SRC_MEMBER;
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(user, "srcpresident");

        getInstrumentation().runOnMainSync(() ->{
            Intent i = new Intent(c, SrcMemberActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }

    @Test
    public void startStudentActivity(){
        int user = UserUtils.STUDENT;
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(user, "1627982");

        getInstrumentation().runOnMainSync(() ->{
            Intent i = new Intent(c, StudentActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }

    @Test
    public void startPostActivity(){
        getInstrumentation().runOnMainSync(() ->{
            Intent i = new Intent(c, SrcPostActivityActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }

    @Test
    public void startPostActivityWithBundle(){
        getInstrumentation().runOnMainSync(() ->{
            Intent i = new Intent(c, SrcPostActivityActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(ServerUtils.ACTIVITY_ID, 2);
            i.putExtra(ServerUtils.ACTIVITY_TITLE, anyString());
            i.putExtra(ServerUtils.ACTIVITY_DESC, anyString());
            c.startActivity(i);
        });
    }

    @Test
    public void startPostPollActivity(){
        getInstrumentation().runOnMainSync(() ->{
            Intent i = new Intent(c, SrcPostPollActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }
}
