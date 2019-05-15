package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;
import android.view.MenuItem;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class StudentActivityTest {

    private static Context c = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<StudentActivity> activityTestRule = new ActivityTestRule<>(StudentActivity.class);

    @BeforeClass
    public static void onSetUp(){
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(UserUtils.STUDENT, "1627982");
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
                activityTestRule.getActivity().drawerLayout.openDrawer(Gravity.LEFT);
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