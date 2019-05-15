package com.wits.witssrcconnect.managers;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class UiManagerTest {

    private Context c = InstrumentationRegistry.getTargetContext();
    @Test
    public void forceBottomSheetToFullyExpand() {
        try {
            runOnUiThread(()->{
                BottomSheetDialog dialog = new BottomSheetDialog(c);
                dialog.setOnShowListener(UiManager::forceBottomSheetToFullyExpand);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void populateWithSrcActivities() {
        try {
            runOnUiThread(()->{
                ContentValues cv = new ContentValues();
                cv.put(ServerUtils.ACTION, ServerUtils.READ_ALL_ACTIVITIES);
                new ServerCommunicator(cv) {
                    @Override
                    protected void onPreExecute() {

                    }

                    @Override
                    protected void onPostExecute(String output) {
                        try {
                            JSONArray jsonArray = new JSONArray(output);
                            LinearLayout holder = new LinearLayout(c);
                            holder.setOrientation(LinearLayout.VERTICAL);
                            FragmentManager fragmentManager = Mockito.mock(FragmentManager.class);
                            UiManager.populateWithSrcActivities(holder, jsonArray, fragmentManager, false);
                            UiManager.populateWithSrcActivities(holder, jsonArray, fragmentManager, true);
                            UiManager.anonymitySwitch.performClick();
                            UiManager.anonymitySwitch.performClick();
                            UiManager.sendButton.performClick();
                            UiManager.comment.setText("Sample text");
                            UiManager.sendButton.performClick();
                            UiManager.viewComments.performClick();
                            UiManager.menu.performClick();
                            UiManager.updateActivity(c, 1, anyString(), anyString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute(ServerUtils.SRC_MEMBER_LINK);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void populateWithPolls() {
    }
}