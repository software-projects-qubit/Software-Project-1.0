package com.wits.witssrcconnect.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcPollManager;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.ServerUtils;

public class PollVoteActivity extends AppCompatActivity {

    public static int pollId = -1;
    public static String title = "", desc = "";
    public static int pollType = -1;
    public static String[] pollChoices = new String[]{};
    public AppCompatButton vote;
    final String[] option = {""};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poll_votes);

        getSupportActionBar().setElevation(0);

        ((AppCompatTextView) findViewById(R.id.vote_view_title)).setText(title);
        ((AppCompatTextView) findViewById(R.id.vote_view_desc)).setText(desc);
        LinearLayout optionsHolder = findViewById(R.id.vote_options_holder);
        vote = findViewById(R.id.appCompatButton);

        if (pollType == ServerUtils.POLL_TYPE_SINGLE_SELECT){
            RadioGroup radioGroup = new RadioGroup(this);
            for (String s : pollChoices){
                RadioButton rb = new RadioButton(this);
                rb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    option[0] = handleOptionSelection(rb, isChecked);
                });
                rb.setText(s);
                radioGroup.addView(rb);
            }
            optionsHolder.addView(radioGroup);
            vote.setOnClickListener(v ->{
                if (TextUtils.isEmpty(option[0])){
                    Toast.makeText(PollVoteActivity.this, "Select an option", Toast.LENGTH_SHORT).show();
                    return;
                }
                SrcPollManager.votePoll(PollVoteActivity.this, option[0], PollVoteActivity.pollId, UserManager.getCurrentlyLoggedInUsername());
            });
        }
        else{
            for(String s : pollChoices){
                CheckBox b = new CheckBox(this);
                b.setText(s);
                optionsHolder.addView(b);
            }
            vote.setOnClickListener(v->{
                castVote(optionsHolder);
            });
        }
    }

    public void castVote(LinearLayout optionsHolder) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < optionsHolder.getChildCount(); i++){
            CheckBox cb = (CheckBox) optionsHolder.getChildAt(i);
            if (!cb.isChecked()) continue;
            s.append(cb.getText().toString());
            s.append("~");
        }
        if (TextUtils.isEmpty(s.toString())) {
            Toast.makeText(optionsHolder.getContext(), "Select option", Toast.LENGTH_SHORT).show();
            return;
        }
        SrcPollManager.votePoll(PollVoteActivity.this, s.toString(), PollVoteActivity.pollId, UserManager.getCurrentlyLoggedInUsername());
    }

    public String handleOptionSelection(RadioButton rb, boolean isChecked) {
        if (isChecked) return rb.getText().toString();
        return "";
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
