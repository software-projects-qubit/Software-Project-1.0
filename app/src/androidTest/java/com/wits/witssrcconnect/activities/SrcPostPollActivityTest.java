package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatRadioButton;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SrcPostPollActivityTest {

    @Rule
    public ActivityTestRule<SrcPostPollActivity> activityTestRule = new ActivityTestRule<>(SrcPostPollActivity.class);
    private static Context c = InstrumentationRegistry.getTargetContext();

    @BeforeClass
    public static void setUp(){
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(UserUtils.STUDENT, "1627982");
    }

    @Test
    public void postPollPressedWithNoInput(){
        try {
            runOnUiThread(()-> activityTestRule.getActivity().findViewById(R.id.src_post_poll).performClick());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void postPollPressedWithSingleSelect(){
        try {
            runOnUiThread(()->{
                AppCompatRadioButton single = activityTestRule.getActivity().findViewById(R.id.src_single_select);
                single.setChecked(true);
                activityTestRule.getActivity().findViewById(R.id.src_post_poll).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void postPollPressedWithMultiSelect(){
        try {
            runOnUiThread(()->{
                AppCompatRadioButton multi = activityTestRule.getActivity().findViewById(R.id.src_multi_select);
                multi.setChecked(true);
                activityTestRule.getActivity().findViewById(R.id.src_post_poll).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void addVotingOption(){

    }
}