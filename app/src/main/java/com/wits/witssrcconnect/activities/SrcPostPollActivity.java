package com.wits.witssrcconnect.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;

public class SrcPostPollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_poll);

        TextInputEditText
                title = findViewById(R.id.src_poll_title),
                pollDesc = findViewById(R.id.src_poll_desc);

        AppCompatRadioButton
                singleSelect = findViewById(R.id.src_single_select),
                multiSelect = findViewById(R.id.src_multi_select);

        findViewById(R.id.src_add_poll_option).setOnClickListener(v -> {

        });

        LinearLayout optionsHolder = findViewById(R.id.options_holder);

        findViewById(R.id.src_post_poll).setOnClickListener(v -> {

        });
    }
}
