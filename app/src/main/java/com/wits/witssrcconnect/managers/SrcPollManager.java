package com.wits.witssrcconnect.managers;

import android.content.ContentValues;

import com.wits.witssrcconnect.activities.SrcPostPollActivity;
import com.wits.witssrcconnect.services.ServerCommunicator;
import com.wits.witssrcconnect.utils.ServerUtils;

public class SrcPollManager {
    public static void postPoll(ContentValues cv, SrcPostPollActivity srcPostPollActivity) {
        new ServerCommunicator(cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {

            }
        }.execute(ServerUtils.POLL_LINK);
    }
}
