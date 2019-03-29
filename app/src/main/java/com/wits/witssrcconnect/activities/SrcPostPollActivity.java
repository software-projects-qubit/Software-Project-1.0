package com.wits.witssrcconnect.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.wits.witssrcconnect.R;

public class SrcPostPollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_poll);

        TextInputEditText
                title = findViewById(R.id.src_poll_title),
                pollDesc = findViewById(R.id.src_poll_desc);


    }
}
