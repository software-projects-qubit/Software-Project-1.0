package com.wits.witssrcconnect.managers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.wits.witssrcconnect.activities.PollVoteActivity;
import com.wits.witssrcconnect.fragments.AllSrcPollFragment;
import com.wits.witssrcconnect.fragments.MySrcPollFragment;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SrcPollManager {

    public static void postPoll(ContentValues cv, Context context) {
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
                Toast.makeText(context, "Posting poll...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String output) {
                handlePostPollFeedBack(output, context);
            }
        }.execute(ServerUtils.POLL_LINK);
    }

    public static void handlePostPollFeedBack(String output, Context context) {
        if (output.equals(ServerUtils.SUCCESS)) {
            Toast.makeText(context, "Poll posted", Toast.LENGTH_SHORT).show();
            if (context instanceof Activity) ((Activity) context).finish();
        } else {
            Toast.makeText(context, "Failed to post poll", Toast.LENGTH_SHORT).show();
        }
    }

    public static void fetchAllPolls(Context context, SwipeRefreshLayout pullToRefresh) {
        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.READ_ALL_POLLS);
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                handleFetchAllPollsFeedBack(output, context, pullToRefresh);
            }
        }.execute(ServerUtils.POLL_LINK);
    }

    public static void handleFetchAllPollsFeedBack(String output, Context context, SwipeRefreshLayout pullToRefresh) {
        if (pullToRefresh != null) pullToRefresh.setRefreshing(false);
        if (output.equals("")) {
            Toast.makeText(context, "Failed to get polls", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONArray polls = new JSONArray(output);
                if (polls.length() == 0) {
                    AllSrcPollFragment.init(polls);
                    MySrcPollFragment.init(polls);
                    Toast.makeText(context, "There are no polls available", Toast.LENGTH_SHORT).show();
                    return;
                }

                String currentUser = UserManager.getCurrentlyLoggedInUsername();
                JSONArray myPolls = new JSONArray();

                for (int i = 0; i < polls.length(); i++) {
                    JSONObject poll = (JSONObject) polls.get(i);
                    if (currentUser.equals(poll.getString(ServerUtils.SRC_USERNAME))) {
                        myPolls.put(poll);
                    }
                }

                AllSrcPollFragment.init(polls);
                MySrcPollFragment.init(myPolls);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void votePoll(Context c, String choices, int pollId, String userId) {
        ContentValues cv = new ContentValues();
        cv.put(ServerUtils.ACTION, ServerUtils.POST_POLL_VOTE);
        cv.put(ServerUtils.POLL_ID, pollId);
        cv.put(ServerUtils.STUDENT_ID, userId);
        cv.put(ServerUtils.POLL_SET_CHOICE, choices);
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                handleVotePollFeedback(c, output);
            }
        }.execute(ServerUtils.POLL_LINK);
    }

    public static void handleVotePollFeedback(Context c, String output) {
        if (output.equals("2")){
            Toast.makeText(c, "You have already voted for this poll", Toast.LENGTH_SHORT).show();
        }
        else if (output.equals(ServerUtils.SUCCESS)){
            Toast.makeText(c, "Vote sent", Toast.LENGTH_SHORT).show();
            if (c instanceof Activity) ((Activity) c).finish();
        }
        else{
            Toast.makeText(c, "Voting failed", Toast.LENGTH_SHORT).show();
        }
    }
}
