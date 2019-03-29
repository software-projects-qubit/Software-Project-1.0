package com.wits.witssrcconnect.managers;

import android.content.ContentValues;
import android.widget.Toast;

import com.wits.witssrcconnect.activities.SrcPostPollActivity;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

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
}
