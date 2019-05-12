package com.wits.witssrcconnect.activities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wits.witssrcconnect.R;
import com.wits.witssrcconnect.managers.SrcPollManager;
import com.wits.witssrcconnect.managers.UiManager;
import com.wits.witssrcconnect.managers.UserManager;
import com.wits.witssrcconnect.utils.ServerUtils;

import java.util.ArrayList;
import java.util.Objects;

public class SrcPostPollActivity extends AppCompatActivity {

    public AppCompatEditText choiceInput;
    public Button add;
    public AppCompatImageButton deleteItem;
    public ArrayList<String> optionsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_poll);

        TextInputEditText title = findViewById(R.id.src_poll_title);
        TextInputEditText pollDesc = findViewById(R.id.src_poll_desc);

        AppCompatRadioButton singleSelect = findViewById(R.id.src_single_select);
        AppCompatRadioButton multiSelect = findViewById(R.id.src_multi_select);

        LinearLayout optionsHolder = findViewById(R.id.options_holder);


        findViewById(R.id.src_add_poll_option).setOnClickListener(v ->
                addVotingOption(optionsArrayList, optionsHolder));

        findViewById(R.id.src_post_poll).setOnClickListener(v -> {
            String sTitle = Objects.requireNonNull(title.getText()).toString().trim();
            String sPollDesc = Objects.requireNonNull(pollDesc.getText()).toString().trim();

            boolean everythingOkay = true;

            if (TextUtils.isEmpty(sTitle)) {
                everythingOkay = false;
                title.setError("Title required");
            }

            if (TextUtils.isEmpty(sPollDesc)) {
                everythingOkay = false;
                pollDesc.setError("Poll Description required");
            }

            boolean singleSelected = singleSelect.isChecked();
            boolean multiSelected = multiSelect.isChecked();

            int pollType = -1;
            if (singleSelected) {
                pollType = ServerUtils.POLL_TYPE_SINGLE_SELECT;
            } else if (multiSelected) {
                pollType = ServerUtils.POLL_TYPE_MULTI_SELECT;
            } else {
                everythingOkay = false;
                Toast.makeText(v.getContext(), "Select poll type", Toast.LENGTH_SHORT).show();
            }

            if (optionsArrayList.size() < 2) {
                everythingOkay = false;
                Toast.makeText(v.getContext(), "Add at least 2 options", Toast.LENGTH_SHORT).show();
            }

            if (everythingOkay) {

                String[] dateTime = UiManager.getDateTime();

                StringBuilder stringBuilder = new StringBuilder();
                for (String choice : optionsArrayList) {
                    stringBuilder.append(choice);
                    stringBuilder.append('~');
                }
                stringBuilder.deleteCharAt(stringBuilder.toString().lastIndexOf("~"));

                sPollDesc = sPollDesc.replace("\n", "\\n");
                ContentValues cv = new ContentValues();
                cv.put(ServerUtils.ACTION, ServerUtils.POST_POLL);
                cv.put(ServerUtils.SRC_USERNAME, UserManager.getCurrentlyLoggedInUsername());
                cv.put(ServerUtils.POLL_TITLE, sTitle);
                cv.put(ServerUtils.POLL_DESC, sPollDesc);
                cv.put(ServerUtils.POLL_CHOICE, stringBuilder.toString());
                cv.put(ServerUtils.POLL_TYPE, pollType);
                cv.put(ServerUtils.POLL_DATE, dateTime[0]);
                cv.put(ServerUtils.POLL_TIME, dateTime[1]);

                SrcPollManager.postPoll(cv, this);
            }
        });
    }

    private void addVotingOption(ArrayList<String> optionsArrayList, LinearLayout optionsHolder) {
        choiceInput = new AppCompatEditText(this);
        choiceInput.setHint("Enter option");

        choiceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (input.contains("~")) {
                    input = input.replace("~", "");
                    choiceInput.setText(input);
                    choiceInput.setSelection(input.length());
                }
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add Option")
                .setView(choiceInput)
                .setPositiveButton("Add", null)
                .create();

        dialog.setOnShowListener(dialog1 -> {
            add = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            add.setOnClickListener(v -> {
                String sChoice = Objects.requireNonNull(choiceInput.getText()).toString().trim();
                if (TextUtils.isEmpty(sChoice)) {
                    choiceInput.setError("Enter option");
                } else {
                    sChoice = sChoice.replace("\n", "\\n");
                    optionsArrayList.add(sChoice);
                    View itemOption = View.inflate(this, R.layout.item_option_holder, null);

                    ((AppCompatTextView) itemOption.findViewById(R.id.choice_view)).setText(sChoice);

                    String finalSChoice = sChoice;
                    deleteItem = findViewById(R.id.delete_choice);
                    deleteItem.setOnClickListener(v1 -> {
                        optionsArrayList.remove(finalSChoice);
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
