package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class ViewCommentsActivityTest {

    @Rule
    public ActivityTestRule<ViewCommentsActivity> activityTestRule = new ActivityTestRule<>(ViewCommentsActivity.class);

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void setActivityId() {
        int i = anyInt();
        ViewCommentsActivity.setActivityId(i);
        assertEquals(ViewCommentsActivity.activityId, i);
    }

    @Test
    public void setActivityTitle() {
        String t = anyString();
        ViewCommentsActivity.setActivityTitle(t);
        assertEquals(ViewCommentsActivity.title, t);
    }

    @Test
    public void setActivityDesc() {
        String d = anyString();
        ViewCommentsActivity.setActivityDesc(d);
        assertEquals(ViewCommentsActivity.desc, d);
    }

    @Test
    public void handleSwitch() {
        try {
            runOnUiThread(()->{
                int[] a = new int[1];
                activityTestRule.getActivity().handleSwitch(a, true, c);
                activityTestRule.getActivity().handleSwitch(a, false, c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void updateComments() {
        try {
            runOnUiThread(()->{
                ViewCommentsActivity.commentsHolder = new LinearLayout(c);
                ViewCommentsActivity.updateComments();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void sendComment(){
        try {
            runOnUiThread(()->{
                activityTestRule.getActivity().findViewById(R.id.send_comment_vc).performClick();
                ((TextInputEditText)activityTestRule.getActivity().findViewById(R.id.input_comment_vc))
                        .setText("some text");
                activityTestRule.getActivity().findViewById(R.id.send_comment_vc).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}