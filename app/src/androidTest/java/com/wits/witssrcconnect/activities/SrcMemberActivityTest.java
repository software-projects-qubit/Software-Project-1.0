package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UiManager;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class SrcMemberActivityTest {

    private static Context c = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<SrcMemberActivity> activityTestRule = new ActivityTestRule<>(SrcMemberActivity.class);

    @BeforeClass
    public static void onSetUp(){
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, "srcpresident");
    }

    @Test
    public void testBackPressed(){
        try {
            runOnUiThread(()-> activityTestRule.getActivity().onBackPressed());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void openNav(){
        try {
            runOnUiThread(()->{
                activityTestRule.getActivity().drawerLayout.openDrawer(GravityCompat.START);
                activityTestRule.getActivity().onBackPressed();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void pressHome(){
        try {
            runOnUiThread(()->{
                NavigationView nv = activityTestRule.getActivity().findViewById(R.id.nav_view);
                MenuItem mi = nv.getMenu().findItem(R.id.nav_home);
                activityTestRule.getActivity().onNavigationItemSelected(mi);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void pressTimeLine(){
        try {
            runOnUiThread(()->{
                NavigationView nv = activityTestRule.getActivity().findViewById(R.id.nav_view);
                MenuItem mi = nv.getMenu().findItem(R.id.nav_activity_timeline);
                activityTestRule.getActivity().onNavigationItemSelected(mi);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void pressPolls(){
        try {
            runOnUiThread(()->{
                NavigationView nv = activityTestRule.getActivity().findViewById(R.id.nav_view);
                MenuItem mi = nv.getMenu().findItem(R.id.nav_polls);
                activityTestRule.getActivity().onNavigationItemSelected(mi);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void pressLogOut(){
        try {
            runOnUiThread(()->{
                NavigationView nv = activityTestRule.getActivity().findViewById(R.id.nav_view);
                MenuItem mi = nv.getMenu().findItem(R.id.nav_log_out);
                activityTestRule.getActivity().onNavigationItemSelected(mi);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}