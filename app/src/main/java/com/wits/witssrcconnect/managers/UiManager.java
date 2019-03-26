package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.activities.LogInActivity;

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
}
