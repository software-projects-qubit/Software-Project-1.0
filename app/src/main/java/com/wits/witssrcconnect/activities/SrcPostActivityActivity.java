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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_src_post_activity);

        TextInputEditText
                title = findViewById(R.id.src_activity_title),
                activity = findViewById(R.id.src_activity_input);

        AppCompatButton postUpdateActivity = findViewById(R.id.src_post_activity);

        //check if the activity received any intent
        Bundle b = getIntent().getExtras();
        int activityId = -1;
        if (b != null) {
            activityId = b.getInt(ServerUtils.ACTIVITY_ID, -1);
            String sTitle = b.getString(ServerUtils.ACTIVITY_TITLE);
            String sDesc = b.getString(ServerUtils.ACTIVITY_DESC);

            if (activityId != -1) {
                title.setText(sTitle);
                activity.setText(sDesc);
                postUpdateActivity.setText(getString(R.string.update_activity));
            }
        }

        //posts src member activity to the server, as long as the activity is not empty
        int finalActivityId = activityId;
        postUpdateActivity.setOnClickListener(v -> {
            String sTitle = Objects.requireNonNull(title.getText()).toString().trim();
            String sActivity = Objects.requireNonNull(activity.getText()).toString().trim();

            boolean everythingOkay = true;
            if (TextUtils.isEmpty(sTitle)) {
                everythingOkay = false;
                title.setError("Title required");
            }

            if (TextUtils.isEmpty(sActivity)) {
                everythingOkay = false;
                activity.setError("Activity required");
            }

            if (everythingOkay) {
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
