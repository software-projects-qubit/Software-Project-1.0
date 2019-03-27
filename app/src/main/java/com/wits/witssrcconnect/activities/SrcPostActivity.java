package com.wits.witssrcconnect.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcActivityManager;

import java.util.Objects;

public class SrcPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_src_post_activity);

        TextInputEditText
                title = findViewById(R.id.src_activity_title),
                activity = findViewById(R.id.src_activity_input);
        //posts src member activity to the server, as long as the activity is not empty
        findViewById(R.id.src_post_activity).setOnClickListener(v -> {
            String sTitle = Objects.requireNonNull(title.getText()).toString().trim();
            String sActivity = Objects.requireNonNull(activity.getText()).toString().trim();

            boolean everythingOkay = true;
            if (TextUtils.isEmpty(sTitle)){
                everythingOkay = false;
                title.setError("Title required");
            }

            if (TextUtils.isEmpty(sActivity)){
                everythingOkay = false;
                activity.setError("Activity required");
            }

            if (everythingOkay){
                SrcActivityManager.postActivity(sTitle, sActivity.replace("\n", " \\n "), this);
            }
        });
    }
}
