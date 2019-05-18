package com.wits.witssrcconnect.activities;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UiManager;

public class PollVoteActivity extends AppCompatActivity {

    public static int pollId = -1;
    public static String title = "", desc = "";
    public static int pollType = -1;
    public static String[] pollChoices = new String[]{};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poll_votes);
    }



    public static void setPollId(int pollId){
        PollVoteActivity.pollId = pollId;
    }

    public static void setTitle(String title){
        PollVoteActivity.title = title;
    }

    public static void setDesc(String desc){
        PollVoteActivity.desc = desc;
    }

    public static void setPollType(int pollType){
        PollVoteActivity.pollType = pollType;
    }

    public static void setPollChoices(String[] pollChoices){
        PollVoteActivity.pollChoices = pollChoices;
    }
}
