package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.activities.LogInActivity;
import com.wits.witssrcconnect.activities.student.StudentActivity;

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


}
