package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wits.witssrcconnect.R;
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
public class SrcPostActivityActivityTest {
    private static Context c = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<SrcPostActivityActivity> activityTestRule = new ActivityTestRule<>(SrcPostActivityActivity.class);

    @BeforeClass
    public static void onSetUp(){
        UserManager.initUserManager(c);
        UserManager.setUserLoggedIn(UserUtils.SRC_MEMBER, "srcpresident");
    }

    @Test
    public void pressPostWithNoInput(){
        try {
            runOnUiThread(()-> activityTestRule.getActivity().findViewById(R.id.src_post_activity).performClick());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void pressPostWithTitleInput(){
        try {
            runOnUiThread(()-> {
                activityTestRule.getActivity().title.setText("title");
                activityTestRule.getActivity().findViewById(R.id.src_post_activity).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void pressPostWithAllInputs(){
        try {
            runOnUiThread(()-> {
                activityTestRule.getActivity().title.setText("title");
                activityTestRule.getActivity().activity.setText("activity");
                activityTestRule.getActivity().findViewById(R.id.src_post_activity).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void pressPostWithActivityId(){
        try {
            runOnUiThread(()->{
                activityTestRule.getActivity().activityId = -2;
                activityTestRule.getActivity().title.setText("title");
                activityTestRule.getActivity().activity.setText("activity");
                activityTestRule.getActivity().findViewById(R.id.src_post_activity).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}