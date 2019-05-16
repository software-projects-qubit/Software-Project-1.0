package com.wits.witssrcconnect.managers;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class UiManagerTest {

    private Context c = InstrumentationRegistry.getTargetContext();

    @Test
    public void forceBottomSheetToFullyExpand() {
        try {
            runOnUiThread(() -> {
                BottomSheetDialog dialog = new BottomSheetDialog(c);
                dialog.setOnShowListener(UiManager::forceBottomSheetToFullyExpand);
                dialog.show();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////////////MODIFY AGAIN HERE
    @Test
    public void populateWithSrcActivities(){
        try {
            runOnUiThread(()->{
                try {
                    LinearLayout holder = new LinearLayout(c);
                    holder.setOrientation(LinearLayout.VERTICAL);

                    FragmentManager fragmentManager = Mockito.mock(FragmentManager.class);

                    JSONArray testJsonArray = new JSONArray();
                    JSONObject testJsonObject = new JSONObject();
                    testJsonObject.put(ServerUtils.ACTIVITY_ID, anyInt());
                    testJsonObject.put(ServerUtils.ACTIVITY_TITLE, anyString());
                    testJsonObject.put(ServerUtils.ACTIVITY_DESC, anyString());
                    testJsonObject.put(ServerUtils.SRC_USERNAME, anyString());
                    testJsonObject.put(ServerUtils.ACTIVITY_DATE, anyString());
                    testJsonObject.put(ServerUtils.ACTIVITY_TIME, anyString());

                    testJsonArray.put(testJsonObject);
                    testJsonArray.put(testJsonObject);

                    UiManager.populateWithSrcActivities(holder, testJsonArray, fragmentManager, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                int[] a = new int[1];
                a[0] = ServerUtils.ANONYMOUS_COMMENT_ON;

                UiManager.sendComment(comment, a, anyInt());
                comment.setText("test text");
                UiManager.sendComment(comment, a, anyInt());
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void openComments(){
        try {
            runOnUiThread(()->{
                FragmentManager fragmentManager = Mockito.mock(FragmentManager.class);
                UiManager.openComments(anyInt(), anyString(), anyString(), fragmentManager);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void deleteActivity(){
        try {
            runOnUiThread(()->{
                LinearLayout holder = new LinearLayout(c);
                holder.setOrientation(LinearLayout.VERTICAL);
                View activityItemView = View.inflate(holder.getContext(), R.layout.item_src_activity_card, null);
                holder.addView(activityItemView);
                UiManager.deleteActivity(anyInt(), activityItemView, holder);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void handleAnonymity(){
        try {
            runOnUiThread(()->{
                int[] a = new int[1];
                UiManager.handleAnonymity(true, a, c);
                UiManager.handleAnonymity(false, a, c);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void deleteItem(){
        try {
            runOnUiThread(()->{
                UiManager.deleteItem(new ContentValues(), new LinearLayout(c), new View(c), anyString());
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void handleDeleteItemFeedback(){
        try {
            runOnUiThread(()->{
                LinearLayout l = new LinearLayout(c);
                l.setOrientation(LinearLayout.VERTICAL);

                View v = new View(c);
                l.addView(v);

                UiManager.handleDeleteItemFeedback(ServerUtils.FAILED, l, v);
                UiManager.handleDeleteItemFeedback(ServerUtils.SUCCESS, l, v);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }

    @Test
    public void populateWithPolls(){

        try {
            runOnUiThread(()->{
                JSONArray testJsonArray = new JSONArray();
                JSONObject testJsonObject = new JSONObject();
                try {
                    testJsonObject.put(ServerUtils.POLL_TITLE, anyString());
                    testJsonObject.put(ServerUtils.SRC_USERNAME, anyString());
                    testJsonObject.put(ServerUtils.POLL_DATE, anyString());
                    testJsonObject.put(ServerUtils.POLL_TIME, anyString());
                    testJsonObject.put(ServerUtils.POLL_DESC, anyString());
                    String options = "opt1~opt2~opt3";
                    testJsonObject.put(ServerUtils.POLL_CHOICE, options);
                    testJsonObject.put(ServerUtils.POLL_TYPE, anyInt());

                    testJsonArray.put(testJsonObject);
                    testJsonArray.put(testJsonObject);
                    testJsonArray.put(testJsonObject);

                    LinearLayout holder = new LinearLayout(c);
                    holder.setOrientation(LinearLayout.VERTICAL);
                    FragmentManager fragmentManager = Mockito.mock(FragmentManager.class);
                    UiManager.populateWithPolls(holder, testJsonArray, fragmentManager);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void openPollVoteBottomSheet(){

    }
}