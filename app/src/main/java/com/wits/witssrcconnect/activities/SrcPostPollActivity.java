package com.wits.witssrcconnect.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.wits.witssrcconnect.R;

import java.util.Objects;

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
            String sTitle = Objects.requireNonNull(title.getText()).toString().trim();
            String sPollDesc = Objects.requireNonNull(pollDesc.getText()).toString().trim();

            boolean everythingOkay = true;

            if (TextUtils.isEmpty(sTitle)){
                everythingOkay = false;
                title.setError("Title required");
            }

            if (TextUtils.isEmpty(sPollDesc)){
                everythingOkay = false;
                pollDesc.setError("Poll Description required");
            }
        });
    }
}
