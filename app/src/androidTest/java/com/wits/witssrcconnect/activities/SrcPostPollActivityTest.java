package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.UserUtils;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

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
    public void pressVotingOptionWithNoChoiceOfInput(){
        try {
            runOnUiThread(()->{
                activityTestRule.getActivity().findViewById(R.id.src_add_poll_option).performClick();
                activityTestRule.getActivity().dialog.show();
                activityTestRule.getActivity().dialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                LinearLayout optionsHolder = activityTestRule.getActivity().findViewById(R.id.options_holder);
                activityTestRule.getActivity().handleOption(optionsHolder,
                        activityTestRule.getActivity().optionsArrayList,
                        activityTestRule.getActivity().dialogInterface);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    @Test
    public void pressVotingOptionButton(){
        try {
            runOnUiThread(()->{
                activityTestRule.getActivity().findViewById(R.id.src_add_poll_option).performClick();
                AppCompatEditText choiceInput = activityTestRule.getActivity().choiceInput;
                choiceInput.requestFocus();
                choiceInput.setText("a");
                choiceInput.setText("ab");
                choiceInput.setText("abc~");
                activityTestRule.getActivity().dialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                LinearLayout optionsHolder = activityTestRule.getActivity().findViewById(R.id.options_holder);
                activityTestRule.getActivity().handleOption(optionsHolder,
                        activityTestRule.getActivity().optionsArrayList,
                        activityTestRule.getActivity().dialogInterface);
                activityTestRule.getActivity().deleteItem.performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void pressPostPoll(){
        try {
            runOnUiThread(()->{
                AppCompatRadioButton multi = activityTestRule.getActivity().findViewById(R.id.src_multi_select);
                multi.setChecked(true);
                activityTestRule.getActivity().title.setText("test");
                activityTestRule.getActivity().pollDesc.setText("desc");
                activityTestRule.getActivity().optionsArrayList.add("opt1");
                activityTestRule.getActivity().optionsArrayList.add("opt2");
                activityTestRule.getActivity().findViewById(R.id.src_post_poll).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}