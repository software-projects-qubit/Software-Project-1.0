package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class PollVoteActivityTest {

    private Context c = getTargetContext();
    @Rule
    public ActivityTestRule<PollVoteActivity> activityTestRule = new ActivityTestRule<>(PollVoteActivity.class);

    @Test
    public void voteButtonPressed(){
        try{
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                PollVoteActivity.setPollType(ServerUtils.POLL_TYPE_MULTI_SELECT);
                PollVoteActivity.setPollChoices(new String[]{"opt1", "opt2"});
                activityTestRule.getActivity().vote.performClick();
                activityTestRule.getActivity().option[0] = "something";
                activityTestRule.getActivity().vote.performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void castVote(){
        try {
            runOnUiThread(()->{
                UserManager.initUserManager(c);
                LinearLayout l = new LinearLayout(c);
                l.setOrientation(LinearLayout.VERTICAL);
                activityTestRule.getActivity().castVote(l);
                CheckBox cb0 = new CheckBox(c);
                cb0.setText("something");
                cb0.setChecked(true);
                CheckBox cb1 = new CheckBox(c);
                cb1.setText("something");
                l.addView(cb0);
                l.addView(cb1);
                activityTestRule.getActivity().castVote(l);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}