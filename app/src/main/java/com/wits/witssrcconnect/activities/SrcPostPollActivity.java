package com.wits.witssrcconnect.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.UiManager;

import java.util.ArrayList;
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

        LinearLayout optionsHolder = findViewById(R.id.options_holder);
        ArrayList<String> optionsArrayList = new ArrayList<>();

        findViewById(R.id.src_add_poll_option).setOnClickListener(v ->
                addVotingOption(optionsArrayList, optionsHolder));

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

            boolean singleSelected = singleSelect.isSelected();
            boolean multiSelected = multiSelect.isSelected();

            if (singleSelected){

            }
            else if(multiSelected){

            }
            else{
                everythingOkay = false;
                Toast.makeText(v.getContext(), "Select poll type", Toast.LENGTH_SHORT).show();
            }

            if (optionsArrayList.size() < 2){
                everythingOkay = false;
                Toast.makeText(v.getContext(), "Add at least 2 options", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void addVotingOption(ArrayList<String> optionsArrayList, LinearLayout optionsHolder) {
        AppCompatEditText choiceInput = new AppCompatEditText(this);
        choiceInput.setHint("Enter option");

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add Option")
                .setView(choiceInput)
                .setPositiveButton("Add", null)
                .setPositiveButton("Cancel", (dialog12, which) -> dialog12.dismiss())
                .create();

        dialog.setOnShowListener(dialog1 -> {
            Button add = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            add.setOnClickListener(v -> {
                String sChoice = Objects.requireNonNull(choiceInput.getText()).toString().trim();
                if (TextUtils.isEmpty(sChoice)){
                    choiceInput.setError("Enter option");
                }
                else{
                    optionsArrayList.add(sChoice);
                    View itemOption = View.inflate(this, R.layout.item_option_holder, null);

                    ((AppCompatTextView) itemOption.findViewById(R.id.choice_view)).setText(sChoice);
                    itemOption.findViewById(R.id.delete_choice).setOnClickListener(v1 -> {
                        optionsArrayList.remove(sChoice);
                        optionsHolder.removeView(itemOption);
                    });

                    optionsHolder.addView(itemOption, UiManager.getLayoutParams(15));
                    dialog1.dismiss();
                }
            });
        });

        dialog.show();
    }
}
