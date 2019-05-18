package com.wits.witssrcconnect.activities;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class ViewCommentsActivityTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void setActivityId() {
        ViewCommentsActivity.setActivityId(0);
    }

    @Test
    public void setActivityTitle() {
        ViewCommentsActivity.setActivityTitle("some");
    }

    @Test
    public void setActivityDesc() {
        ViewCommentsActivity.setActivityDesc("abc");
    }

    @Test
    public void handleSwitch() {
        try {
            runOnUiThread(()->{
                int[] a = new int[1];
                ViewCommentsActivity.handleSwitch(a, true, c);
                ViewCommentsActivity.handleSwitch(a, false, c);
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
                TextInputEditText comment = new TextInputEditText(c);
                int[] a = new int[]{ServerUtils.ANONYMOUS_COMMENT_OFF};
                ViewCommentsActivity.handleSendComment(comment, a);
                comment.setText("some error");
                ViewCommentsActivity.handleSendComment(comment, a);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}