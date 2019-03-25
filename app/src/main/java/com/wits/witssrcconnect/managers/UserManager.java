package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.wits.witssrcconnect.activities.src_member.SrcMemberActivity;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

public class UserManager {

    private static SharedPreferences SHARED_PREFERENCES;
    private static String NAME = "USER_PREF";
    private static String LOGGED_IN_KEY = "LOGGED_IN";
    private static String USER_TYPE_KEY = "USER_TYPE";

    public static void initUserManager(Context context){
        SHARED_PREFERENCES = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static void setUserLoggedIn(int userType){
        SHARED_PREFERENCES.edit()
                .putBoolean(LOGGED_IN_KEY, true)
                .putInt(USER_TYPE_KEY, userType)
                .apply();
    }

    public static boolean userCurrentlyLoggedIn(){
        return SHARED_PREFERENCES.getBoolean(LOGGED_IN_KEY, false);
    }

    public static int getLoggedInUserType(){
        return SHARED_PREFERENCES.getInt(USER_TYPE_KEY, UserUtils.DEFAULT_USER);
    }

    //for src member
    //since their data is stored on our server
    public static void logIn(int user, ContentValues cv, String link, Context context){
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                if (output != null && output.equals(ServerUtils.SUCCESS)){
                    UserManager.setUserLoggedIn(user);
                    context.startActivity(new Intent(context, SrcMemberActivity.class));
                    ((Activity) context).finish();
                }
                else{
                    Toast.makeText(context, "LogIn failed", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(link);
    }

    //clear the data that stored in preferences
    public static void userLoggedOut(){
        SHARED_PREFERENCES.edit().clear().apply();
    }
}
