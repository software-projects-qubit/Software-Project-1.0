package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.activities.SrcPostPollActivity;
import com.wits.witssrcconnect.fragments.AllSrcPollFragment;
import com.wits.witssrcconnect.fragments.MySrcPollFragment;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SrcPollManager {
    public static void postPoll(ContentValues cv, SrcPostPollActivity srcPostPollActivity) {
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {
                Toast.makeText(srcPostPollActivity, "Posting poll...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String output) {
                if (output != null && output.equals(ServerUtils.SUCCESS)){
                    Toast.makeText(srcPostPollActivity, "Poll posted", Toast.LENGTH_SHORT).show();
                    srcPostPollActivity.finish();
                }
                else{
                    Toast.makeText(srcPostPollActivity, "Failed to post poll", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(ServerUtils.POLL_LINK);
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
                if (pullToRefresh != null) pullToRefresh.setRefreshing(false);
                if (output == null || output.equals("")){
                    Toast.makeText(context, "Failed to get polls", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        JSONArray polls = new JSONArray(output);
                        if (polls.length() == 0){
                            Toast.makeText(context, "There are no polls available", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String currentUser = UserManager.getCurrentlyLoggedInUsername();
                        JSONArray myPolls = new JSONArray();

                        for (int i = 0; i < polls.length(); i++){
                            JSONObject poll = (JSONObject) polls.get(i);
                            if (currentUser.equals(poll.getString(ServerUtils.SRC_USERNAME))){
                                myPolls.put(poll);
                            }
                        }

                        AllSrcPollFragment.init(polls);
                        MySrcPollFragment.init(myPolls);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.execute(ServerUtils.POLL_LINK);
    }
}
