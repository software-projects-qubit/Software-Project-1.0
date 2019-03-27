package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.ContentValues;
import android.widget.Toast;

import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SrcActivityManager {

    //this function posts an activity to
    public static void postActivity(String title, String activity, Activity activityClass) {
        Toast.makeText(activityClass, "Please wait...", Toast.LENGTH_SHORT).show();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        String sDateTime = simpleDateFormat.format(new Date());
        String[] dateTimeList = sDateTime.split("-");
        String date = dateTimeList[0].trim();
        String time = dateTimeList[1].trim();

        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.POST_ACTIVITY);
        cv.put(ServerUtils.SRC_USERNAME, UserManager.getCurrentlyLoggedInUsername());
        cv.put(ServerUtils.ACTIVITY_TITLE, title);
        cv.put(ServerUtils.ACTIVITY_DESC, activity);
        cv.put(ServerUtils.ACTIVITY_POST_DATE, date);
        cv.put(ServerUtils.ACTIVITY_POST_TIME, time);

        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                if (output != null && output.equals(ServerUtils.SUCCESS)){
                    Toast.makeText(activityClass, "Activity posted", Toast.LENGTH_SHORT).show();
                    activityClass.finish();
                }
                else{
                    Toast.makeText(activityClass, "Activity post failed", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(ServerUtils.SRC_MEMBER_LINK);
    }
}
