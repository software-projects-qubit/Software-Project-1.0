package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
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

    //this function populates any given linear layout with src activities
    public static void populateWithSrcActivities(LinearLayout holder, JSONArray activities) {
        holder.removeAllViews();

        try {
            for (int i = 0; i < activities.length(); i++){
                JSONObject activity = (JSONObject) activities.get(i);
                View activityItemView = View.inflate(holder.getContext(), R.layout.item_src_activity_card, null);
                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_title))
                        .setText(activity.getString(ServerUtils.ACTIVITY_TITLE));

                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_posted_by))
                        .setText(String.format("posted by: %s  ", activity.getString(ServerUtils.SRC_USERNAME)));

                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_date_time))
                        .setText(String.format("on %s : %s  ", activity.getString(ServerUtils.ACTIVITY_DATE),
                                activity.getString(ServerUtils.ACTIVITY_TIME)));

                ((AppCompatTextView) activityItemView.findViewById(R.id.src_activity_card_activity))
                        .setText(activity.getString(ServerUtils.ACTIVITY_DESC).replace("\\n", "\n"));

                SwitchCompat anonymitySwitch = activityItemView.findViewById(R.id.anonymity_switch);

                //declare a variable to save the anonymity switch
                final int[] anonymityTracker = {ServerUtils.ANONYMOUS_COMMENT_OFF};

                anonymitySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    anonymityTracker[0] = isChecked ? ServerUtils.ANONYMOUS_COMMENT_ON : ServerUtils.ANONYMOUS_COMMENT_OFF;
                    String message;
                    if (isChecked) message = "Anonymous comment on";
                    else message = "Anonymous comment off";
                    Toast.makeText(holder.getContext(), message, Toast.LENGTH_SHORT).show();
                });

                TextInputEditText comment = activityItemView.findViewById(R.id.input_comment);
                activityItemView.findViewById(R.id.send_comment).setOnClickListener(v -> {

                });

                holder.addView(activityItemView, UiManager.getLayoutParams(15));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
