package com.wits.witssrcconnect.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcActivitiesManager;
import com.wits.witssrcconnect.managers.UiManager;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SrcPostActivityBottomSheet extends BottomSheetDialogFragment {

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View content = View.inflate(getContext(), R.layout.bottom_sheet_src_post_activity, null);

        dialog.setContentView(content);
        //force bottom sheet to fully expand
        dialog.setOnShowListener(UiManager::forceBottomSheetToFullyExpand);

        TextInputEditText activity = content.findViewById(R.id.src_activity_input);
        //posts src member activity to the server, as long as the activity is not empty
        content.findViewById(R.id.src_post_activity).setOnClickListener(v -> {
            String sActivity = Objects.requireNonNull(activity.getText()).toString().trim();
            if (!TextUtils.isEmpty(sActivity)){
                sActivity = sActivity.replace("\n", " \\n ");
                postActivity(sActivity, dialog);

            }
            else activity.setError("Activity required");
        });
    }

    public static void postActivity(String activity, Dialog dialog) {
        Context c = dialog.getContext();
        Toast.makeText(c, "Please wait...", Toast.LENGTH_SHORT).show();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        String sDateTime = simpleDateFormat.format(new Date());
        String[] dateTimeList = sDateTime.split("-");
        String date = dateTimeList[0].trim();
        String time = dateTimeList[1].trim();

        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.POST_ACTIVITY);
        cv.put(ServerUtils.SRC_USERNAME, UserManager.getCurrentlyLoggedInUsername());
        cv.put(ServerUtils.ACTIVITY_CURRENT, activity);
        cv.put(ServerUtils.ACTIVITY_POST_DATE, date);
        cv.put(ServerUtils.ACTIVITY_POST_TIME, time);

        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                if (output != null && output.equals(ServerUtils.SUCCESS)){
                    dialog.dismiss();
                    Toast.makeText(c, "Activity posted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(c, "Activity post failed", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(ServerUtils.SRC_MEMBER_LINK);
    }
}
