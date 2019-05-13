package com.wits.witssrcconnect.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcActivityManager;
import com.wits.witssrcconnect.utils.ServerUtils;

import java.util.Objects;

public class SrcPostActivityActivity extends AppCompatActivity {

    public TextInputEditText title, activity;
    public int activityId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_src_post_activity);

        title = findViewById(R.id.src_activity_title);
        activity = findViewById(R.id.src_activity_input);

        AppCompatButton postUpdateActivity = findViewById(R.id.src_post_activity);

        //check if the activity received any intent
        Bundle b = getIntent().getExtras();
        if (b != null) {
            activityId = b.getInt(ServerUtils.ACTIVITY_ID, -1);
            String sTitle = b.getString(ServerUtils.ACTIVITY_TITLE);
            String sDesc = b.getString(ServerUtils.ACTIVITY_DESC);


            title.setText(sTitle);
            activity.setText(sDesc);
            postUpdateActivity.setText(getString(R.string.update_activity));

        }

        //posts src member activity to the server, as long as the activity is not empty

        postUpdateActivity.setOnClickListener(v -> {
            String sTitle = Objects.requireNonNull(title.getText()).toString().trim();
            String sActivity = Objects.requireNonNull(activity.getText()).toString().trim();
            int finalActivityId = activityId;

            if (TextUtils.isEmpty(sTitle)) {
                title.setError("Title required");
            }

            else if (TextUtils.isEmpty(sActivity)) {
                activity.setError("Activity required");
            }

            else {
                sActivity = sActivity.replace("\n", "\\n");
                if (finalActivityId == -1) {
                    SrcActivityManager.postActivity(sTitle, sActivity, this);
                } else {
                    SrcActivityManager.updateActivity(finalActivityId, sTitle, sActivity, this);
                }
            }
        });
    }
}
