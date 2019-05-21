package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.activities.ViewCommentsActivity;
import com.wits.witssrcconnect.fragments.AllSrcActivitiesFragment;
import com.wits.witssrcconnect.fragments.MySrcActivitiesFragment;
import com.wits.witssrcconnect.fragments.StudentViewSrcActivitiesFragment;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;
import com.wits.witssrcconnect.utils.UserUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SrcActivityManager {

    //this function posts an activity to
    public static void postActivity(String title, String desc, Context context) {
        String[] dateTime = UiManager.getDateTime();

        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.POST_ACTIVITY);
        cv.put(ServerUtils.SRC_USERNAME, UserManager.getCurrentlyLoggedInUsername());
        cv.put(ServerUtils.ACTIVITY_TITLE, title);
        cv.put(ServerUtils.ACTIVITY_DESC, desc);
        cv.put(ServerUtils.ACTIVITY_DATE, dateTime[0]);
        cv.put(ServerUtils.ACTIVITY_TIME, dateTime[1]);

        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
                ServerCommunicator.showLoadingDialog(context);
                Toast.makeText(context, "Posting Activity...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String output) {
                ServerCommunicator.closeLoadingDialog();
                handlePostActivityFeedback(output, context);
            }
        }.execute(ServerUtils.SRC_MEMBER_LINK);
    }

    public static void handlePostActivityFeedback(String output, Context context) {
        if (output.equals(ServerUtils.SUCCESS)) {
            Toast.makeText(context, "Activity posted", Toast.LENGTH_SHORT).show();
            if (context instanceof Activity) ((Activity) context).finish();

        } else {
            Toast.makeText(context, "Activity post failed", Toast.LENGTH_SHORT).show();
        }
    }

    public static void fetchAllActivities(Context context, SwipeRefreshLayout pullToRefresh) {
        UserManager.initUserManager(context);
        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.READ_ALL_ACTIVITIES);
        cv.put(ServerUtils.USERNAME, UserManager.getCurrentlyLoggedInUsername());
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onPostExecute(String output) {
                handleFetchAllActivitiesFeedback(output, pullToRefresh, context);
            }
        }.execute(ServerUtils.SRC_MEMBER_LINK);
    }

    public static void handleFetchAllActivitiesFeedback(String output, SwipeRefreshLayout pullToRefresh, Context context) {
        if (pullToRefresh != null) pullToRefresh.setRefreshing(false);

        if (output.equals("")) {
            Toast.makeText(context, "Failed to get activities", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONArray activities = new JSONArray(output);

                if (activities.length() == 0) {
                    Toast.makeText(context, "There are no activities", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (UserManager.getLoggedInUserType()) {
                    case UserUtils.SRC_MEMBER:
                        String current_src_username = UserManager.getCurrentlyLoggedInUsername();
                        JSONArray myActivities = new JSONArray();

                        for (int i = 0; i < activities.length(); i++) {
                            JSONObject activity = (JSONObject) activities.get(i);
                            if (current_src_username.equals(activity.getString(ServerUtils.SRC_USERNAME))) {
                                myActivities.put(activity);
                            }
                        }

                        AllSrcActivitiesFragment.init(activities);
                        MySrcActivitiesFragment.init(myActivities);
                        break;

                    case UserUtils.STUDENT:
                        StudentViewSrcActivitiesFragment.init(activities);
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void postComment(ContentValues cv, TextInputEditText comment) {
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
                Toast.makeText(comment.getContext(), "posting comment...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String output) {
                handleCommentFeedback(output, comment);
            }
        }.execute(ServerUtils.COMMENT_LINK);
    }

    public static void handleCommentFeedback(String output, TextInputEditText comment) {
        if (output.equals(ServerUtils.SUCCESS)) {
            Toast.makeText(comment.getContext(), "Comment posted", Toast.LENGTH_SHORT).show();
            comment.setText("");
            comment.clearFocus();
            ViewCommentsActivity.updateComments();
        } else {
            Toast.makeText(comment.getContext(), "Failed to post comment", Toast.LENGTH_SHORT).show();
        }
    }

    public static void populateViewWithComments(LinearLayout commentsHolder, int activityId) {
        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.READ_COMMENT);
        cv.put(ServerUtils.ACTIVITY_ID, activityId);

        Context c = commentsHolder.getContext();
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
                Toast.makeText(c, "Retrieving comments...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String output) {
                handlePopulateViewWithCommentsFeedback(commentsHolder, output, c);
            }
        }.execute(ServerUtils.COMMENT_LINK);
    }

    public static void handlePopulateViewWithCommentsFeedback(LinearLayout commentsHolder, String output, Context c) {
        if (output.equals(""))
            Toast.makeText(c, "Failed to get comments", Toast.LENGTH_SHORT).show();
        else {
            try {
                JSONArray comments = new JSONArray(output);
                commentsHolder.removeAllViews();
                int length = comments.length();
                if (length == 0)
                    Toast.makeText(c, "There are no comments", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < length; i++) {
                    JSONObject comment = (JSONObject) comments.get(i);
                    View commentView = View.inflate(c, R.layout.item_comment_view_card, null);

                    int anonymity = comment.getInt(ServerUtils.STUDENT_ANONYMITY);
                    String name = anonymity == ServerUtils.ANONYMOUS_COMMENT_ON ?
                            UserUtils.ANONYMOUS : comment.getString(ServerUtils.STUDENT_USERNAME);

                    ((AppCompatTextView) commentView.findViewById(R.id.comment_owner))
                            .setText(String.format("comment by: %s ", name));

                    ((AppCompatTextView) commentView.findViewById(R.id.comment_view_date_time))
                            .setText(String.format("on %s at %s ", comment.getString(ServerUtils.STUDENT_DATE),
                                    comment.getString(ServerUtils.STUDENT_TIME)));

                    ((AppCompatTextView) commentView.findViewById(R.id.comment_view))
                            .setText(comment.getString(ServerUtils.STUDENT_COMMENT)
                                    .replace("\\n", "\n"));

                    commentsHolder.addView(commentView, UiManager.getLayoutParams(15));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateActivity(int finalActivityId, String sTitle, String sActivity, Context context) {
        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.UPDATE_ACTIVITY);
        cv.put(ServerUtils.ACTIVITY_ID, finalActivityId);
        cv.put(ServerUtils.ACTIVITY_TITLE, sTitle);
        cv.put(ServerUtils.ACTIVITY_DESC, sActivity);

        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
                ServerCommunicator.showLoadingDialog(context);
                Toast.makeText(context, "Updating Activity...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String output) {
                ServerCommunicator.closeLoadingDialog();
                handleUpdateActivity(output, context);
            }
        }.execute(ServerUtils.SRC_MEMBER_LINK);
    }

    public static void handleUpdateActivity(String output, Context context) {
        if (output.equals(ServerUtils.SUCCESS)) {
            Toast.makeText(context, "Activity updated", Toast.LENGTH_SHORT).show();
            if (context instanceof Activity) ((Activity) context).finish();
        }
        else Toast.makeText(context, "Activity update failed", Toast.LENGTH_SHORT).show();

    }
}
