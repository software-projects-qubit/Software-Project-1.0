package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.activities.SrcMemberActivity;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

public class UserManager {

    private static SharedPreferences SHARED_PREFERENCES;
    private static String NAME = "USER_PREF";
    private static String LOGGED_IN_USER_KEY = "LOGGED_IN_USERNAME";
    private static String LOGGED_IN_KEY = "LOGGED_IN";
    private static String USER_TYPE_KEY = "USER_TYPE";

    public static void initUserManager(Context context){
        SHARED_PREFERENCES = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    //saves which type of user logged into shared preferences
    public static void setUserLoggedIn(int userType, String username){
        SHARED_PREFERENCES.edit()
                .putString(LOGGED_IN_USER_KEY, username)
                .putBoolean(LOGGED_IN_KEY, true)
                .putInt(USER_TYPE_KEY, userType)
                .apply();
    }

    //retrieves the name of the user who is currently logged in
    public static String getCurrentlyLoggedInUsername(){
        return SHARED_PREFERENCES.getString(LOGGED_IN_USER_KEY, "");
    }

    //retrieves the type of user logged in as type of string e.g. Student, SRC Member
    public static String getLoggedInUserTypeName(Context context){
        return getLoggedInUserType() == UserUtils.STUDENT ?
                context.getString(R.string.student) : context.getString(R.string.src_member);
    }

    //checks if you are loggedIn
    public static boolean getCurrentlyLoggedInStatus(){
        return SHARED_PREFERENCES.getBoolean(LOGGED_IN_KEY, false);
    }

    //retrieves which user type is logged in
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
                    UserManager.setUserLoggedIn(user, cv.getAsString(ServerUtils.SRC_USERNAME));
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
