package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.activities.LogInActivity;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class UiManager {

    //This function creates a layout Params for LinearLayout
    //it takes in a margin and return a layout Param, together with the margins applied
    //margins applied left, right, up, down
    public static LinearLayout.LayoutParams getLayoutParams(int margins) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(margins, margins, margins, margins);
        return params;
    }

    //Function that populates the navigation head with username and user type
    //of the currently logged in user
    public static void populateNavHead(View headerView) {
        ((AppCompatTextView) headerView.findViewById(R.id.header_username))
                .setText(UserManager.getCurrentlyLoggedInUsername());
        ((AppCompatTextView) headerView.findViewById(R.id.header_user_type))
                .setText(UserManager.getLoggedInUserTypeName(headerView.getContext()));
    }

    //logs the user out and clears all the data in shared preferences
    public static void logOut(Context context) {
        context.startActivity(new Intent(context, LogInActivity.class));
        UserManager.userLoggedOut();
        ((Activity) context).finish();
    }

    //When used, forces any bottom sheet to fully expand
    public static void forceBottomSheetToFullyExpand(DialogInterface dialog1) {
        BottomSheetDialog d = (BottomSheetDialog) dialog1;
        FrameLayout bottomSheet = d.findViewById(android.support.design.R.id.design_bottom_sheet);
        assert bottomSheet != null;
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    //this function gets the current date and time and provides a string array which has date and time
    public static String[] getDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        String sDateTime = simpleDateFormat.format(new Date());
        String[] dateTimeList = sDateTime.split("-");
        String date = dateTimeList[0].trim();
        String time = dateTimeList[1].trim();
        return new String[]{date, time};
    }
    //this function populates any given linear layout with src activities
    public static void populateWithSrcActivities(LinearLayout holder, JSONArray activities) {
        holder.removeAllViews();

        try {
            for (int i = 0; i < activities.length(); i++){
                //get JsonObject from jsonArray which contains data about each and every activity
                JSONObject activity = (JSONObject) activities.get(i);

                //get activity id
                int activityId = activity.getInt(ServerUtils.ACTIVITY_ID);

                //inflate a card view that will display all the date
                View activityItemView = View.inflate(holder.getContext(), R.layout.item_src_activity_card, null);

                //display title of the activity
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_title))
                        .setText(activity.getString(ServerUtils.ACTIVITY_TITLE));

                //display the src member who posted the activity
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_posted_by))
                        .setText(String.format("posted by: %s  ", activity.getString(ServerUtils.SRC_USERNAME)));

                //display the date and time of when the activity was posted
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_date_time))
                        .setText(String.format("on %s : %s  ", activity.getString(ServerUtils.ACTIVITY_DATE),
                                activity.getString(ServerUtils.ACTIVITY_TIME)));

                //display the activity description
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_activity))
                        .setText(activity.getString(ServerUtils.ACTIVITY_DESC).replace("\\n", "\n"));

                //create a reference to the anonymous comment switch
                SwitchCompat anonymitySwitch = activityItemView.findViewById(R.id.anonymity_switch);

                //declare a variable to save the anonymity switch
                final int[] anonymityTracker = {ServerUtils.ANONYMOUS_COMMENT_OFF};

                //this allows the app to listen for anonymous comment states (on and off) and displays
                //a notification at the change of each state
                anonymitySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    anonymityTracker[0] = isChecked ? ServerUtils.ANONYMOUS_COMMENT_ON : ServerUtils.ANONYMOUS_COMMENT_OFF;
                    String message;
                    if (isChecked) message = "Anonymous comment on";
                    else message = "Anonymous comment off";
                    Toast.makeText(holder.getContext(), message, Toast.LENGTH_SHORT).show();
                });

                //create a reference to the TextInputEditText used to input comments
                TextInputEditText comment = activityItemView.findViewById(R.id.input_comment);

                //set an onClickListener to send comment button, when pressed it will send the comment
                //to the database
                activityItemView.findViewById(R.id.send_comment).setOnClickListener(v -> {
                    //retrieves what the user entered
                    String sComment = Objects.requireNonNull(comment.getText()).toString().trim();
                    //check if the user actually entered something
                    //if he or she didn't enter anything, show error, else post the comment to the database
                    if (TextUtils.isEmpty(sComment)){
                        comment.setError("Comment required");
                    }
                    else {
                        Log.d("POPULATE_TEST", String.valueOf(activityId));
                        Log.d("POPULATE_TEST", String.valueOf(anonymityTracker[0]));

                        sComment = sComment.replace("\n", "\\n");
                        ContentValues cv = new ContentValues();

                        String[] dateTime = getDateTime();

                        cv.put(ServerUtils.ACTION, ServerUtils.POST_COMMENT);
                        cv.put(ServerUtils.ACTIVITY_ID, String.valueOf(activityId));
                        cv.put(ServerUtils.STUDENT_USERNAME, UserManager.getCurrentlyLoggedInUsername());
                        cv.put(ServerUtils.STUDENT_COMMENT, sComment);
                        cv.put(ServerUtils.STUDENT_ANONYMITY, String.valueOf(anonymityTracker[0]));
                        cv.put(ServerUtils.STUDENT_DATE, dateTime[0]);
                        cv.put(ServerUtils.STUDENT_TIME, dateTime[1]);

                        SrcActivityManager.postComment(cv, comment);
                    }
                });

                holder.addView(activityItemView, UiManager.getLayoutParams(15));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
